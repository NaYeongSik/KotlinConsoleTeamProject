package com.nya.quiz.models.rank

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.interfaces.rank.RankingService

class RankingModel(private val rankingModel : RankingRepositoryImpl): RankingService {

    override fun getTotalRanking(): List<QuizStat> = rankingModel.getTotalRanking()

    override fun recordRanking(id: String, score: Float): Boolean {
        TODO("Not yet implemented")
        //TODO: rankingRepository을 이용해서 데이터 쓰기
    }

    override fun deleteInfo(id: String) {
        TODO("Not yet implemented")
        //TODO: rankingRepository을 이용해서 데이터 지우기
    }
}