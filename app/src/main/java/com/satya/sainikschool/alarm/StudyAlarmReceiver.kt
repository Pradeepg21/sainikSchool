package com.satya.sainikschool.alarm

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.satya.sainikschool.MainActivity
import com.satya.sainikschool.R
import com.satya.sainikschool.data.store.AppGraph

/** Fires at a study session's start time: shows an alarm notification and arms the next occurrence. */
class StudyAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getStringExtra(AlarmScheduler.EXTRA_ID) ?: return
        val s = AppGraph.schedules.schedules.value.firstOrNull { it.id == id } ?: return
        if (!s.enabled) return

        val open = PendingIntent.getActivity(
            context, s.requestCode,
            Intent(context, MainActivity::class.java).putExtra(MainActivity.EXTRA_ROUTE, "planner").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val n = NotificationCompat.Builder(context, AlarmScheduler.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Study time: ${s.title}")
            .setContentText("${s.subjectTag} • ${s.durationMinutes} min session. Jai Hind — let's begin!")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(open)
            .build()

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED ||
            android.os.Build.VERSION.SDK_INT < 33
        ) {
            NotificationManagerCompat.from(context).notify(s.requestCode, n)
        }
        AlarmScheduler.schedule(context, s)
    }
}
