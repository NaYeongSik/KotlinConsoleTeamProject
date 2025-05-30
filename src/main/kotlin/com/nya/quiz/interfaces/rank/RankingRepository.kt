package com.nya.quiz.interfaces.rank

import com.nya.quiz.commons.QuizStat

interface RankingRepository {
    fun getTotalRanking(): List<QuizStat>
    fun recordRanking(data: QuizStat): Boolean
    fun updateRanking(data: QuizStat): Boolean
    fun deleteInfo(id: String): Boolean
}