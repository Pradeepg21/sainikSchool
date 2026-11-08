package com.satya.sainikschool.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.satya.sainikschool.alarm.AlarmScheduler
import com.satya.sainikschool.data.model.StudySchedule
import com.satya.sainikschool.data.model.UserNote
import com.satya.sainikschool.data.store.AppGraph
import com.satya.sainikschool.ui.components.AppScaffold
import com.satya.sainikschool.ui.components.Crumb
import com.satya.sainikschool.ui.components.InfoCard
import com.satya.sainikschool.ui.components.Nav
import com.satya.sainikschool.ui.components.ScreenColumn
import com.satya.sainikschool.ui.nav.Crumbs
import com.satya.sainikschool.ui.nav.R
import com.satya.sainikschool.ui.theme.Danger
import com.satya.sainikschool.ui.theme.Saffron
import java.util.Calendar
import java.util.UUID

private val tags = listOf("General", "Mathematics", "Intelligence", "English", "Language", "General Knowledge", "General Science", "Social Science", "Revision", "Mock Test")

// ---------------- Notes ----------------

@Composable
fun NotesScreen(nav: Nav) {
    val notes by AppGraph.notes.notes.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }
    var tag by rememberSaveable { mutableStateOf("All") }
    val shown = notes.filter {
        (tag == "All" || it.subjectTag == tag) &&
            (query.isBlank() || it.title.contains(query, true) || it.body.contains(query, true))
    }
    AppScaffold("My Notes", listOf(Crumbs.home, Crumb("My Notes")), nav, floating = {
        ExtendedFloatingActionButton(onClick = {
            val id = UUID.randomUUID().toString()
            AppGraph.notes.upsert(UserNote(id, "", "", "General", System.currentTimeMillis()))
            nav.go(R.note(id))
        }, icon = { Icon(Icons.Default.Add, null) }, text = { Text("New note") })
    }) { pad ->
        ScreenColumn(pad) {
            OutlinedTextField(query, { query = it }, Modifier.fillMaxWidth(), placeholder = { Text("Search notes") }, leadingIcon = { Icon(Icons.Default.Search, null) }, singleLine = true)
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                (listOf("All") + tags).forEach { t -> FilterChip(selected = tag == t, onClick = { tag = t }, label = { Text(t) }) }
            }
            if (shown.isEmpty()) InfoCard {
                Text("No notes yet.", fontWeight = FontWeight.SemiBold)
                Text("Write formulas, tricky questions, GK facts and mistakes from mock tests. Short notes make revision fast before the exam.", style = MaterialTheme.typography.bodySmall)
            }
            shown.forEach { n ->
                InfoCard(onClick = { nav.go(R.note(n.id)) }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(n.title.ifBlank { "Untitled note" }, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                        if (n.pinned) Icon(Icons.Default.PushPin, "Pinned", tint = Saffron)
                    }
                    if (n.body.isNotBlank()) Text(n.body, maxLines = 3, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodyMedium)
                    Text("${n.subjectTag} • ${fmtDate(n.updatedAt)}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Spacer(Modifier.height(72.dp))
        }
    }
}

@Composable
fun NoteEditScreen(noteId: String, nav: Nav) {
    val existing = remember(noteId) { AppGraph.notes.notes.value.firstOrNull { it.id == noteId } }
    var title by rememberSaveable { mutableStateOf(existing?.title ?: "") }
    var body by rememberSaveable { mutableStateOf(existing?.body ?: "") }
    var tag by rememberSaveable { mutableStateOf(existing?.subjectTag ?: "General") }
    var pinned by rememberSaveable { mutableStateOf(existing?.pinned ?: false) }
    var confirmDelete by remember { mutableStateOf(false) }

    fun save() {
        if (title.isBlank() && body.isBlank()) AppGraph.notes.delete(noteId)
        else AppGraph.notes.upsert(UserNote(noteId, title.trim(), body, tag, System.currentTimeMillis(), pinned))
    }

    androidx.activity.compose.BackHandler { save(); nav.back() }
    AppScaffold(
        if (title.isBlank()) "Note" else title,
        listOf(Crumbs.home, Crumb("My Notes", R.NOTES), Crumb(title.ifBlank { "Note" })),
        Nav(nav.go, nav.back, nav.home, { save(); nav.crumb(it) }, nav.replace),
        onBack = { save(); nav.back() },
        onHome = { save(); nav.home() },
        actions = {
            IconButton(onClick = { pinned = !pinned }) { Icon(if (pinned) Icons.Default.PushPin else Icons.Outlined.PushPin, "Pin") }
            IconButton(onClick = { confirmDelete = true }) { Icon(Icons.Default.Delete, "Delete") }
        },
    ) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(title, { title = it }, Modifier.fillMaxWidth(), label = { Text("Title") }, singleLine = true)
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                tags.forEach { t -> FilterChip(selected = tag == t, onClick = { tag = t }, label = { Text(t) }) }
            }
            OutlinedTextField(body, { body = it }, Modifier.fillMaxWidth().weight(1f), label = { Text("Write your note…") })
            Button(onClick = { save(); nav.back() }, modifier = Modifier.fillMaxWidth()) { Text("Save note") }
        }
    }
    if (confirmDelete) AlertDialog(
        onDismissRequest = { confirmDelete = false },
        title = { Text("Delete this note?") },
        confirmButton = { TextButton(onClick = { AppGraph.notes.delete(noteId); confirmDelete = false; nav.back() }) { Text("Delete", color = Danger) } },
        dismissButton = { TextButton(onClick = { confirmDelete = false }) { Text("Cancel") } },
    )
}

