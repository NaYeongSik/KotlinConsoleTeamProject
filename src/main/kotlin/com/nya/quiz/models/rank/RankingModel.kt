package com.nya.quiz.models.rank

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.interfaces.rank.RankingService

class RankingModel(): RankingService {

    override fun getTotalRanking(): List<QuizStat> = UserStatRepositoryImpl.getTotalUserInfo()

}