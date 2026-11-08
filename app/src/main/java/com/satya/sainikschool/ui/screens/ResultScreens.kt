package com.satya.sainikschool.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.satya.sainikschool.data.content.ContentRepository
import com.satya.sainikschool.data.model.ExamClass
import com.satya.sainikschool.data.model.PaperKind
import com.satya.sainikschool.data.store.AppGraph
import com.satya.sainikschool.data.store.LocalResultRepository
import com.satya.sainikschool.ui.components.AppScaffold
import com.satya.sainikschool.ui.components.Crumb
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
import com.satya.sainikschool.ui.theme.Danger
import com.satya.sainikschool.ui.theme.Saffron
import com.satya.sainikschool.ui.theme.Success
import com.satya.sainikschool.ui.theme.Warning

@Composable
fun ResultScreen(resultId: String, nav: Nav) {
    val results by AppGraph.results.results.collectAsState()
    val r = results.firstOrNull { it.id == resultId }
    val crumbs = listOf(Crumbs.home, Crumb("My Results", R.HISTORY), Crumb("Result"))
    AppScaffold("Result", crumbs, nav) { pad ->
        ScreenColumn(pad) {
            if (r == null) {
                Text("This result is no longer stored (only the last ${LocalResultRepository.MAX_RESULTS} attempts are kept).")
                return@ScreenColumn
            }
            val paper = remember(r.paperId) { ContentRepository.paper(r.paperId) }
            InfoCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ScoreRing(r.percent / 100f, Modifier.size(110.dp))
                    Spacer(Modifier.width(18.dp))
                    Column {
                        Text(r.paperTitle, style = MaterialTheme.typography.titleMedium)
                        Text("${r.score} / ${r.totalMarks} marks", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
                        Text("Time taken: ${fmtClock(r.timeTakenSec)}", style = MaterialTheme.typography.bodySmall)
                        Text(fmtDate(r.timestamp), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Pill("✓ ${r.correct} correct", Success)
                    Pill("✗ ${r.wrong} wrong", Danger)
                    Pill("— ${r.skipped} skipped", MaterialTheme.colorScheme.onSurfaceVariant)
                }
                val attempted = r.correct + r.wrong
                if (attempted > 0) Text("Accuracy: ${r.correct * 100 / attempted}% of attempted questions", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 8.dp))
            }

            if (paper.kind == PaperKind.FULL_MOCK) {
                val subjOk = r.subjectScores.all { (_, v) -> v.second == 0 || v.first * 100 >= v.second * 25 }
                val aggOk = r.percent >= 40f
                InfoCard(container = (if (subjOk && aggOk) Success else Danger).copy(alpha = 0.10f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.EmojiEvents, null, tint = if (subjOk && aggOk) Success else Danger)
                        Spacer(Modifier.width(8.dp))
                        Text(if (subjOk && aggOk) "You would QUALIFY (≥25% each subject, ≥40% overall)" else "Not yet qualifying — need ≥25% in each subject and ≥40% overall", fontWeight = FontWeight.SemiBold)
                    }
                    Text("Qualifying is the first step — aim for 70%+ to be in a strong merit position.", style = MaterialTheme.typography.bodySmall)
                }
            }

            SectionTitle("Subject-wise performance")
            r.subjectScores.forEach { (name, v) ->
                val pct = if (v.second == 0) 0f else v.first.toFloat() / v.second
                val color = when { pct >= 0.7f -> Success; pct >= 0.4f -> Warning; else -> Danger }
                InfoCard {
                    Row { Text(name, Modifier.weight(1f), fontWeight = FontWeight.SemiBold); Text("${v.first}/${v.second}") }
                    Spacer(Modifier.height(6.dp))
                    Box(Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(5.dp)).background(MaterialTheme.colorScheme.surfaceVariant)) {
                        Box(Modifier.fillMaxWidth(pct.coerceIn(0f, 1f)).fillMaxHeight().clip(RoundedCornerShape(5.dp)).background(color))
                    }
                    Text(
                        when { pct >= 0.7f -> "Strong — keep revising."; pct >= 0.4f -> "Average — practise more chapter tests."; else -> "Weak area — revisit notes and practise daily." },
                        style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Button(onClick = { nav.go(R.review(r.id)) }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Visibility, null); Spacer(Modifier.width(6.dp)); Text("Review answers with detailed solutions")
            }
            OutlinedButton(onClick = {
                val retakeId = when (paper.kind) {
                    PaperKind.CHAPTER_QUIZ -> ContentRepository.newChapterPaperId(paper.questions.first().chapterId)
                    PaperKind.SUBJECT_MOCK -> ContentRepository.newSubjectPaperId(paper.questions.first().subjectId)
                    else -> paper.id
                }
                nav.go(R.exam(retakeId))
            }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Refresh, null); Spacer(Modifier.width(6.dp)); Text(if (paper.kind == PaperKind.FULL_MOCK || paper.kind == PaperKind.PREVIOUS_YEAR) "Retake this paper" else "Try a new set")
            }
        }
    }
}

