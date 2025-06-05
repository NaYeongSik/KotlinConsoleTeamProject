package com.nya.quiz

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.commons.END_MESSAGE
import com.nya.quiz.commons.MAIN_MENU_MESSAGE
import com.nya.quiz.commons.MainMenuState
import com.nya.quiz.commons.START_MENU_MESSAGE
import com.nya.quiz.commons.StateManager
import com.nya.quiz.commons.INVALID_MESSAGE
import com.nya.quiz.commons.ViewState
import com.nya.quiz.commons.isValid
import com.nya.quiz.commons.StartViewState
import com.nya.quiz.models.User
import com.nya.quiz.viewmodels.mainViewModels.*
import com.nya.quiz.viewmodels.startVIewModels.HelpViewModel
import com.nya.quiz.viewmodels.startVIewModels.LoginViewModel
import com.nya.quiz.viewmodels.startVIewModels.SignUpViewModel
import com.nya.quiz.views.mainview.IncorrectNoteView
import com.nya.quiz.views.mainview.RankingView
import com.nya.quiz.views.mainview.QuizView
import com.nya.quiz.views.mainview.RetryIncorrectWordView


val quizView = QuizView(QuizViewModel())
val rankingView = RankingView(RankingViewModel())
val incorrectNoteView = IncorrectNoteView(IncorrectNoteViewModel())
val retryIncorrectWordView = RetryIncorrectWordView(RetryIncorrectWordViewModel())

/**
 * Start program
 * 콘솔 프로그램 시작 부분. State가 END_VIEW로 바뀔때까지 동작
 */
fun startProgram() {

    while (!StateManager.isEndState()) { // State가 END_VIEW 일때 프로그램 종료
        printMenuMessage()
        val line = ConsoleManager.consoleLine() // 사용자 입력 대기.

        if (isValid(line)) { // 각 State별 유효성 검사 진행
            when (StateManager.getState()) { // 각 State별로 프로세스 진행 (시작 메뉴, 메인 메뉴, 종료)
                ViewState.START_VIEW -> runStartMenuProcess(line) // 시작 메뉴 동작
                ViewState.MAIN_VIEW -> runMainMenuProcess(line)   // 메인 메뉴 동작
                ViewState.END_VIEW -> runEndMenuProcess()        // 종료 동작
            }
        } else {
            println(INVALID_MESSAGE) // 유효성 검사 실패시 출력 메시지
        }
    }
}

/**
 * Run start menu process
 * 시작 메뉴에서 진행되는 동작(로그인, 회원가입, 도움말, 종료).
 * @param line : 사용자 입력 값 (1~4). 사용자가 입력한 메뉴 프로세스 진행
 */

fun runStartMenuProcess(line: String) {
    when (StartViewState.fromInt(line.trim().toInt())) {
        StartViewState.LOG_IN -> LoginViewModel.login()

        StartViewState.SIGN_UP -> SignUpViewModel.signUp()

        StartViewState.HELP -> HelpViewModel.help()

        StartViewState.EXIT -> runEndMenuProcess()

        else -> println(INVALID_MESSAGE)
    }
}

/**
 * Run main menu process
 * 메인 메뉴에서 진행되는 동작 (풀이시작, 오답다시풀기, 오답노트, 통계, 랭킹, 로그아웃, 종료)
 * @param line : 사용자 입력 값 (1~7). 사용자가 입력한 메뉴 프로세스 진행
 */
fun runMainMenuProcess(line: String) {

    when (MainMenuState.fromInt(line.trim().toInt())) {
        MainMenuState.START_QUIZ -> quizView.show()

        MainMenuState.RETRY_INCORRECT_WORD -> retryIncorrectWordView.show()

        MainMenuState.INCORRECT_NOTE -> incorrectNoteView.showIncorrectNote()

        MainMenuState.RANK -> rankingView.showRanking()

        MainMenuState.LOG_OUT -> User.logout()

        MainMenuState.DELETE_ACCOUNT -> DeleteAccountViewModel().deleteAccount()

        MainMenuState.EXIT -> runEndMenuProcess()

        else -> println(INVALID_MESSAGE)
    }
}

/**
 * Run end menu process
 * 시작 메뉴 및 메인 메뉴에서 종료 이벤트 진행시 동작
 */
fun runEndMenuProcess() {
    StateManager.updateState()
    printMenuMessage()
    ConsoleManager.closeScanner()
}

fun printMenuMessage() {
    when (StateManager.getState()) {
        ViewState.START_VIEW -> println(START_MENU_MESSAGE)
        ViewState.MAIN_VIEW -> println(MAIN_MENU_MESSAGE)
        ViewState.END_VIEW -> println(END_MESSAGE)
    }
}


fun main() {
    startProgram()
}