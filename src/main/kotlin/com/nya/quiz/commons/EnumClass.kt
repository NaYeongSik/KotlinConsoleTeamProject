package com.nya.quiz.commons

enum class QuizCounter(val quizNum:Int) {
    SHORT(10),
    MIDDLE(20),
    LONG(30)
}

enum class QuizTimeLimit(val time:Int){
    SHORT(15),
    MIDDLE(30),
    LONG(45)
}

enum class LoginState(val state:Int){
    LOGGED_OUT(0),
    LOGGED_IN(1),
    LOGIN_FAILED(2)
}

enum class ViewState(val state:Int){
    START_VIEW(1),
    MAIN_VIEW(2)
}

enum class StartViewState(var state:Int){
    LOG_IN(1),
    SIGN_UP(2),
    HELP(3),
    EXIT(4)
}

enum class MainMenuState(var state:Int){
    START_QUIZ(1),
    RETRY_INCORRECT_WORD(2),
    INCORRECT_NOTE(3),
    RANK(4),
    LOG_OUT(5),
    DELETE_ACCOUNT(6),
    EXIT(7),
}




