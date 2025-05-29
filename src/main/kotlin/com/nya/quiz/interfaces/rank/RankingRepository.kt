package com.nya.quiz.interfaces.rank

import com.nya.quiz.commons.QuizStat

interface RankingRepository {
    fun getTotalRanking(): List<QuizStat>
    fun recordRanking(id: String, score: Float)
    fun deleteInfo(id: String)
}