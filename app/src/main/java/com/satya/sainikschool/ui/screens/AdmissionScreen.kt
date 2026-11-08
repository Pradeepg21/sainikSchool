package com.satya.sainikschool.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.satya.sainikschool.data.content.AdmissionInfo
import com.satya.sainikschool.data.content.ContentRepository
import com.satya.sainikschool.data.model.ExamClass
import com.satya.sainikschool.ui.components.AppScaffold
import com.satya.sainikschool.ui.components.Bullet
import com.satya.sainikschool.ui.components.Crumb
import com.satya.sainikschool.ui.components.InfoCard
import com.satya.sainikschool.ui.components.Nav
import com.satya.sainikschool.ui.components.ScreenColumn
import com.satya.sainikschool.ui.components.subjectColor
import com.satya.sainikschool.ui.nav.Crumbs
import com.satya.sainikschool.ui.nav.R

private val tabs = listOf("Overview", "Eligibility", "Exam Pattern", "Syllabus", "Admission Process", "Dates & Fees", "Reservation", "Schools", "Tips & FAQ")

@Composable
fun AdmissionScreen(nav: Nav) {
    var tab by rememberSaveable { mutableIntStateOf(0) }
    AppScaffold("Syllabus & Admission", listOf(Crumbs.home, Crumb("Syllabus & Admission")), nav) { pad ->
        Column(Modifier.fillMaxSize().padding(pad)) {
            ScrollableTabRow(selectedTabIndex = tab, edgePadding = 8.dp) {
                tabs.forEachIndexed { i, t -> Tab(selected = tab == i, onClick = { tab = i }, text = { Text(t) }) }
            }
            ScreenColumn(androidx.compose.foundation.layout.PaddingValues(0.dp)) {
                when (tab) {
                    0 -> Overview()
                    1 -> AdmissionInfo.eligibility.forEach { Block(it) }
                    2 -> Pattern()
                    3 -> Syllabus(nav)
                    4 -> Process()
                    5 -> { Block(AdmissionInfo.dates); Block(AdmissionInfo.fees); Block(AdmissionInfo.documents) }
                    6 -> { Block(AdmissionInfo.reservation); Block(AdmissionInfo.qualifying) }
                    7 -> Schools()
                    else -> Faq()
                }
                Text("Information last reviewed: ${AdmissionInfo.LAST_REVIEWED}. Always verify with the official NTA Information Bulletin.",
                    style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun Block(b: AdmissionInfo.InfoBlock) {
    InfoCard {
        Text(b.title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(6.dp))
        b.lines.forEach { Bullet(it) }
    }
}

@Composable
private fun Links() {
    val uri = LocalUriHandler.current
    InfoCard(container = MaterialTheme.colorScheme.primaryContainer) {
        Text("Official websites", style = MaterialTheme.typography.titleMedium)
        listOf(
            "NTA — AISSEE application, admit card, result" to AdmissionInfo.OFFICIAL_NTA,
            "Sainik Schools Society" to AdmissionInfo.OFFICIAL_SOCIETY,
            "e-Counselling portal" to AdmissionInfo.OFFICIAL_COUNSELLING,
        ).forEach { (label, url) ->
            OutlinedButton(onClick = { runCatching { uri.openUri(url) } }, modifier = Modifier.fillMaxWidth().padding(top = 6.dp)) {
                Text(label, Modifier.weight(1f)); Icon(Icons.AutoMirrored.Filled.OpenInNew, null)
            }
        }
    }
}

@Composable
private fun Overview() {
    Block(AdmissionInfo.overview)
    Links()
}

@Composable
private fun Pattern() {
    listOf(
        Triple("Class 6 entry", AdmissionInfo.class6Pattern, AdmissionInfo.class6PatternNotes),
        Triple("Class 9 entry", AdmissionInfo.class9Pattern, AdmissionInfo.class9PatternNotes),
    ).forEach { (title, rows, notes) ->
        InfoCard {
            Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(8.dp))
            Row { listOf("Subject" to 2f, "Qs" to 1f, "Marks/Q" to 1f, "Total" to 1f).forEach { (h, w) -> Text(h, Modifier.weight(w), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge) } }
            HorizontalDivider(Modifier.padding(vertical = 6.dp))
            rows.forEach { r ->
                Row(Modifier.padding(vertical = 3.dp)) {
                    Text(r.subject, Modifier.weight(2f)); Text("${r.questions}", Modifier.weight(1f)); Text("${r.marksEach}", Modifier.weight(1f)); Text("${r.total}", Modifier.weight(1f))
                }
            }
            HorizontalDivider(Modifier.padding(vertical = 6.dp))
            Row {
                Text("Total", Modifier.weight(2f), fontWeight = FontWeight.Bold); Text("${rows.sumOf { it.questions }}", Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f)); Text("${rows.sumOf { it.total }}", Modifier.weight(1f), fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(8.dp))
            notes.forEach { Bullet(it) }
        }
    }
    Block(AdmissionInfo.qualifying)
}

@Composable
private fun Syllabus(nav: Nav) {
    var cls by rememberSaveable { mutableStateOf(ExamClass.CLASS_6.id) }
    SingleChoiceSegmentedButtonRow(Modifier.fillMaxWidth()) {
        ExamClass.entries.forEachIndexed { i, c ->
            SegmentedButton(selected = cls == c.id, onClick = { cls = c.id }, shape = SegmentedButtonDefaults.itemShape(i, 2)) { Text(c.title) }
        }
    }
    ContentRepository.subjectsFor(ExamClass.from(cls)).forEach { s ->
        var open by remember(s.id) { mutableStateOf(false) }
        InfoCard(onClick = { open = !open }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(s.name, style = MaterialTheme.typography.titleMedium, color = subjectColor(s.iconKey))
                    Text("${s.chapters.size} topics • ${s.totalMarks} marks", style = MaterialTheme.typography.bodySmall)
                }
                Icon(if (open) Icons.Default.ExpandLess else Icons.Default.ExpandMore, null)
            }
            if (open) {
                Spacer(Modifier.height(6.dp))
                s.chapters.forEachIndexed { i, c ->
                    Text("${i + 1}. ${c.title}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 6.dp))
                    Text(c.summary, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(Modifier.height(8.dp))
                OutlinedButton(onClick = { nav.go(R.subject(s.id)) }) { Text("Study ${s.name}") }
            }
        }
    }
}

@Composable
private fun Process() {
    InfoCard {
        Text("Step-by-step admission process", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(6.dp))
        AdmissionInfo.process.forEach { step ->
            Row(Modifier.padding(vertical = 5.dp)) {
                Text(step, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
    Block(AdmissionInfo.documents)
    Links()
}

@Composable
private fun Schools() {
    InfoCard {
        Text("${AdmissionInfo.schools.size} established Sainik Schools", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Text("Plus new Sainik Schools in partnership mode — see the full current list on the Sainik Schools Society website.", style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(8.dp))
        AdmissionInfo.schools.groupBy { it.state }.toSortedMap().forEach { (state, list) ->
            Text(state, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(top = 8.dp))
            list.forEach { Text("• ${it.name}", style = MaterialTheme.typography.bodyMedium) }
        }
    }
    Links()
}

@Composable
private fun Faq() {
    InfoCard {
        Text("Preparation tips", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        AdmissionInfo.prepTips.forEach { Bullet(it) }
    }
    AdmissionInfo.faqs.forEach { f ->
        var open by remember(f.q) { mutableStateOf(false) }
        InfoCard(onClick = { open = !open }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(f.q, Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.width(8.dp))
                Icon(if (open) Icons.Default.ExpandLess else Icons.Default.ExpandMore, null)
            }
            if (open) Text(f.a, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 6.dp))
        }
    }
}
