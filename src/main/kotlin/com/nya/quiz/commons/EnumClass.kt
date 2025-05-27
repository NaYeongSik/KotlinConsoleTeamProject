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

enum class ViewState(val state:Int){
    START_VIEW(1),
    MAIN_VIEW(2),
    END_VIEW(3) // 프로그램 종료 조건으로 사용하기위해 추가
}

enum class StartViewState(var state:Int) {
    LOG_IN(1),
    SIGN_UP(2),
    HELP(3),
    EXIT(4);

    companion object { // 사용자가 입력한 값을 Int로 변환하여 대조해서 처리하려했으나 직접적인 대조가 안되어 추가했습니다.
        fun fromInt(code: Int): StartViewState? = entries.find { it.state == code }
    }
}

enum class MainMenuState(var state:Int){
    START_QUIZ(1),
    RETRY_INCORRECT_WORD(2),
    INCORRECT_NOTE(3),
    RANK(4),
    LOG_OUT(5),
    DELETE_ACCOUNT(6),
    EXIT(7);

    companion object {
        fun fromInt(code: Int): MainMenuState? = MainMenuState.entries.find { it.state == code }
    }
}