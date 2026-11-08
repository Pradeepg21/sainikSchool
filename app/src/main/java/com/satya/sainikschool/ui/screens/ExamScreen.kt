package com.satya.sainikschool.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.satya.sainikschool.data.content.ContentRepository
import com.satya.sainikschool.data.model.ExamResult
import com.satya.sainikschool.data.model.Paper
import com.satya.sainikschool.data.model.PaperKind
import com.satya.sainikschool.data.model.Question
import com.satya.sainikschool.data.store.AppGraph
import com.satya.sainikschool.ui.components.AppScaffold
import com.satya.sainikschool.ui.components.Crumb
import com.satya.sainikschool.ui.components.Nav
import com.satya.sainikschool.ui.components.Pill
import com.satya.sainikschool.ui.nav.Crumbs
import com.satya.sainikschool.ui.nav.R
import com.satya.sainikschool.ui.theme.Danger
import com.satya.sainikschool.ui.theme.Review
import com.satya.sainikschool.ui.theme.Saffron
import com.satya.sainikschool.ui.theme.Success
import kotlinx.coroutines.delay
import java.util.UUID

/** Breadcrumb trail that leads to a paper (used by exam & review screens). */
fun paperCrumbs(p: Paper): List<Crumb> = when (p.kind) {
    PaperKind.CHAPTER_QUIZ -> Crumbs.chapter(p.questions.first().chapterId)
    PaperKind.SUBJECT_MOCK -> Crumbs.subject(p.questions.first().subjectId)
    PaperKind.FULL_MOCK -> Crumbs.cls(p.examClass) + Crumb("Mock Tests", R.mocks(p.examClass))
    PaperKind.PREVIOUS_YEAR -> Crumbs.cls(p.examClass) + Crumb("Previous Years", R.pyq(p.examClass))
}

