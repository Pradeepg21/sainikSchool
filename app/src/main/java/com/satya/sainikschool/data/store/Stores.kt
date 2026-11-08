package com.satya.sainikschool.data.store

import android.content.Context
import android.content.SharedPreferences
import com.satya.sainikschool.data.model.ExamResult
import com.satya.sainikschool.data.model.StudySchedule
import com.satya.sainikschool.data.model.UserNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

/*
 * Storage contracts. The app currently uses the on-device implementations below.
 * To move to Firestore, implement these interfaces with a Firestore-backed class
 * (e.g. collection users/{uid}/results, users/{uid}/notes, users/{uid}/schedules)
 * and swap them in [AppGraph].
 */

interface ResultRepository {
    val results: StateFlow<List<ExamResult>>
    fun save(result: ExamResult)
    fun get(id: String): ExamResult?
    fun clear()
}

interface NotesRepository {
    val notes: StateFlow<List<UserNote>>
    fun upsert(note: UserNote)
    fun delete(id: String)
}

interface ScheduleRepository {
    val schedules: StateFlow<List<StudySchedule>>
    fun upsert(schedule: StudySchedule)
    fun delete(id: String)
}

private fun SharedPreferences.readArray(key: String): JSONArray =
    runCatching { JSONArray(getString(key, "[]")) }.getOrDefault(JSONArray())

private fun <T> JSONArray.mapObjects(f: (JSONObject) -> T?): List<T> =
    (0 until length()).mapNotNull { runCatching { f(getJSONObject(it)) }.getOrNull() }

/** Keeps only the latest [MAX_RESULTS] attempts, newest first. */
class LocalResultRepository(context: Context) : ResultRepository {
    companion object { const val MAX_RESULTS = 5; private const val KEY = "results" }

    private val prefs = context.getSharedPreferences("sainik_results", Context.MODE_PRIVATE)
    private val state = MutableStateFlow(load())
    override val results = state.asStateFlow()

    private fun load(): List<ExamResult> = prefs.readArray(KEY).mapObjects { o ->
        val subj = o.getJSONObject("subjects")
        ExamResult(
            id = o.getString("id"), paperId = o.getString("paperId"), paperTitle = o.getString("paperTitle"),
            examClassId = o.getString("cls"), kind = o.getString("kind"), timestamp = o.getLong("ts"),
            score = o.getInt("score"), totalMarks = o.getInt("total"), correct = o.getInt("correct"),
            wrong = o.getInt("wrong"), skipped = o.getInt("skipped"), timeTakenSec = o.getInt("time"),
            subjectScores = subj.keys().asSequence().associateWith { k -> subj.getJSONArray(k).let { it.getInt(0) to it.getInt(1) } },
            answers = o.getJSONArray("answers").let { a -> (0 until a.length()).map { a.getInt(it) } },
        )
    }

    private fun persist(list: List<ExamResult>) {
        val arr = JSONArray()
        list.forEach { r ->
            arr.put(JSONObject().apply {
                put("id", r.id); put("paperId", r.paperId); put("paperTitle", r.paperTitle); put("cls", r.examClassId)
                put("kind", r.kind); put("ts", r.timestamp); put("score", r.score); put("total", r.totalMarks)
                put("correct", r.correct); put("wrong", r.wrong); put("skipped", r.skipped); put("time", r.timeTakenSec)
                put("subjects", JSONObject().apply { r.subjectScores.forEach { (k, v) -> put(k, JSONArray().put(v.first).put(v.second)) } })
                put("answers", JSONArray().apply { r.answers.forEach { put(it) } })
            })
        }
        prefs.edit().putString(KEY, arr.toString()).apply()
        state.value = list
    }

    override fun save(result: ExamResult) = persist((listOf(result) + state.value).sortedByDescending { it.timestamp }.take(MAX_RESULTS))
    override fun get(id: String) = state.value.firstOrNull { it.id == id }
    override fun clear() = persist(emptyList())
}

