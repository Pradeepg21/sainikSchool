package com.satya.sainikschool.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import com.satya.sainikschool.data.model.StudySchedule
import com.satya.sainikschool.data.store.AppGraph
import java.util.Calendar

object AlarmScheduler {
    const val CHANNEL_ID = "study_alarms"
    const val EXTRA_ID = "schedule_id"

    fun createChannel(context: Context) {
        val nm = context.getSystemService(NotificationManager::class.java)
        val ch = NotificationChannel(CHANNEL_ID, "Study schedule alarms", NotificationManager.IMPORTANCE_HIGH).apply {
            description = "Rings at the start of each scheduled study session"
            enableVibration(true)
            vibrationPattern = longArrayOf(0, 600, 300, 600, 300, 600)
            setSound(
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM),
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build(),
            )
        }
        nm.createNotificationChannel(ch)
    }

    /** Next trigger time strictly after [now] on one of the schedule's days, or null if no days. */
    fun nextTrigger(s: StudySchedule, now: Calendar = Calendar.getInstance()): Long? {
        if (s.days.isEmpty()) return null
        for (offset in 0..7) {
            val c = (now.clone() as Calendar).apply {
                add(Calendar.DAY_OF_YEAR, offset)
                set(Calendar.HOUR_OF_DAY, s.hour); set(Calendar.MINUTE, s.minute)
                set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
            }
            if (c.get(Calendar.DAY_OF_WEEK) in s.days && c.timeInMillis > now.timeInMillis) return c.timeInMillis
        }
        return null
    }

    private fun pending(context: Context, s: StudySchedule): PendingIntent =
        PendingIntent.getBroadcast(
            context, s.requestCode,
            Intent(context, StudyAlarmReceiver::class.java).putExtra(EXTRA_ID, s.id),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

    fun canExact(context: Context): Boolean {
        val am = context.getSystemService(AlarmManager::class.java)
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.S || am.canScheduleExactAlarms()
    }

    fun schedule(context: Context, s: StudySchedule) {
        cancel(context, s)
        if (!s.enabled) return
        val at = nextTrigger(s) ?: return
        val am = context.getSystemService(AlarmManager::class.java)
        val pi = pending(context, s)
        if (canExact(context)) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, at, pi)
        } else {
            // Falls back to an inexact alarm (may be delayed a few minutes) until the user grants exact alarms.
            am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, at, pi)
        }
    }

    fun cancel(context: Context, s: StudySchedule) {
        context.getSystemService(AlarmManager::class.java).cancel(pending(context, s))
    }

    fun rescheduleAll(context: Context) {
        AppGraph.schedules.schedules.value.forEach { schedule(context, it) }
    }
}
