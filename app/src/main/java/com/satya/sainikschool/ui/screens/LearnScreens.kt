package com.satya.sainikschool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.satya.sainikschool.data.content.ContentRepository
import com.satya.sainikschool.data.content.Generators
import com.satya.sainikschool.data.model.UserNote
import com.satya.sainikschool.data.store.AppGraph
import com.satya.sainikschool.ui.components.AppScaffold
import com.satya.sainikschool.ui.components.Bullet
import com.satya.sainikschool.ui.components.InfoCard
import com.satya.sainikschool.ui.components.ListRow
import com.satya.sainikschool.ui.components.Nav
import com.satya.sainikschool.ui.components.Pill
import com.satya.sainikschool.ui.components.ScreenColumn
import com.satya.sainikschool.ui.components.SectionTitle
import com.satya.sainikschool.ui.components.subjectColor
import com.satya.sainikschool.ui.components.subjectIcon
import com.satya.sainikschool.ui.nav.Crumbs
import com.satya.sainikschool.ui.nav.R
import com.satya.sainikschool.ui.theme.Saffron
import java.util.UUID

@Composable
fun SubjectScreen(subjectId: String, nav: Nav) {
    val s = ContentRepository.subject(subjectId)
    val color = subjectColor(s.iconKey)
    AppScaffold(s.name, Crumbs.subject(subjectId), nav) { pad ->
        ScreenColumn(pad) {
            InfoCard(container = color.copy(alpha = 0.10f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(subjectIcon(s.iconKey), null, tint = color)
                    Spacer(Modifier.width(10.dp))
                    Text("${s.questionsInExam} questions × ${s.marksPerQuestion} marks = ${s.totalMarks} marks in the exam", style = MaterialTheme.typography.titleSmall)
                }
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(onClick = { nav.go(R.exam(ContentRepository.newSubjectPaperId(s.id))) }, modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Timer, null); Spacer(Modifier.width(6.dp)); Text("Sectional Test")
                    }
                    OutlinedButton(onClick = { nav.go(R.pyq(s.examClass)) }, modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.History, null); Spacer(Modifier.width(6.dp)); Text("Prev. Years")
                    }
                }
            }
            SectionTitle("Chapters (${s.chapters.size})")
            s.chapters.forEachIndexed { i, c ->
                val practice = if (Generators.has(c.genKey)) "Unlimited practice" else "${ContentRepository.curatedCount(c.id)} solved Qs"
                ListRow(subjectIcon(s.iconKey), "${i + 1}. ${c.title}", "${c.summary}\n$practice", color) { nav.go(R.chapter(c.id)) }
            }
        }
    }
}

@Composable
fun ChapterScreen(chapterId: String, nav: Nav) {
    val (s, c) = ContentRepository.chapter(chapterId)
    val idx = s.chapters.indexOf(c)
    val color = subjectColor(s.iconKey)
    AppScaffold(c.title, Crumbs.chapter(chapterId), nav) { pad ->
        ScreenColumn(pad) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Pill(s.name, color)
                Pill("Chapter ${idx + 1} of ${s.chapters.size}", MaterialTheme.colorScheme.secondary)
            }
            Text(c.summary, style = MaterialTheme.typography.bodyLarge)

            c.notes.forEach { n ->
                InfoCard {
                    Text(n.heading, style = MaterialTheme.typography.titleMedium, color = color)
                    Spacer(Modifier.height(6.dp))
                    n.points.forEach { Bullet(it, color) }
                    if (n.example != null) {
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider()
                        Spacer(Modifier.height(8.dp))
                        Row {
                            Icon(Icons.Default.Lightbulb, null, tint = Saffron)
                            Spacer(Modifier.width(8.dp))
                            Text("Example: ${n.example}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }

            InfoCard(container = MaterialTheme.colorScheme.secondaryContainer) {
                Text("Practise this chapter", style = MaterialTheme.typography.titleMedium)
                Text(
                    if (Generators.has(c.genKey)) "Every attempt gives a fresh set of questions with step-by-step solutions."
                    else "Answer each question and see the detailed explanation instantly.",
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(Modifier.height(8.dp))
                Button(onClick = { nav.go(R.exam(ContentRepository.newChapterPaperId(c.id))) }, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.PlayArrow, null); Spacer(Modifier.width(6.dp)); Text("Start Practice")
                }
            }

            OutlinedButton(onClick = {
                val id = UUID.randomUUID().toString()
                AppGraph.notes.upsert(UserNote(id, c.title, "", s.name, System.currentTimeMillis()))
                nav.go(R.note(id))
            }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.EditNote, null); Spacer(Modifier.width(6.dp)); Text("Write my own note on this chapter")
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                if (idx > 0) OutlinedButton(onClick = { nav.replace(R.chapter(s.chapters[idx - 1].id)) }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null); Spacer(Modifier.width(4.dp)); Text("Previous")
                } else Spacer(Modifier.weight(1f))
                if (idx < s.chapters.lastIndex) OutlinedButton(onClick = { nav.replace(R.chapter(s.chapters[idx + 1].id)) }, modifier = Modifier.weight(1f)) {
                    Text("Next"); Spacer(Modifier.width(4.dp)); Icon(Icons.AutoMirrored.Filled.ArrowForward, null)
                } else Spacer(Modifier.weight(1f))
            }
        }
    }
}
