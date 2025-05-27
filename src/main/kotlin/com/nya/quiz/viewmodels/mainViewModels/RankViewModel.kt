package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.models.rank.RankingServiceImpl

class RankingViewModel(private val rankingService: RankingServiceImpl){

    private fun requestRanking(userId: String){
        rankingService.getMyRank(userId)
    }

    fun getMyRank(): String{

        return "testtesttesttest\ntesttesttesttesttest\ntesttesttesttesttesttest"
    }

}