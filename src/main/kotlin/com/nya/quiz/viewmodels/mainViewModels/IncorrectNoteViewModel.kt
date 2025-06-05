package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.quizWordRegex
import com.nya.quiz.models.rank.RankingRepositoryImpl

/** RankingRepositoryImpl가 Object라 바로 호출 **/


class IncorrectNoteViewModel{

//    private val rankingRepository = RankingRepositoryImpl

    fun getMyIncorrectNote(): String{
        // RankingRepositoryImpl 가 object 이기 때문
       // var myProfile = rankingRepository.profile
        var myProfile = RankingRepositoryImpl.profile
        var quizList = myProfile.incorrectQuiz
        var stringBuilder = StringBuilder()

        if (quizList.isEmpty()) {
            stringBuilder.append("저장된 오답 문제가 없습니다.\n")
        } else {

            val lastQuizListStr = quizList.last()
            // Regex를 함수로
            val regex = quizWordRegex()
            val formattedList = regex.findAll(lastQuizListStr).map { match ->
                val word = match.groupValues[1].trim()
                val meanings = match.groupValues[2].split(',').map { it.trim() }.joinToString(", ")
                "$word : $meanings"
            }.toList()
            /************* with 사용 ***************/
            // 5개씩 한 줄에 출력
            formattedList.chunked(5).forEach { lineList ->
                // with 함수 사용
                with(stringBuilder){
                    append(lineList.joinToString("    "))
                    appendLine() // 자동으로 개행문자 추가, 코틀린에서는 이를 권장
                }
//                stringBuilder.append(lineList.joinToString("    "))
//                stringBuilder.append("\n")
            }
        }

        stringBuilder.append("\n진행한 문제 수 : ${myProfile.correctCount + myProfile.incorrectCount}\n정답률: ${myProfile.correctRate}%")

        return stringBuilder.toString()
    }

//    fun deleteMyIncorrectNote(): Boolean{
//        return rankingRepository.deleteInfo(rankingRepository.profile.userId)
//    }
    /**** 1줄로 표현과 RankingRepositoryImpl 가 Object 여서 바로 호출하는 방법 선택 ****/
    fun deleteMyIncorrectNote() = RankingRepositoryImpl.deleteInfo(RankingRepositoryImpl.profile.userId)

}

