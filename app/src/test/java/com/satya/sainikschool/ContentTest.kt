package com.satya.sainikschool

import com.satya.sainikschool.data.content.ContentRepository
import com.satya.sainikschool.data.content.Generators
import com.satya.sainikschool.data.model.ExamClass
import com.satya.sainikschool.data.model.Question
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ContentTest {

    private fun check(q: Question) {
        assertEquals("4 options: ${q.id} ${q.text}", 4, q.options.size)
        assertEquals("distinct options: ${q.id} ${q.options}", 4, q.options.toSet().size)
        assertTrue("answer index: ${q.id}", q.answer in 0..3)
        assertTrue("explanation: ${q.id}", q.explanation.isNotBlank())
        assertTrue("no padded distractor: ${q.id} ${q.options}", q.options.none { it.endsWith(")") && it.contains(" (") && it.substringAfterLast(" (").dropLast(1).all(Char::isDigit) })
    }

    @Test fun everyGeneratorProducesValidQuestions() {
        val keys = ContentRepository.subjects.flatMap { s -> s.chapters.mapNotNull { c -> c.genKey?.let { Triple(it, s, c) } } }
        assertTrue(keys.isNotEmpty())
        keys.forEach { (key, s, c) ->
            assertTrue("unknown generator $key", Generators.has(key))
            repeat(2000) { seed -> check(Generators.generate(key, s.id, c.id, seed.toLong(), 1)) }
        }
    }

    @Test fun curatedQuestionsAreValid() {
        ContentRepository.subjects.forEach { s ->
            s.chapters.forEach { c -> ContentRepository.paper("chap:${c.id}:1").questions.forEach(::check) }
        }
    }

    @Test fun fullMocksFollowOfficialPattern() {
        for (n in 1..ContentRepository.MOCKS_PER_CLASS) {
            val p6 = ContentRepository.paper(ContentRepository.mockId(ExamClass.CLASS_6, n))
            assertEquals(125, p6.questions.size); assertEquals(300, p6.totalMarks)
            val p9 = ContentRepository.paper(ContentRepository.mockId(ExamClass.CLASS_9, n))
            assertEquals(150, p9.questions.size); assertEquals(400, p9.totalMarks)
            (p6.questions + p9.questions).forEach(::check)
            assertEquals("no duplicate questions", p6.questions.size, p6.questions.map { it.id }.toSet().size)
        }
    }

    @Test fun previousYearPapersExistForEverySubjectAndYear() {
        ContentRepository.subjects.forEach { s ->
            ContentRepository.previousYears.forEach { y ->
                val p = ContentRepository.paper(ContentRepository.yearId(s.id, y))
                assertEquals("${s.id} $y", s.questionsInExam, p.questions.size)
                assertEquals(p.questions.map { it.text }, ContentRepository.paper(p.id).questions.map { it.text }) // deterministic
            }
        }
    }
}