@Composable
fun ScoreRing(fraction: Float, modifier: Modifier) {
    val track = MaterialTheme.colorScheme.surfaceVariant
    val color = when { fraction >= 0.7f -> Success; fraction >= 0.4f -> Saffron; else -> Danger }
    Box(modifier, contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = 12.dp.toPx()
            val s = Size(size.width - stroke, size.height - stroke)
            val o = Offset(stroke / 2, stroke / 2)
            drawArc(track, 0f, 360f, false, o, s, style = Stroke(stroke))
            drawArc(color, -90f, 360f * fraction.coerceIn(0f, 1f), false, o, s, style = Stroke(stroke, cap = StrokeCap.Round))
        }
        Text("${"%.0f".format(fraction * 100)}%", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    }
}

/** Shows every question with the correct answer and explanation; with a result, also the student's choice. */
@Composable
fun ReviewScreen(resultId: String?, paperId: String?, nav: Nav) {
    val result = resultId?.let { AppGraph.results.get(it) }
    val pid = paperId ?: result?.paperId
    if (pid == null) {
        AppScaffold("Solutions", listOf(Crumbs.home, Crumb("Solutions")), nav) { pad -> ScreenColumn(pad) { Text("This result is no longer stored.") } }
        return
    }
    val paper = remember(pid) { ContentRepository.paper(pid) }
    var filter by remember { mutableStateOf("All") }
    val crumbs = if (result != null) listOf(Crumbs.home, Crumb("My Results", R.HISTORY), Crumb("Result", R.result(result.id)), Crumb("Solutions"))
    else paperCrumbs(paper) + Crumb("Solutions")

    AppScaffold("Solutions — ${paper.title}", crumbs, nav) { pad ->
        val indexed = paper.questions.mapIndexed { i, q -> Triple(i, q, result?.answers?.getOrNull(i)) }
        val shown = indexed.filter { (_, q, a) ->
            when (filter) {
                "Correct" -> a == q.answer
                "Wrong" -> a != null && a != -1 && a != q.answer
                "Skipped" -> a == -1
                else -> true
            }
        }
        LazyColumn(Modifier.fillMaxSize().padding(pad), contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (result != null) item {
                Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("All", "Wrong", "Skipped", "Correct").forEach { f -> FilterChip(selected = filter == f, onClick = { filter = f }, label = { Text(f) }) }
                }
            } else item {
                Text("Study each solution carefully, then attempt the paper to test yourself.", style = MaterialTheme.typography.bodySmall)
                Button(onClick = { nav.go(R.exam(paper.id)) }, Modifier.padding(top = 6.dp)) { Text("Attempt this paper") }
            }
            if (shown.isEmpty()) item { Text("No questions in this filter.") }
            itemsIndexed(shown, key = { _, t -> t.first }) { _, (i, q, a) ->
                InfoCard {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Q${i + 1}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(8.dp))
                        Pill(ContentRepository.subjectNameOf(q), MaterialTheme.colorScheme.secondary)
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(q.text, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(6.dp))
                    q.options.forEachIndexed { oi, o ->
                        val c = when { oi == q.answer -> Success; oi == a -> Danger; else -> MaterialTheme.colorScheme.onSurface }
                        Text("${"ABCD"[oi]}. $o" + when { oi == q.answer -> "  ✓"; oi == a -> "  ✗ (your answer)"; else -> "" }, color = c,
                            fontWeight = if (oi == q.answer || oi == a) FontWeight.SemiBold else FontWeight.Normal, modifier = Modifier.padding(vertical = 2.dp))
                    }
                    Spacer(Modifier.height(8.dp))
                    ExplanationCard(q, a)
                }
            }
        }
    }
}

