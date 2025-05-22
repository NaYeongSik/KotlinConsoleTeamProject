package com.nya.quiz.commons

enum class QuizCounter(var quizNum:Int) {
    SHORT(10),
    MIDDLE(20),
    LONG(30)
}

enum class QuizTimeLimit(var time:Int){
    SHORT(15),
    MIDDLE(30),
    LONG(45)
}

enum class LoginState(var state:Int){
    LOGGED_OUT(0),
    LOGGED_IN(1),
    LOGIN_FAILED(2)
}