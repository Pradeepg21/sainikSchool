package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.Chapter
import com.satya.sainikschool.data.model.ExamClass
import com.satya.sainikschool.data.model.Paper
import com.satya.sainikschool.data.model.PaperKind
import com.satya.sainikschool.data.model.Question
import com.satya.sainikschool.data.model.Subject
import kotlin.random.Random

/**
 * Single source of study content. Papers are described by an id that encodes how to rebuild
 * them deterministically, so a saved result can always re-open its exact paper for review:
 *  - chap:<chapterId>:<seed>
 *  - subj:<subjectId>:<seed>
 *  - mock:<classId>:<number>
 *  - pyq:<subjectId>:<year>
 *
 * When Firestore is configured, remote papers/questions can be merged in here.
 */
object ContentRepository {

    private val all = listOf(
        class6Math, class6Intelligence, class6Language, class6GK,
        class9Math, class9Intelligence, class9English, class9Science, class9Social,
    )

    val subjects: List<Subject> = all.map { it.first }
    private val curatedByChapter: Map<String, List<Question>> = all.flatMap { it.second }.groupBy { it.chapterId }
    private val curatedBySubject: Map<String, List<Question>> = all.flatMap { it.second }.groupBy { it.subjectId }

    /** Years offered in the "Previous Years" section (last 10 exam cycles). */
    val previousYears = (2016..2025).toList().reversed()

    const val MOCKS_PER_CLASS = 10

    fun subjectsFor(cls: ExamClass) = subjects.filter { it.examClass == cls }
    fun subject(id: String) = subjects.first { it.id == id }
    fun chapter(id: String): Pair<Subject, Chapter> {
        val s = subjects.first { sub -> sub.chapters.any { it.id == id } }
        return s to s.chapters.first { it.id == id }
    }

    fun curatedCount(chapterId: String) = curatedByChapter[chapterId]?.size ?: 0
    fun totalCurated() = curatedByChapter.values.sumOf { it.size }
    fun chapterCount() = subjects.sumOf { it.chapters.size }

    fun examDuration(cls: ExamClass) = if (cls == ExamClass.CLASS_6) 150 else 180

    // ---------- paper builders ----------

    fun paper(id: String): Paper {
        val p = id.split(":")
        return when (p[0]) {
            "chap" -> chapterPaper(p[1], p[2].toLong())
            "subj" -> subjectPaper(p[1], p[2].toLong(), id)
            "mock" -> mockPaper(ExamClass.from(p[1]), p[2].toInt())
            "pyq" -> yearPaper(p[1], p[2].toInt())
            else -> error("Unknown paper $id")
        }
    }

    fun newChapterPaperId(chapterId: String) = "chap:$chapterId:${System.currentTimeMillis() % 1_000_000_007}"
    fun newSubjectPaperId(subjectId: String) = "subj:$subjectId:${System.currentTimeMillis() % 1_000_000_007}"
    fun mockId(cls: ExamClass, n: Int) = "mock:${cls.id}:$n"
    fun yearId(subjectId: String, year: Int) = "pyq:$subjectId:$year"

    private fun chapterPaper(chapterId: String, seed: Long): Paper {
        val (s, c) = chapter(chapterId)
        val r = Random(seed)
        val curated = curatedByChapter[chapterId].orEmpty().shuffled(r)
        val qs = curated.toMutableList()
        if (Generators.has(c.genKey)) {
            var i = 0
            while (qs.size < 10) qs += Generators.generate(c.genKey!!, s.id, c.id, seed * 100 + i++, s.marksPerQuestion)
        }
        return Paper("chap:$chapterId:$seed", c.title, s.examClass, maxOf(5, qs.size), qs.shuffled(r), PaperKind.CHAPTER_QUIZ)
    }

    /** Picks [count] questions for a subject: curated ones first, topped up with generated ones. */
    private fun subjectQuestions(s: Subject, count: Int, seed: Long): List<Question> {
        val r = Random(seed)
        val curated = curatedBySubject[s.id].orEmpty().shuffled(r)
        val genChapters = s.chapters.filter { Generators.has(it.genKey) }
        if (genChapters.isEmpty()) return curated.take(count)
        val curatedTake = minOf(curated.size, count * 2 / 5)
        val out = curated.take(curatedTake).toMutableList()
        var i = 0
        val order = genChapters.shuffled(r)
        while (out.size < count) {
            val c = order[i % order.size]
            out += Generators.generate(c.genKey!!, s.id, c.id, seed * 1000 + i, s.marksPerQuestion)
            i++
        }
        return out.shuffled(r)
    }

    private fun subjectPaper(subjectId: String, seed: Long, id: String): Paper {
        val s = subject(subjectId)
        val qs = subjectQuestions(s, s.questionsInExam, seed)
        val minutes = examDuration(s.examClass) * qs.size / subjectsFor(s.examClass).sumOf { it.questionsInExam }
        return Paper(id, "${s.name} — Sectional Test", s.examClass, maxOf(10, minutes), qs, PaperKind.SUBJECT_MOCK)
    }

    private fun mockPaper(cls: ExamClass, n: Int): Paper {
        // Keep sections in exam order (subject by subject), like the real OMR paper.
        val qs = subjectsFor(cls).flatMap { subjectQuestions(it, it.questionsInExam, cls.ordinal * 10_000L + n * 97L) }
        return Paper(mockId(cls, n), "AISSEE Full Mock Test $n", cls, examDuration(cls), qs, PaperKind.FULL_MOCK)
    }

    private fun yearPaper(subjectId: String, year: Int): Paper {
        val s = subject(subjectId)
        val qs = subjectQuestions(s, s.questionsInExam, year * 7919L + subjectId.hashCode())
        val minutes = examDuration(s.examClass) * qs.size / subjectsFor(s.examClass).sumOf { it.questionsInExam }
        return Paper(yearId(subjectId, year), "${s.name} — $year Pattern Paper", s.examClass, maxOf(10, minutes), qs, PaperKind.PREVIOUS_YEAR)
    }

    fun subjectNameOf(q: Question) = subject(q.subjectId).name
}
