package com.nya.quiz.interfaces.rank

import com.nya.quiz.commons.QuizStat

interface UserStatRepository {
    fun getTotalUserInfo(): List<QuizStat>
    fun updateUserInfo(data: QuizStat): Boolean
    fun deleteInfo(id: String): Boolean
}