class LocalNotesRepository(context: Context) : NotesRepository {
    private val prefs = context.getSharedPreferences("sainik_notes", Context.MODE_PRIVATE)
    private val state = MutableStateFlow(load())
    override val notes = state.asStateFlow()

    private fun load() = prefs.readArray("notes").mapObjects { o ->
        UserNote(o.getString("id"), o.getString("title"), o.getString("body"), o.optString("tag", "General"), o.getLong("ts"), o.optBoolean("pinned"))
    }.sortedWith(compareByDescending<UserNote> { it.pinned }.thenByDescending { it.updatedAt })

    private fun persist(list: List<UserNote>) {
        val arr = JSONArray()
        list.forEach { n -> arr.put(JSONObject().put("id", n.id).put("title", n.title).put("body", n.body).put("tag", n.subjectTag).put("ts", n.updatedAt).put("pinned", n.pinned)) }
        prefs.edit().putString("notes", arr.toString()).apply()
        state.value = list.sortedWith(compareByDescending<UserNote> { it.pinned }.thenByDescending { it.updatedAt })
    }

    override fun upsert(note: UserNote) = persist(state.value.filterNot { it.id == note.id } + note)
    override fun delete(id: String) = persist(state.value.filterNot { it.id == id })
}

class LocalScheduleRepository(context: Context) : ScheduleRepository {
    private val prefs = context.getSharedPreferences("sainik_schedules", Context.MODE_PRIVATE)
    private val state = MutableStateFlow(load())
    override val schedules = state.asStateFlow()

    private fun load() = prefs.readArray("schedules").mapObjects { o ->
        StudySchedule(
            o.getString("id"), o.getString("title"), o.optString("tag", "General"), o.getInt("h"), o.getInt("m"),
            o.optInt("dur", 60), o.getJSONArray("days").let { a -> (0 until a.length()).map { a.getInt(it) }.toSet() }, o.optBoolean("on", true),
        )
    }.sortedBy { it.hour * 60 + it.minute }

    private fun persist(list: List<StudySchedule>) {
        val arr = JSONArray()
        list.forEach { s ->
            arr.put(JSONObject().put("id", s.id).put("title", s.title).put("tag", s.subjectTag).put("h", s.hour).put("m", s.minute)
                .put("dur", s.durationMinutes).put("days", JSONArray().apply { s.days.forEach { put(it) } }).put("on", s.enabled))
        }
        prefs.edit().putString("schedules", arr.toString()).apply()
        state.value = list.sortedBy { it.hour * 60 + it.minute }
    }

    override fun upsert(schedule: StudySchedule) = persist(state.value.filterNot { it.id == schedule.id } + schedule)
    override fun delete(id: String) = persist(state.value.filterNot { it.id == id })
}

/** Simple user preferences (selected class, student name). */
class Prefs(context: Context) {
    private val prefs = context.getSharedPreferences("sainik_prefs", Context.MODE_PRIVATE)
    private val _classId = MutableStateFlow(prefs.getString("cls", null))
    val classId = _classId.asStateFlow()
    private val _name = MutableStateFlow(prefs.getString("name", "") ?: "")
    val name = _name.asStateFlow()

    fun setClass(id: String) { prefs.edit().putString("cls", id).apply(); _classId.value = id }
    fun setName(n: String) { prefs.edit().putString("name", n).apply(); _name.value = n }
}

/** Manual dependency container — replace Local* with Firestore implementations later. */
object AppGraph {
    lateinit var results: ResultRepository; private set
    lateinit var notes: NotesRepository; private set
    lateinit var schedules: ScheduleRepository; private set
    lateinit var prefs: Prefs; private set

    fun init(context: Context) {
        val app = context.applicationContext
        results = LocalResultRepository(app)
        notes = LocalNotesRepository(app)
        schedules = LocalScheduleRepository(app)
        prefs = Prefs(app)
    }
}
