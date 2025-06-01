package com.nya.quiz.commons


data class QuizStat(
    var userId: String = "",
    var correctRate: Float = 0f,
    var correctCount: Int = 0,
    var incorrectCount: Int = 0,
    var incorrectQuiz: List<String> = emptyList<String>()
    ){
    override fun toString(): String {
        var incorrectQuizStr = incorrectQuiz.joinToString("|")
        return "${userId}|${correctRate}|${correctCount}|${incorrectCount}|${incorrectQuizStr}"
    }
}
