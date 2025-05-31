package com.nya.quiz.models.rank

class RankingFactory(private val rankingRepository: RankingRepositoryImpl) {
    fun create(): RankingModel {
        return RankingModel(rankingRepository)
    }
}