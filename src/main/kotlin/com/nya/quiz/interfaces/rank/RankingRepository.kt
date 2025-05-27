package com.nya.quiz.interfaces.rank

interface RankingRepository<QuizStat> {
    fun getMyRanking(id: String): QuizStat
    fun getTopRanking(): List<QuizStat>
    fun recordRanking(id: String, score: Float)
    fun deleteInfo(id: String)
}