package com.nya.quiz.models.quiz

import com.nya.quiz.commons.quizWordRegex
import com.nya.quiz.file.IncorrectNoteFileManager
import com.nya.quiz.file.QuizFileManager
import com.nya.quiz.interfaces.QuizRepository

/** viewModel에서 파일입출력하는 함수를 Repo로 이동 **/
object QuizRepositoryImpl: QuizRepository {

    private val quizFileManager =  QuizFileManager()
    private val incorrectNoteFileManager = IncorrectNoteFileManager()

    override suspend fun loadQuizWords(): List<QuizWord> {
        val lines = quizFileManager.readFile() ?: emptyList()
        return lines.mapNotNull { line ->
            val parts = line.split('\t')
            if (parts.size < 3) return@mapNotNull null
            val word = parts[1].trim()
            val meanings = parts[2]
                .split(',')
                .map { it.replace(Regex("\\([^)]*\\)"), "").trim() }
            QuizWord(word, meanings)
        }
    }


    override suspend fun loadIncorrectNoteQuizWords(userId: String): List<QuizWord> {
        val lines = incorrectNoteFileManager.readFile() ?: emptyList()

        val line = lines.firstOrNull { it.startsWith("$userId|") } ?: return emptyList()

        val parts = line.split('|')
        if (parts.size < 5) return emptyList()

        val incorrectQuizStr = parts.subList(4, parts.size).joinToString("|")
        val regex = quizWordRegex()

        return regex.findAll(incorrectQuizStr).mapNotNull { match ->
            val word = match.groupValues[1].trim()
            val meaningsStr = match.groupValues[2]
            val meanings = meaningsStr.split(',')
                .map { it.trim() }
                .filter { it.isNotEmpty() }
            QuizWord(word, meanings)
        }.toList()
    }
}