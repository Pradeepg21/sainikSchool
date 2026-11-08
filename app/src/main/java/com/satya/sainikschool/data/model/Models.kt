package com.satya.sainikschool.data.model

/** The two AISSEE entry points. Class 6 entry is taken by students in Class 5; Class 9 entry by students in Class 8. */
enum class ExamClass(val id: String, val title: String, val subtitle: String) {
    CLASS_6("c6", "Class 6 Entry", "For students studying in Class 5 (age 10–12)"),
    CLASS_9("c9", "Class 9 Entry", "For students studying in Class 8 (age 13–15)");

    companion object {
        fun from(id: String): ExamClass = entries.first { it.id == id }
    }
}

/** A subject (section) of the AISSEE paper, with its official weightage. */
data class Subject(
    val id: String,
    val name: String,
    val examClass: ExamClass,
    val questionsInExam: Int,
    val marksPerQuestion: Int,
    val iconKey: String,
    val chapters: List<Chapter>,
) {
    val totalMarks get() = questionsInExam * marksPerQuestion
}

/** A chapter with study notes. Practice questions come from [QuestionBank]. */
data class Chapter(
    val id: String,
    val title: String,
    val summary: String,
    val notes: List<NoteSection>,
    /** Key into [com.satya.sainikschool.data.content.Generators] for unlimited auto-generated practice, or null. */
    val genKey: String? = null,
)

data class NoteSection(
    val heading: String,
    val points: List<String>,
    val example: String? = null,
)

/** A single MCQ. [answer] is the index into [options]. */
data class Question(
    val id: String,
    val subjectId: String,
    val chapterId: String,
    val text: String,
    val options: List<String>,
    val answer: Int,
    val explanation: String,
    val marks: Int = 1,
)

/** A ready-to-attempt paper (mock, chapter quiz or year-wise set). */
data class Paper(
    val id: String,
    val title: String,
    val examClass: ExamClass,
    val durationMinutes: Int,
    val questions: List<Question>,
    val kind: PaperKind,
) {
    val totalMarks get() = questions.sumOf { it.marks }
}

enum class PaperKind(val label: String) {
    FULL_MOCK("Full Mock Test"),
    SUBJECT_MOCK("Subject Test"),
    CHAPTER_QUIZ("Chapter Practice"),
    PREVIOUS_YEAR("Previous Year Practice"),
}

/** Saved result of an attempt (only the latest 5 are kept). */
data class ExamResult(
    val id: String,
    val paperId: String,
    val paperTitle: String,
    val examClassId: String,
    val kind: String,
    val timestamp: Long,
    val score: Int,
    val totalMarks: Int,
    val correct: Int,
    val wrong: Int,
    val skipped: Int,
    val timeTakenSec: Int,
    val subjectScores: Map<String, Pair<Int, Int>>, // subject name -> (scored, max)
    val answers: List<Int>, // chosen option per question, -1 = skipped
) {
    val percent get() = if (totalMarks == 0) 0f else score * 100f / totalMarks
}

data class UserNote(
    val id: String,
    val title: String,
    val body: String,
    val subjectTag: String,
    val updatedAt: Long,
    val pinned: Boolean = false,
)

/** A recurring study session. [days] uses java.util.Calendar day constants (1=Sunday..7=Saturday). */
data class StudySchedule(
    val id: String,
    val title: String,
    val subjectTag: String,
    val hour: Int,
    val minute: Int,
    val durationMinutes: Int,
    val days: Set<Int>,
    val enabled: Boolean,
) {
    val requestCode get() = id.hashCode()
}
