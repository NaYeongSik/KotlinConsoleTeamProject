package com.nya.quiz.commons

object DataMapper {

    fun transToQuizStat(data: String): QuizStat{
        var dataList = data.split("|")
        var userId = dataList[0].trim()
        var correctRate = dataList[1].trim().toFloat()
        var correctCount = dataList[2].trim().toInt()
        var incorrectCount = dataList[3].trim().toInt()

        val incorrectQuiz = if (dataList.size > 4) {
            dataList.subList(4, dataList.size).map { it.trim() }.filter { it.isNotEmpty() }
        } else {
            emptyList<String>()
        }
        return QuizStat(userId, correctRate, correctCount, incorrectCount, incorrectQuiz)
    }
}