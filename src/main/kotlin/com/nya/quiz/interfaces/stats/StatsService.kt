package com.nya.quiz.interfaces.stats

interface StatsService {
    fun getMyTotalStats(userId: String)
    fun calcMyStats()
}