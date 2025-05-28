package com.nya.quiz.commons

data class QuizStat(val userId: String, val incorrectQuizNum: MutableList<Int>, val incorrectCount: Int, val correctCount: Int)