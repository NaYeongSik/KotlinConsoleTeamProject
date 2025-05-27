package com.nya.quiz.commons

data class QuizStat(val userId: String, val incorrectRate: Double, val incorrectQuizNum: MutableList<Int>, val incorrectCount: Int, val correctCount: Int)