package com.nya.quiz.models

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.interfaces.Solvable

data class Quiz(
    val totalQuestions: Int,
    val timeLimitSec: Int,
    var isFinished: Boolean = false
)

data class QuizWord(
    val word: String,
    val meanings: List<String>
)

object QuizStatMapper {
    fun filterQuizStat(data: String): QuizStat {
        var dataList = data.split("|")
        var userId = dataList[0].trim()
        var correctRate = dataList[1].trim().toFloat()
        var correctCount = dataList[2].trim().toInt()
        var incorrectCount = dataList[3].trim().toInt()

        val incorrectQuiz = if (dataList.size > 4) {
            dataList.subList(4, dataList.size).map { it.trim() }.filter { it.isNotEmpty() }
        } else {
            emptyList()
        }
        return QuizStat(userId, correctRate, correctCount, incorrectCount, incorrectQuiz)
    }
}