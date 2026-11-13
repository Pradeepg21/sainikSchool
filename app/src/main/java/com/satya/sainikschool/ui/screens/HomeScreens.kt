package com.satya.sainikschool.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.satya.sainikschool.alarm.AlarmScheduler
import com.satya.sainikschool.data.content.ContentRepository
import com.satya.sainikschool.data.model.ExamClass
import com.satya.sainikschool.data.store.AppGraph
import com.satya.sainikschool.ui.components.AppScaffold
import com.satya.sainikschool.ui.components.Crumb
import com.satya.sainikschool.ui.components.InfoCard
import com.satya.sainikschool.ui.components.ListRow
import com.satya.sainikschool.ui.components.Nav
import com.satya.sainikschool.ui.components.ScreenColumn
import com.satya.sainikschool.ui.components.SectionTitle
import com.satya.sainikschool.ui.components.subjectColor
import com.satya.sainikschool.ui.components.subjectIcon
import com.satya.sainikschool.ui.nav.Crumbs
import com.satya.sainikschool.ui.nav.R
import com.satya.sainikschool.ui.theme.Navy
import com.satya.sainikschool.ui.theme.Olive
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(nav: Nav) {
    val clsId by AppGraph.prefs.classId.collectAsState()
    val cls = clsId?.let { ExamClass.from(it) } ?: ExamClass.CLASS_6
    val results by AppGraph.results.results.collectAsState()
    val schedules by AppGraph.schedules.schedules.collectAsState()

    AppScaffold("Sainik Prep", listOf(Crumb("Home")), nav, showBack = false) { pad ->
        ScreenColumn(pad) {
            // Hero
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp))
                    .background(Brush.linearGradient(listOf(Navy, Olive))).padding(20.dp)
            ) {
                Column {
                    Text("Jai Hind, Cadet!", style = MaterialTheme.typography.headlineSmall, color = Color.White)
                    Text("Your complete AISSEE preparation — learn, practise, test and track.", color = Color.White.copy(alpha = 0.85f))
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "AISSEE 2027 exam on 31 January 2027 • ${ContentRepository.chapterCount()} chapters • ${ContentRepository.totalCurated()}+ solved questions + unlimited practice",
                        style = MaterialTheme.typography.labelMedium, color = Color.White.copy(alpha = 0.9f),
                    )
                }
            }

            SectionTitle("Preparing for")
            SingleChoiceSegmentedButtonRow(Modifier.fillMaxWidth()) {
                ExamClass.entries.forEachIndexed { i, c ->
                    SegmentedButton(
                        selected = c == cls, onClick = { AppGraph.prefs.setClass(c.id) },
                        shape = SegmentedButtonDefaults.itemShape(i, ExamClass.entries.size),
                    ) { Text(c.title) }
                }
            }
            Text(cls.subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Tile(Icons.AutoMirrored.Filled.MenuBook, "Learn", "Subject & chapter notes", MaterialTheme.colorScheme.primary, Modifier.weight(1f)) { nav.go(R.cls(cls)) }
                Tile(Icons.Default.Timer, "Mock Tests", "Full exam with timer", Color(0xFFC62828), Modifier.weight(1f)) { nav.go(R.mocks(cls)) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Tile(Icons.Default.History, "Previous Years", "Last 10 years, subject-wise", Color(0xFF6A1B9A), Modifier.weight(1f)) { nav.go(R.pyq(cls)) }
                Tile(Icons.Default.EmojiEvents, "My Results", "Last 5 attempts", Color(0xFFE8862A), Modifier.weight(1f)) { nav.go(R.HISTORY) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Tile(Icons.Default.EditNote, "My Notes", "Write & pin notes", Color(0xFF00838F), Modifier.weight(1f)) { nav.go(R.NOTES) }
                Tile(Icons.Default.Alarm, "Study Planner", "Timetable with alarms", Color(0xFF2E7D32), Modifier.weight(1f)) { nav.go(R.PLANNER) }
            }
            ListRow(Icons.Default.School, "Syllabus & Admission Process", "Eligibility, exam pattern, syllabus, dates, fees, counselling, schools, FAQs", Navy) { nav.go(R.ADMISSION) }

            val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            val todays = schedules.filter { it.enabled && today in it.days }
            SectionTitle("Today's study plan")
            if (todays.isEmpty()) {
                InfoCard(onClick = { nav.go(R.PLANNER) }) {
                    Text("No sessions planned for today.", style = MaterialTheme.typography.bodyMedium)
                    Text("Tap to create a timetable — you'll get an alarm at each session.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else todays.forEach { s ->
                ListRow(Icons.Default.Alarm, s.title, "%02d:%02d • %s • %d min".format(s.hour, s.minute, s.subjectTag, s.durationMinutes), Color(0xFF2E7D32)) { nav.go(R.PLANNER) }
            }

            SectionTitle("Latest result")
            val last = results.firstOrNull()
            if (last == null) {
                InfoCard(onClick = { nav.go(R.mocks(cls)) }) {
                    Text("No tests taken yet.", style = MaterialTheme.typography.bodyMedium)
                    Text("Start with a full mock to know where you stand.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                ListRow(Icons.Default.Quiz, last.paperTitle, "${last.score}/${last.totalMarks} • ${"%.1f".format(last.percent)}% • ${fmtDate(last.timestamp)}", Color(0xFFE8862A)) { nav.go(R.result(last.id)) }
            }
            if (!AlarmScheduler.canExact(androidx.compose.ui.platform.LocalContext.current) && schedules.isNotEmpty()) {
                InfoCard(container = MaterialTheme.colorScheme.tertiaryContainer, onClick = { nav.go(R.PLANNER) }) {
                    Text("Allow exact alarms so study reminders ring on time.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
private fun Tile(icon: ImageVector, title: String, sub: String, color: Color, modifier: Modifier, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = modifier.height(118.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(1.dp)) {
        Column(Modifier.padding(14.dp)) {
            Box(Modifier.size(38.dp).clip(CircleShape).background(color.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) { Icon(icon, null, tint = color) }
            Spacer(Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            Text(sub, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2)
        }
    }
}

fun fmtDate(ts: Long): String = SimpleDateFormat("d MMM yyyy, h:mm a", Locale.getDefault()).format(Date(ts))

@Composable
fun ClassScreen(cls: ExamClass, nav: Nav) {
    val subjects = ContentRepository.subjectsFor(cls)
    AppScaffold(cls.title, Crumbs.cls(cls), nav) { pad ->
        ScreenColumn(pad) {
            InfoCard(container = MaterialTheme.colorScheme.primaryContainer) {
                Text(cls.subtitle, style = MaterialTheme.typography.titleSmall)
                val totalQ = subjects.sumOf { it.questionsInExam }
                val totalM = subjects.sumOf { it.totalMarks }
                Text("Exam: $totalQ questions • $totalM marks • ${ContentRepository.examDuration(cls)} minutes • no negative marking", style = MaterialTheme.typography.bodySmall)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FilledTonalButton(onClick = { nav.go(R.mocks(cls)) }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Timer, null); Spacer(Modifier.width(6.dp)); Text("Mock Tests")
                }
                FilledTonalButton(onClick = { nav.go(R.pyq(cls)) }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.History, null); Spacer(Modifier.width(6.dp)); Text("Prev. Years")
                }
            }
            SectionTitle("Subjects")
            subjects.forEach { s ->
                ListRow(
                    subjectIcon(s.iconKey), s.name,
                    "${s.chapters.size} chapters • ${s.questionsInExam} Qs × ${s.marksPerQuestion} = ${s.totalMarks} marks",
                    subjectColor(s.iconKey),
                ) { nav.go(R.subject(s.id)) }
            }
            ListRow(Icons.AutoMirrored.Filled.Assignment, "Full syllabus & admission", "See every topic and the complete admission process", MaterialTheme.colorScheme.secondary) { nav.go(R.ADMISSION) }
            Text(
                "Tip: Mathematics carries ${subjects.first { it.iconKey == "math" }.totalMarks} of ${subjects.sumOf { it.totalMarks }} marks — give it the most time.",
                style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
