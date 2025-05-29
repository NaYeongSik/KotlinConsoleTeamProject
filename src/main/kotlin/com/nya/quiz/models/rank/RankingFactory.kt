package com.nya.quiz.models.rank

class RankingFactory(private val rankingRepository: RankingRepository) {
    fun create(): RankingModel {
        return RankingModel(rankingRepository)
    }
}