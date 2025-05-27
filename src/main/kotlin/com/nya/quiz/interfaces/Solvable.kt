package com.nya.quiz.interfaces

interface Solvable {
    fun start(quizCount:Int, quizTime:Int)
    fun stop()
    fun pass()
}