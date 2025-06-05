package com.nya.quiz.models

data class Quiz(
    val totalQuestions: Int,
    val timeLimitSec: Int,
    var isFinished: Boolean = false
)

data class QuizWord(
    val word: String,
    val meanings: List<String>
)