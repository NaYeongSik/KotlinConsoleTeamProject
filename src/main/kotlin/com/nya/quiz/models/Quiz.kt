package com.nya.quiz.models

import com.nya.quiz.file.QuizFileManager
import com.nya.quiz.interfaces.Solvable

data class Quiz(
    val totalQuestions: Int,
    val timeLimitSec: Int,
    var isFinished: Boolean = false
)

data class QuizWord(
    val word: String,
    val meanings: List<String>
)

class SolveQuiz(): Solvable{

    override fun start(quizCount: Int, quizTime: Int) {
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun pass() {
        TODO("Not yet implemented")
    }


}

