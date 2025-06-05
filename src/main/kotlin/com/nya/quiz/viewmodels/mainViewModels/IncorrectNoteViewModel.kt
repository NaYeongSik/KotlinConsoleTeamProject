package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.WORD_NOTE_REGEX
import com.nya.quiz.models.rank.UserStatRepositoryImpl

class IncorrectNoteViewModel{

    fun getMyIncorrectNote(): String{
        var myProfile = UserStatRepositoryImpl.profile
        var quizList = myProfile.incorrectQuiz
        var stringBuilder = StringBuilder()

        if (quizList.isEmpty()) {
            stringBuilder.append("저장된 오답 문제가 없습니다.\n")
        } else {

            val lastQuizListStr = quizList.last()
            //val regex = Regex("""QuizWord\(word=([^,]+), meanings=\[([^\]]*)\]\)""")
            val regex = Regex(WORD_NOTE_REGEX)
            val formattedList = regex.findAll(lastQuizListStr).map { match ->
                val word = match.groupValues[1].trim()
                val meanings = match.groupValues[2].split(',').map { it.trim() }.joinToString(", ")
                "$word : $meanings"
            }.toList()

            // 5개씩 한 줄에 출력
            formattedList.chunked(5).forEach { lineList ->
                stringBuilder.append(lineList.joinToString("    "))
                stringBuilder.append("\n")
            }
        }

        stringBuilder.append("\n진행한 문제 수 : ${myProfile.correctCount + myProfile.incorrectCount}\n정답률: ${myProfile.correctRate}%")

        return stringBuilder.toString()
    }

    fun deleteMyIncorrectNote()= UserStatRepositoryImpl.deleteInfo(UserStatRepositoryImpl.profile.userId)

}