fun scorePaper(p: Paper, answers: List<Int>, timeSec: Int): ExamResult {
    var score = 0; var correct = 0; var wrong = 0; var skipped = 0
    val subj = linkedMapOf<String, Pair<Int, Int>>()
    p.questions.forEachIndexed { i, q ->
        val name = ContentRepository.subjectNameOf(q)
        val (s, m) = subj[name] ?: (0 to 0)
        val a = answers.getOrElse(i) { -1 }
        val got = when {
            a == -1 -> { skipped++; 0 }
            a == q.answer -> { correct++; q.marks }
            else -> { wrong++; 0 }
        }
        score += got
        subj[name] = (s + got) to (m + q.marks)
    }
    return ExamResult(
        UUID.randomUUID().toString(), p.id, p.title, p.examClass.id, p.kind.name, System.currentTimeMillis(),
        score, p.totalMarks, correct, wrong, skipped, timeSec, subj, answers,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ExamScreen(paperId: String, nav: Nav) {
    val paper = remember(paperId) { ContentRepository.paper(paperId) }
    val n = paper.questions.size
    val practice = paper.kind == PaperKind.CHAPTER_QUIZ

    var answers by rememberSaveable { mutableStateOf(List(n) { -1 }) }
    var marked by rememberSaveable { mutableStateOf(List(n) { false }) }
    var visited by rememberSaveable { mutableStateOf(List(n) { it == 0 }) }
    var current by rememberSaveable { mutableIntStateOf(0) }
    var remaining by rememberSaveable { mutableIntStateOf(paper.durationMinutes * 60) }
    var elapsed by rememberSaveable { mutableIntStateOf(0) }
    var submitted by rememberSaveable { mutableStateOf(false) }
    var showPalette by remember { mutableStateOf(false) }
    var confirmSubmit by remember { mutableStateOf(false) }
    var confirmLeave by remember { mutableStateOf<(() -> Unit)?>(null) }

    fun submit() {
        if (submitted) return
        submitted = true
        val r = scorePaper(paper, answers, elapsed)
        AppGraph.results.save(r)
        nav.replace(R.result(r.id))
    }

    fun goTo(i: Int) {
        current = i.coerceIn(0, n - 1)
        visited = visited.toMutableList().also { it[current] = true }
    }

    LaunchedEffect(paperId) {
        while (!submitted) {
            delay(1000)
            elapsed++
            if (!practice) {
                remaining--
                if (remaining <= 0) { submit(); break }
            }
        }
    }

    fun leave(action: () -> Unit) {
        if (answers.any { it != -1 } && !submitted) confirmLeave = action else action()
    }
    BackHandler(enabled = !submitted) { leave(nav.back) }

    val q = paper.questions[current]
    AppScaffold(
        title = paper.title,
        crumbs = paperCrumbs(paper) + Crumb(if (practice) "Practice" else "Test"),
        nav = Nav(nav.go, nav.back, nav.home, { r -> leave { nav.crumb(r) } }, nav.replace),
        onBack = { leave(nav.back) },
        onHome = { leave(nav.home) },
        actions = {
            Surface(shape = RoundedCornerShape(50), color = if (!practice && remaining < 300) Danger else Color.White.copy(alpha = 0.18f)) {
                Row(Modifier.padding(horizontal = 10.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Timer, null, Modifier.size(16.dp), tint = Color.White)
                    Spacer(Modifier.width(4.dp))
                    Text(fmtClock(if (practice) elapsed else remaining), color = Color.White, style = MaterialTheme.typography.labelLarge)
                }
            }
        },
        bottomBar = {
            Surface(tonalElevation = 3.dp, shadowElevation = 6.dp) {
                Row(
                    Modifier.fillMaxWidth().navigationBarsPadding().padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = { goTo(current - 1) }, enabled = current > 0) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Previous") }
                    OutlinedButton(onClick = { showPalette = true }, modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Apps, null); Spacer(Modifier.width(4.dp)); Text("${answers.count { it != -1 }}/$n")
                    }
                    Button(onClick = { confirmSubmit = true }, modifier = Modifier.weight(1f)) { Text("Submit") }
                    IconButton(onClick = { goTo(current + 1) }, enabled = current < n - 1) { Icon(Icons.AutoMirrored.Filled.ArrowForward, "Next") }
                }
            }
        },
    ) { pad ->
        Column(Modifier.fillMaxSize().padding(pad)) {
            // Section jump chips for multi-subject papers
            val sections = paper.questions.mapIndexed { i, qq -> qq.subjectId to i }.distinctBy { it.first }
            if (sections.size > 1) {
                Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(horizontal = 12.dp, vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    sections.forEach { (sid, start) ->
                        FilterChip(selected = q.subjectId == sid, onClick = { goTo(start) }, label = { Text(ContentRepository.subject(sid).name) })
                    }
                }
            }
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Question ${current + 1} of $n", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f))
                    Pill("+${q.marks} marks", Success)
                    Spacer(Modifier.width(4.dp))
                    IconButton(onClick = { marked = marked.toMutableList().also { it[current] = !it[current] } }) {
                        Icon(if (marked[current]) Icons.Default.Bookmark else Icons.Default.BookmarkBorder, "Mark for review", tint = Review)
                    }
                }
                Text(q.text, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)

                val revealed = practice && answers[current] != -1
                q.options.forEachIndexed { oi, opt ->
                    val selected = answers[current] == oi
                    val (border, bg) = when {
                        revealed && oi == q.answer -> Success to Success.copy(alpha = 0.10f)
                        revealed && selected -> Danger to Danger.copy(alpha = 0.10f)
                        selected -> MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.primaryContainer
                        else -> MaterialTheme.colorScheme.outlineVariant to MaterialTheme.colorScheme.surface
                    }
                    Row(
                        Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(bg)
                            .border(BorderStroke(if (selected || (revealed && oi == q.answer)) 2.dp else 1.dp, border), RoundedCornerShape(12.dp))
                            .clickable(enabled = !revealed) {
                                answers = answers.toMutableList().also { it[current] = if (selected) -1 else oi }
                            }
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            Modifier.size(30.dp).clip(CircleShape).background(if (selected) border else MaterialTheme.colorScheme.surfaceVariant),
                            contentAlignment = Alignment.Center,
                        ) { Text("ABCD"[oi].toString(), color = if (selected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold) }
                        Spacer(Modifier.width(12.dp))
                        Text(opt, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                        if (revealed && oi == q.answer) Icon(Icons.Default.CheckCircle, null, tint = Success)
                        else if (revealed && selected) Icon(Icons.Default.Cancel, null, tint = Danger)
                    }
                }
                if (!practice && answers[current] != -1) {
                    TextButton(onClick = { answers = answers.toMutableList().also { it[current] = -1 } }) { Text("Clear response") }
                }
                if (revealed) ExplanationCard(q, answers[current])
                if (current < n - 1 && (revealed || !practice)) {
                    Button(onClick = { goTo(current + 1) }, modifier = Modifier.fillMaxWidth()) { Text(if (answers[current] == -1) "Skip" else "Save & Next") }
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }

    if (showPalette) {
        ModalBottomSheet(onDismissRequest = { showPalette = false }) {
            Column(Modifier.padding(horizontal = 20.dp).padding(bottom = 24.dp)) {
                Text("Question palette", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Legend(Success, "Answered"); Legend(Danger, "Not answered"); Legend(Review, "Marked"); Legend(MaterialTheme.colorScheme.outline, "Not visited")
                }
                Spacer(Modifier.height(12.dp))
                FlowRow(Modifier.heightIn(max = 420.dp).verticalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    for (i in 0 until n) {
                        val c = when {
                            marked[i] -> Review
                            answers[i] != -1 -> Success
                            visited[i] -> Danger
                            else -> MaterialTheme.colorScheme.outline
                        }
                        Box(
                            Modifier.size(42.dp).clip(RoundedCornerShape(10.dp)).background(c.copy(alpha = if (i == current) 1f else 0.85f))
                                .then(if (i == current) Modifier.border(3.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(10.dp)) else Modifier)
                                .clickable { goTo(i); showPalette = false },
                            contentAlignment = Alignment.Center,
                        ) { Text("${i + 1}", color = Color.White, fontWeight = FontWeight.Bold) }
                    }
                }
            }
        }
    }

    if (confirmSubmit) {
        val answered = answers.count { it != -1 }
        AlertDialog(
            onDismissRequest = { confirmSubmit = false },
            title = { Text("Submit test?") },
            text = {
                Text("Answered: $answered\nNot answered: ${n - answered}\nMarked for review: ${marked.count { it }}" +
                    if (n - answered > 0) "\n\nThere is no negative marking — consider attempting every question." else "")
            },
            confirmButton = { Button(onClick = { confirmSubmit = false; submit() }) { Text("Submit") } },
            dismissButton = { TextButton(onClick = { confirmSubmit = false }) { Text("Continue test") } },
        )
    }

    confirmLeave?.let { action ->
        AlertDialog(
            onDismissRequest = { confirmLeave = null },
            title = { Text("Leave the test?") },
            text = { Text("Your answers in this attempt will be lost. You can submit first to save your result.") },
            confirmButton = { TextButton(onClick = { confirmLeave = null; action() }) { Text("Leave", color = Danger) } },
            dismissButton = { Button(onClick = { confirmLeave = null }) { Text("Stay") } },
        )
    }
}

@Composable
private fun Legend(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(12.dp).clip(RoundedCornerShape(3.dp)).background(color))
        Spacer(Modifier.width(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun ExplanationCard(q: Question, chosen: Int?) {
    val correct = chosen == q.answer
    Surface(shape = RoundedCornerShape(14.dp), color = Saffron.copy(alpha = 0.08f), border = BorderStroke(1.dp, Saffron.copy(alpha = 0.4f))) {
        Column(Modifier.fillMaxWidth().padding(14.dp)) {
            if (chosen != null) {
                Text(
                    when { chosen == -1 -> "Not attempted"; correct -> "Correct! +${q.marks}"; else -> "Incorrect" },
                    color = when { chosen == -1 -> MaterialTheme.colorScheme.onSurfaceVariant; correct -> Success; else -> Danger },
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(4.dp))
            }
            Text("Correct answer: ${"ABCD"[q.answer]}. ${q.options[q.answer]}", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = Saffron.copy(alpha = 0.3f))
            Spacer(Modifier.height(8.dp))
            Row {
                Icon(Icons.Default.Lightbulb, null, tint = Saffron)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Detailed explanation", style = MaterialTheme.typography.labelLarge, color = Saffron)
                    Text(q.explanation, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

fun fmtClock(sec: Int): String {
    val s = sec.coerceAtLeast(0)
    return if (s >= 3600) "%d:%02d:%02d".format(s / 3600, s % 3600 / 60, s % 60) else "%02d:%02d".format(s / 60, s % 60)
}
