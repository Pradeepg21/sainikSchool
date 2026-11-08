package com.satya.sainikschool.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/** One segment of the breadcrumb trail. [route] = null means not clickable. */
data class Crumb(val label: String, val route: String? = null)

/** Navigation callbacks shared by every screen. */
class Nav(
    val go: (String) -> Unit,
    val back: () -> Unit,
    val home: () -> Unit,
    val crumb: (String) -> Unit,
    val replace: (String) -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    title: String,
    crumbs: List<Crumb>,
    nav: Nav,
    showBack: Boolean = true,
    actions: @Composable () -> Unit = {},
    floating: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    onBack: (() -> Unit)? = null,
    onHome: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                    navigationIcon = {
                        if (showBack) IconButton(onClick = { (onBack ?: nav.back)() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                        }
                    },
                    actions = {
                        actions()
                        IconButton(onClick = { (onHome ?: nav.home)() }) { Icon(Icons.Default.Home, "Home") }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                )
                if (crumbs.isNotEmpty()) Breadcrumbs(crumbs, nav)
            }
        },
        floatingActionButton = floating,
        bottomBar = bottomBar,
        containerColor = MaterialTheme.colorScheme.background,
        content = content,
    )
}

@Composable
fun Breadcrumbs(crumbs: List<Crumb>, nav: Nav) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        crumbs.forEachIndexed { i, c ->
            if (i > 0) Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight, null,
                Modifier.size(16.dp), tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
            )
            val clickable = c.route != null && i < crumbs.lastIndex
            Text(
                c.label,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = if (clickable) FontWeight.Medium else FontWeight.Bold,
                color = if (clickable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .then(if (clickable) Modifier.clickable { nav.crumb(c.route!!) } else Modifier)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
            )
        }
    }
}

@Composable
fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(text, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground, modifier = modifier.padding(top = 8.dp))
}

@Composable
fun InfoCard(modifier: Modifier = Modifier, container: Color = MaterialTheme.colorScheme.surface, onClick: (() -> Unit)? = null, content: @Composable ColumnScope.() -> Unit) {
    val colors = CardDefaults.cardColors(containerColor = container)
    val inner: @Composable ColumnScope.() -> Unit = { Column(Modifier.padding(16.dp), content = content) }
    if (onClick != null) Card(onClick = onClick, modifier = modifier.fillMaxWidth(), colors = colors, elevation = CardDefaults.cardElevation(1.dp), content = inner)
    else Card(modifier = modifier.fillMaxWidth(), colors = colors, elevation = CardDefaults.cardElevation(1.dp), content = inner)
}

@Composable
fun ListRow(icon: ImageVector, title: String, subtitle: String?, tint: Color = MaterialTheme.colorScheme.primary, trailing: (@Composable () -> Unit)? = null, onClick: () -> Unit) {
    InfoCard(onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(44.dp).clip(CircleShape).background(tint.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = tint)
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                if (subtitle != null) Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
            if (trailing != null) trailing() else Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun Bullet(text: String, color: Color = MaterialTheme.colorScheme.primary) {
    Row(Modifier.padding(vertical = 3.dp)) {
        Box(Modifier.padding(top = 8.dp).size(6.dp).clip(CircleShape).background(color))
        Spacer(Modifier.width(10.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun Pill(text: String, color: Color) {
    Text(
        text, style = MaterialTheme.typography.labelMedium, color = color,
        modifier = Modifier.clip(RoundedCornerShape(50)).background(color.copy(alpha = 0.12f)).padding(horizontal = 10.dp, vertical = 4.dp),
    )
}

/** Standard scrollable page body with 16dp gutters. */
@Composable
fun ScreenColumn(padding: PaddingValues, content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = content,
    )
}

fun subjectIcon(key: String): ImageVector = when (key) {
    "math" -> Icons.Default.Calculate
    "brain" -> Icons.Default.Psychology
    "language" -> Icons.Default.Translate
    "science" -> Icons.Default.Science
    "globe" -> Icons.Default.Public
    else -> Icons.AutoMirrored.Filled.MenuBook
}

fun subjectColor(key: String): Color = when (key) {
    "math" -> Color(0xFF1565C0)
    "brain" -> Color(0xFF6A1B9A)
    "language" -> Color(0xFF00838F)
    "science" -> Color(0xFF2E7D32)
    "globe" -> Color(0xFFE8862A)
    else -> Color(0xFF455A64)
}
