package com.nya.quiz.views.mainview

import com.nya.quiz.viewmodels.mainViewModels.RankingViewModel

class RankingView(private val rankingViewModel: RankingViewModel) {

    fun showRanking(){
        if (rankingViewModel.setRankingData()){
            printTopRanking(rankingViewModel.getTopRank())
            printMyRank(rankingViewModel.getMyRank())
        } else{
            printErrorMessage()
        }
    }

    fun printTopRanking(topRank: List<String>){
        println("======================== T O P 5 ========================")
        for (topRankingData:String in topRank) println(topRankingData)
        println("=========================================================")
    }

    fun printMyRank(myRank: String){
        println("=======================  내 순위  ========================")
        println(myRank)
        println("=========================================================\n")
    }

    fun printErrorMessage(){
        println("랭킹 정보를 읽어오지 못했습니다.")
    }
}