package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.QUIZ_FILE_REGEX
import com.nya.quiz.models.rank.RankingRepositoryImpl

class IncorrectNoteViewModel {


    fun getMyIncorrectNote(): String {
        var myProfile = RankingRepositoryImpl.profile
        var quizList = myProfile.incorrectQuiz
        var stringBuilder = StringBuilder()

        if (quizList.isEmpty()) {
            stringBuilder.append("저장된 오답 문제가 없습니다.\n")
        } else {

            val lastQuizListStr = quizList.last()
            val regex = Regex(QUIZ_FILE_REGEX)
            val formattedList = regex.findAll(lastQuizListStr).map { match ->
                val word = match.groupValues[1].trim()
                val meanings = match.groupValues[2].split(',').joinToString(", ") { it.trim() }
                "$word : $meanings"
            }.toList()

            // 5개씩 한 줄에 출력
            formattedList.chunked(5).forEach { lineList ->
                stringBuilder.append(lineList.joinToString("    ") + "\n")
            }
        }

        stringBuilder.append("\n진행한 문제 수 : ${myProfile.correctCount + myProfile.incorrectCount}\n정답률: ${myProfile.correctRate}%")

        return stringBuilder.toString()
    }

    fun deleteMyIncorrectNote() = RankingRepositoryImpl.deleteInfo(RankingRepositoryImpl.profile.userId)
}