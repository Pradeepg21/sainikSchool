package com.satya.sainikschool.ui.nav

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.satya.sainikschool.data.content.ContentRepository
import com.satya.sainikschool.data.model.ExamClass
import com.satya.sainikschool.ui.components.Crumb
import com.satya.sainikschool.ui.components.Nav
import com.satya.sainikschool.ui.screens.AdmissionScreen
import com.satya.sainikschool.ui.screens.ChapterScreen
import com.satya.sainikschool.ui.screens.ClassScreen
import com.satya.sainikschool.ui.screens.ExamScreen
import com.satya.sainikschool.ui.screens.HistoryScreen
import com.satya.sainikschool.ui.screens.HomeScreen
import com.satya.sainikschool.ui.screens.MocksScreen
import com.satya.sainikschool.ui.screens.NoteEditScreen
import com.satya.sainikschool.ui.screens.NotesScreen
import com.satya.sainikschool.ui.screens.PlannerScreen
import com.satya.sainikschool.ui.screens.PyqScreen
import com.satya.sainikschool.ui.screens.ResultScreen
import com.satya.sainikschool.ui.screens.ReviewScreen
import com.satya.sainikschool.ui.screens.SubjectScreen

/** Route builders. Paper ids contain ':' so they are URI-encoded. */
object R {
    const val HOME = "home"
    const val NOTES = "notes"
    const val PLANNER = "planner"
    const val ADMISSION = "admission"
    const val HISTORY = "history"
    fun cls(c: ExamClass) = "class/${c.id}"
    fun subject(id: String) = "subject/$id"
    fun chapter(id: String) = "chapter/$id"
    fun exam(paperId: String) = "exam/${Uri.encode(paperId)}"
    fun result(id: String) = "result/$id"
    fun review(resultId: String) = "review/r/$resultId"
    fun solutions(paperId: String) = "review/p/${Uri.encode(paperId)}"
    fun mocks(c: ExamClass) = "mocks/${c.id}"
    fun pyq(c: ExamClass) = "pyq/${c.id}"
    fun note(id: String) = "note/$id"
}

/** Shared breadcrumb prefixes. */
object Crumbs {
    val home = Crumb("Home", R.HOME)
    fun cls(c: ExamClass) = listOf(home, Crumb(if (c == ExamClass.CLASS_6) "Class 6" else "Class 9", R.cls(c)))
    fun subject(id: String): List<Crumb> {
        val s = ContentRepository.subject(id)
        return cls(s.examClass) + Crumb(s.name, R.subject(id))
    }
    fun chapter(id: String): List<Crumb> {
        val (s, c) = ContentRepository.chapter(id)
        return subject(s.id) + Crumb(c.title, R.chapter(id))
    }
}

@Composable
fun AppNavHost(startRoute: Pair<String, Int>?, navController: NavHostController = rememberNavController()) {
    val nav = remember(navController) {
        Nav(
            go = { navController.navigate(it) { launchSingleTop = true } },
            back = { if (!navController.popBackStack()) navController.navigate(R.HOME) },
            home = { navController.navigate(R.HOME) { popUpTo(R.HOME) { inclusive = true }; launchSingleTop = true } },
            crumb = { route ->
                if (route == R.HOME) navController.navigate(R.HOME) { popUpTo(R.HOME) { inclusive = true }; launchSingleTop = true }
                else navController.navigate(route) { popUpTo(R.HOME); launchSingleTop = true }
            },
            replace = { route ->
                val current = navController.currentDestination?.id
                navController.navigate(route) { if (current != null) popUpTo(current) { inclusive = true } }
            },
        )
    }

    LaunchedEffect(startRoute) { startRoute?.first?.let { if (it != R.HOME) nav.go(it) } }

    NavHost(navController, startDestination = R.HOME) {
        composable(R.HOME) { HomeScreen(nav) }
        composable("class/{cls}") { ClassScreen(ExamClass.from(it.arguments!!.getString("cls")!!), nav) }
        composable("subject/{id}") { SubjectScreen(it.arguments!!.getString("id")!!, nav) }
        composable("chapter/{id}") { ChapterScreen(it.arguments!!.getString("id")!!, nav) }
        composable("exam/{pid}") { ExamScreen(Uri.decode(it.arguments!!.getString("pid")!!), nav) }
        composable("result/{id}") { ResultScreen(it.arguments!!.getString("id")!!, nav) }
        composable("review/r/{id}") { ReviewScreen(resultId = it.arguments!!.getString("id")!!, paperId = null, nav = nav) }
        composable("review/p/{pid}") { ReviewScreen(resultId = null, paperId = Uri.decode(it.arguments!!.getString("pid")!!), nav = nav) }
        composable("mocks/{cls}") { MocksScreen(ExamClass.from(it.arguments!!.getString("cls")!!), nav) }
        composable("pyq/{cls}") { PyqScreen(ExamClass.from(it.arguments!!.getString("cls")!!), nav) }
        composable(R.NOTES) { NotesScreen(nav) }
        composable("note/{id}") { NoteEditScreen(it.arguments!!.getString("id")!!, nav) }
        composable(R.PLANNER) { PlannerScreen(nav) }
        composable(R.ADMISSION) { AdmissionScreen(nav) }
        composable(R.HISTORY) { HistoryScreen(nav) }
    }
}
