package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.models.rank.RankingRepository

class IncorrectNoteViewModel{

    private val rankingRepository = RankingRepository

    fun getMyIncorrectNote(): String{
        var myProfile = rankingRepository.profile
        var quizList = myProfile.incorrectQuiz
        var stringBuilder = StringBuilder()

        for (quiz:String in quizList){
            stringBuilder.append(quiz)
            stringBuilder.append("\n")
        }
        stringBuilder.append("\n진행한 문제 수 : ${myProfile.correctCount + myProfile.incorrectCount}\n정답률: ${myProfile.correctRate * 100}%")

        return stringBuilder.toString()
    }

    fun deleteMyIncorrectNote(): Boolean{
        return rankingRepository.deleteInfo(rankingRepository.profile.userId)
    }

}

