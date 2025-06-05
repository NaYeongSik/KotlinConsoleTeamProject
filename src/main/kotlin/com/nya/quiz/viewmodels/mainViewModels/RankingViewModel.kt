package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.models.rank.RankingModel
import com.nya.quiz.models.rank.UserStatRepositoryImpl

class RankingViewModel(){
    private val rankingModel = RankingModel()

    private var sortedRankList: List<QuizStat> = emptyList()

    private fun requestTotalRanking() = rankingModel.getTotalRanking()

    fun getMyRank(): String = getMyRankingData(UserStatRepositoryImpl.profile.userId)

    fun getTopRank(): List<String>{
        val topRankList = mutableListOf<String>()
        var topRankData = sortedRankList.take(5)
        topRankData.forEachIndexed { index, data -> if (data.correctRate > 0 )topRankList.add(convertToRankForm(data,index))}
        return topRankList
    }

    fun setRankingData(): Boolean {
        var totalData = requestTotalRanking()
        sortedRankList = sortRanking(totalData)
        return if (sortedRankList.isNotEmpty()) true else false
    }

    private fun convertToRankForm(data: QuizStat,index: Int): String{
        var total = data.correctCount + data.incorrectCount
        var correctRate = data.correctRate
        var userId = data.userId
        var rank = if (total > 0) index+1 else "-"

        return " ${rank} 위 ${userId} 님, 정답률: ${correctRate}%, 진행한 문제: ${total}문제"
    }

    private fun sortRanking(data: List<QuizStat>): List<QuizStat>{
        return data.sortedWith(
            compareByDescending<QuizStat> { it.correctRate }   // 주 정렬: 정답률 내림차순
                .thenByDescending { it.correctCount }           // 보조 정렬 1: 맞힌 개수 내림차순
                .thenBy { it.userId }                           // 보조 정렬 2: userId 오름차순
        )
    }

    private fun getMyRankingData(userId: String): String{
        var rank = sortedRankList.indexOfFirst { it.userId == userId }
        return if (rank!=-1)convertToRankForm(sortedRankList[rank],rank) else "  기록이 존재하지 않습니다."
    }


}