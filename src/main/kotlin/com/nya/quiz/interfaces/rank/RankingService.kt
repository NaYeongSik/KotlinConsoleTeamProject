package com.nya.quiz.interfaces.rank

interface RankingService <QuizStat> {
    fun getMyRank(id: String): QuizStat
    fun getTopRanking(): List<QuizStat>
    fun recordRanking(id: String, score: Float): Boolean
    fun deleteInfo(id: String)
}