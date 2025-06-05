package com.nya.quiz.commons

/** Mapper를 사용하여 객체를 리턴할수 있도록 함 **/

// 오브젝트로 하는것이 좋은지 함수로만 두는것이 좋은지 궁금합니다.
object QuizStatMapper {}

    fun convertToQuizStat(data: String): QuizStat {
        val dataList = data.split("|")
        val userId = dataList[0].trim()
        val correctRate = dataList[1].trim().toFloat()
        val correctCount = dataList[2].trim().toInt()
        val incorrectCount = dataList[3].trim().toInt()

        val incorrectQuiz = if (dataList.size > 4) {
            dataList.subList(4, dataList.size)
                .map { it.trim() }
                .filter { it.isNotEmpty() }
        } else {
            emptyList()
        }
        return QuizStat(userId, correctRate, correctCount, incorrectCount, incorrectQuiz)
    }
