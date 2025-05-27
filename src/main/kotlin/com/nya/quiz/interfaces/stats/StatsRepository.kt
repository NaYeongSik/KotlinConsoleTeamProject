package com.nya.quiz.interfaces.stats

interface StatsRepository<T> {
    fun getMyTotalStats(userId: String): T
    fun recordTotalStats(userId: String, stat: Any)
}