@Composable
fun MocksScreen(cls: ExamClass, nav: Nav) {
    val results by AppGraph.results.results.collectAsState()
    val subjects = ContentRepository.subjectsFor(cls)
    AppScaffold("Mock Tests", Crumbs.cls(cls) + Crumb("Mock Tests"), nav) { pad ->
        ScreenColumn(pad) {
            InfoCard(container = MaterialTheme.colorScheme.primaryContainer) {
                Text("Real exam experience", style = MaterialTheme.typography.titleMedium)
                Text("Each full mock follows the official AISSEE ${if (cls == ExamClass.CLASS_6) "Class 6" else "Class 9"} pattern: ${subjects.sumOf { it.questionsInExam }} questions, ${subjects.sumOf { it.totalMarks }} marks, ${ContentRepository.examDuration(cls)} minutes, timer, question palette, mark-for-review, and detailed solutions after submission.", style = MaterialTheme.typography.bodySmall)
            }
            SectionTitle("Full-length mock tests")
            for (i in 1..ContentRepository.MOCKS_PER_CLASS) {
                val id = ContentRepository.mockId(cls, i)
                val best = results.filter { it.paperId == id }.maxByOrNull { it.score }
                ListRow(Icons.Default.Quiz, "AISSEE Full Mock Test $i", if (best != null) "Best: ${best.score}/${best.totalMarks}" else "Not attempted • ${ContentRepository.examDuration(cls)} min",
                    if (best != null) Success else MaterialTheme.colorScheme.primary,
                    trailing = { if (best != null) Icon(Icons.Default.CheckCircle, null, tint = Success) else Text("Start", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold) },
                ) { nav.go(R.exam(id)) }
            }
            SectionTitle("Sectional tests (one subject, fresh questions every time)")
            subjects.forEach { s ->
                ListRow(subjectIcon(s.iconKey), s.name, "${s.questionsInExam} questions • ${s.totalMarks} marks", subjectColor(s.iconKey)) { nav.go(R.exam(ContentRepository.newSubjectPaperId(s.id))) }
            }
        }
    }
}

@Composable
fun PyqScreen(cls: ExamClass, nav: Nav) {
    val subjects = ContentRepository.subjectsFor(cls)
    var subjectId by remember { mutableStateOf(subjects.first().id) }
    val s = ContentRepository.subject(subjectId)
    AppScaffold("Previous Years", Crumbs.cls(cls) + Crumb("Previous Years"), nav) { pad ->
        ScreenColumn(pad) {
            InfoCard(container = MaterialTheme.colorScheme.tertiaryContainer) {
                Text("Year-wise practice papers (${ContentRepository.previousYears.last()}–${ContentRepository.previousYears.first()})", style = MaterialTheme.typography.titleSmall)
                Text("Each set follows the subject's AISSEE pattern for that year and covers topics frequently asked in past exams, with full solutions. Official question papers can be downloaded from the NTA website and added to the app through Firestore later.", style = MaterialTheme.typography.bodySmall)
            }
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                subjects.forEach { sub -> FilterChip(selected = sub.id == subjectId, onClick = { subjectId = sub.id }, label = { Text(sub.name) }) }
            }
            ContentRepository.previousYears.forEach { year ->
                val pid = ContentRepository.yearId(s.id, year)
                InfoCard {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("AISSEE $year — ${s.name}", style = MaterialTheme.typography.titleMedium)
                            Text("${s.questionsInExam} questions • ${s.totalMarks} marks", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { nav.go(R.exam(pid)) }, modifier = Modifier.weight(1f)) { Text("Take test") }
                        OutlinedButton(onClick = { nav.go(R.solutions(pid)) }, modifier = Modifier.weight(1f)) { Text("See solutions") }
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryScreen(nav: Nav) {
    val results by AppGraph.results.results.collectAsState()
    var confirm by remember { mutableStateOf(false) }
    AppScaffold("My Results", listOf(Crumbs.home, Crumb("My Results")), nav, actions = {
        if (results.isNotEmpty()) IconButton(onClick = { confirm = true }) { Icon(Icons.Default.DeleteSweep, "Clear") }
    }) { pad ->
        ScreenColumn(pad) {
            Text("Your last ${LocalResultRepository.MAX_RESULTS} test attempts are saved. Older ones are removed automatically.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            if (results.isEmpty()) InfoCard { Text("No attempts yet. Take a mock test or chapter practice to see your progress here.") }
            if (results.size >= 2) {
                InfoCard {
                    Text("Progress (oldest → latest)", style = MaterialTheme.typography.titleSmall)
                    Spacer(Modifier.height(8.dp))
                    Row(Modifier.fillMaxWidth().height(110.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Bottom) {
                        results.reversed().forEach { r ->
                            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("${r.percent.toInt()}%", style = MaterialTheme.typography.labelSmall)
                                Box(Modifier.fillMaxWidth().height((80 * r.percent / 100).coerceAtLeast(2f).dp).clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                                    .background(if (r.percent >= 70) Success else if (r.percent >= 40) Saffron else Danger))
                            }
                        }
                    }
                }
            }
            results.forEach { r ->
                ListRow(Icons.Default.Quiz, r.paperTitle, "${r.score}/${r.totalMarks} (${"%.1f".format(r.percent)}%) • ${fmtDate(r.timestamp)}",
                    if (r.percent >= 70) Success else if (r.percent >= 40) Saffron else Danger) { nav.go(R.result(r.id)) }
            }
        }
    }
    if (confirm) AlertDialog(
        onDismissRequest = { confirm = false },
        title = { Text("Clear all results?") },
        text = { Text("This removes your saved attempts from this device.") },
        confirmButton = { TextButton(onClick = { AppGraph.results.clear(); confirm = false }) { Text("Clear", color = Danger) } },
        dismissButton = { TextButton(onClick = { confirm = false }) { Text("Cancel") } },
    )
}

