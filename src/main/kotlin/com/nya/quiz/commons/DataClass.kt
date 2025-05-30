package com.nya.quiz.commons


data class QuizStat(val userId: String = "", val correctRate: Float = 0f,val correctCount: Int = 0, val incorrectCount: Int = 0, val incorrectQuiz: List<String> = emptyList<String>()){
    override fun toString(): String {
        var incorrectQuizStr = incorrectQuiz.joinToString("|")
        return "${userId}|${correctRate}|${correctCount}|${incorrectCount}|${incorrectQuizStr}"
    }
}
