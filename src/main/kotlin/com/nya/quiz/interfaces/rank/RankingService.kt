package com.nya.quiz.interfaces.rank

import com.nya.quiz.commons.QuizStat

interface RankingService {
    fun getTotalRanking(): List<QuizStat>
    fun recordRanking(id: String, score: Float): Boolean
    fun deleteInfo(id: String)
}