// ---------------- Study planner ----------------

private val dayLabels = listOf(Calendar.MONDAY to "Mon", Calendar.TUESDAY to "Tue", Calendar.WEDNESDAY to "Wed", Calendar.THURSDAY to "Thu", Calendar.FRIDAY to "Fri", Calendar.SATURDAY to "Sat", Calendar.SUNDAY to "Sun")

@Composable
fun PlannerScreen(nav: Nav) {
    val context = LocalContext.current
    val schedules by AppGraph.schedules.schedules.collectAsState()
    var editing by remember { mutableStateOf<StudySchedule?>(null) }
    var exactOk by remember { mutableStateOf(AlarmScheduler.canExact(context)) }

    fun save(s: StudySchedule) { AppGraph.schedules.upsert(s); AlarmScheduler.schedule(context, s) }

    AppScaffold("Study Planner", listOf(Crumbs.home, Crumb("Study Planner")), nav, floating = {
        ExtendedFloatingActionButton(onClick = {
            editing = StudySchedule(UUID.randomUUID().toString(), "", "Mathematics", 17, 0, 60, dayLabels.take(6).map { it.first }.toSet(), true)
        }, icon = { Icon(Icons.Default.Add, null) }, text = { Text("Add session") })
    }) { pad ->
        ScreenColumn(pad) {
            if (!exactOk && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                InfoCard(container = MaterialTheme.colorScheme.tertiaryContainer) {
                    Text("Allow 'Alarms & reminders' so your study alarm rings exactly on time.", style = MaterialTheme.typography.bodyMedium)
                    TextButton(onClick = {
                        context.startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM, Uri.parse("package:${context.packageName}")))
                    }) { Text("Open settings") }
                    TextButton(onClick = { exactOk = AlarmScheduler.canExact(context); if (exactOk) AlarmScheduler.rescheduleAll(context) }) { Text("I've allowed it — refresh") }
                }
            }
            if (schedules.isEmpty()) {
                InfoCard {
                    Text("Build your timetable", style = MaterialTheme.typography.titleMedium)
                    Text("Add study sessions for each subject. An alarm notification will ring at the start of every session on the days you choose.", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(onClick = { suggestedPlan().forEach { save(it) } }) { Text("Use a suggested daily plan") }
                }
            }
            dayLabels.forEach { (d, label) ->
                val list = schedules.filter { d in it.days }
                if (list.isNotEmpty()) {
                    Text(label + if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == d) " (today)" else "", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                    list.forEach { s ->
                        InfoCard(onClick = { editing = s }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Alarm, null, tint = if (s.enabled) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline)
                                Spacer(Modifier.width(12.dp))
                                Column(Modifier.weight(1f)) {
                                    Text("%02d:%02d  %s".format(s.hour, s.minute, s.title.ifBlank { s.subjectTag }), style = MaterialTheme.typography.titleMedium)
                                    Text("${s.subjectTag} • ${s.durationMinutes} min", style = MaterialTheme.typography.bodySmall)
                                }
                                Switch(s.enabled, { save(s.copy(enabled = it)) })
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(72.dp))
        }
    }

    editing?.let { s ->
        ScheduleDialog(s, onDismiss = { editing = null }, onSave = { save(it); editing = null },
            onDelete = { AlarmScheduler.cancel(context, s); AppGraph.schedules.delete(s.id); editing = null },
            isNew = schedules.none { it.id == s.id })
    }
}

private fun suggestedPlan(): List<StudySchedule> {
    val week = setOf(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY)
    fun s(t: String, tag: String, h: Int, m: Int, d: Int, days: Set<Int> = week) = StudySchedule(UUID.randomUUID().toString(), t, tag, h, m, d, days, true)
    return listOf(
        s("Morning maths drill", "Mathematics", 6, 30, 45),
        s("Reasoning practice", "Intelligence", 16, 30, 30),
        s("Language & GK", "General Knowledge", 17, 15, 45),
        s("Revision of mistakes", "Revision", 19, 30, 30),
        s("Weekly full mock test", "Mock Test", 10, 0, 180, setOf(Calendar.SUNDAY)),
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun ScheduleDialog(initial: StudySchedule, isNew: Boolean, onDismiss: () -> Unit, onSave: (StudySchedule) -> Unit, onDelete: () -> Unit) {
    var title by remember { mutableStateOf(initial.title) }
    var tag by remember { mutableStateOf(initial.subjectTag) }
    var days by remember { mutableStateOf(initial.days) }
    var duration by remember { mutableStateOf(initial.durationMinutes.toString()) }
    val time = rememberTimePickerState(initial.hour, initial.minute, is24Hour = false)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isNew) "New study session" else "Edit session") },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                run {
                    OutlinedTextField(title, { title = it }, label = { Text("Title (e.g. Fractions revision)") }, singleLine = true)
                    TimePicker(time)
                    OutlinedTextField(duration, { v -> duration = v.filter { it.isDigit() }.take(3) }, label = { Text("Duration (minutes)") }, singleLine = true)
                    Text("Subject", style = MaterialTheme.typography.labelLarge)
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        (tags - "General").forEach { t -> FilterChip(selected = tag == t, onClick = { tag = t }, label = { Text(t) }) }
                    }
                    Text("Repeat on", style = MaterialTheme.typography.labelLarge)
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        dayLabels.forEach { (d, l) -> FilterChip(selected = d in days, onClick = { days = if (d in days) days - d else days + d }, label = { Text(l) }) }
                    }
                }
            }
        },
        confirmButton = {
            Button(enabled = days.isNotEmpty(), onClick = {
                onSave(initial.copy(title = title.trim(), subjectTag = tag, hour = time.hour, minute = time.minute,
                    durationMinutes = duration.toIntOrNull()?.coerceIn(5, 600) ?: 60, days = days, enabled = true))
            }) { Text("Save") }
        },
        dismissButton = {
            Row {
                if (!isNew) TextButton(onClick = onDelete) { Text("Delete", color = Danger) }
                TextButton(onClick = onDismiss) { Text("Cancel") }
            }
        },
    )
}
