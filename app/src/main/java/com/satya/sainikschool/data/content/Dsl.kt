package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.Chapter
import com.satya.sainikschool.data.model.ExamClass
import com.satya.sainikschool.data.model.NoteSection
import com.satya.sainikschool.data.model.Question
import com.satya.sainikschool.data.model.Subject

/** Tiny builder DSL so syllabus content reads like a book, not like code. */
class SubjectBuilder(
    private val id: String,
    private val name: String,
    private val examClass: ExamClass,
    private val count: Int,
    private val marks: Int,
    private val icon: String,
) {
    private val chapters = mutableListOf<Chapter>()
    val questions = mutableListOf<Question>()

    fun chapter(key: String, title: String, summary: String, gen: String? = null, body: ChapterBuilder.() -> Unit) {
        val cid = "${id}_$key"
        val b = ChapterBuilder(id, cid, marks).apply(body)
        chapters += Chapter(cid, title, summary, b.sections, gen)
        questions += b.questions
    }

    fun build() = Subject(id, name, examClass, count, marks, icon, chapters)
}

class ChapterBuilder(private val subjectId: String, private val chapterId: String, private val marks: Int) {
    val sections = mutableListOf<NoteSection>()
    val questions = mutableListOf<Question>()

    fun note(heading: String, vararg points: String, example: String? = null) {
        sections += NoteSection(heading, points.toList(), example)
    }

    /** MCQ: [ans] is 0-based index of the correct option. */
    fun q(text: String, a: String, b: String, c: String, d: String, ans: Int, why: String) {
        questions += Question("${chapterId}_q${questions.size + 1}", subjectId, chapterId, text, listOf(a, b, c, d), ans, why, marks)
    }
}

fun subject(id: String, name: String, cls: ExamClass, count: Int, marks: Int, icon: String, body: SubjectBuilder.() -> Unit): Pair<com.satya.sainikschool.data.model.Subject, List<Question>> {
    val b = SubjectBuilder(id, name, cls, count, marks, icon).apply(body)
    return b.build() to b.questions
}
