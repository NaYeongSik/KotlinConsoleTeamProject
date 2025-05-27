package com.nya.quiz.models.rank

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.interfaces.rank.RankingService

class RankingFactory(private val rankingRepository: RankingRepositoryImpl) {
    fun create(): RankingServiceImpl {
        return RankingServiceImpl(rankingRepository)
    }
}