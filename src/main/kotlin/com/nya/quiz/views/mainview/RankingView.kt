package com.nya.quiz.views.mainview

import com.nya.quiz.viewmodels.mainViewModels.RankingViewModel

class RankingView(private val rankingViewModel: RankingViewModel) {

    fun showRanking(){
        printTopRanking(rankingViewModel.getMyRank())
        printMyRank(rankingViewModel.getMyRank())
    }

    fun printTopRanking(rankingInfo: String){
        println("======================= T O P 5 =======================")
        println(rankingInfo)
        println("=======================================================")
    }

    fun printMyRank(myRank: String){
        println("=======================================================")
        println("  $myRank  ")
        println("=======================================================\n\n\n")
    }
}