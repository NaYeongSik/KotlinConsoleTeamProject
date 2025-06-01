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
import com.nya.quiz.models.rank.RankingFactory
import com.nya.quiz.models.rank.RankingModel
import com.nya.quiz.models.rank.RankingRepository
import com.nya.quiz.viewmodels.mainViewModels.*
import com.nya.quiz.viewmodels.startVIewModels.HelpViewModel
import com.nya.quiz.viewmodels.startVIewModels.LoginViewModel
import com.nya.quiz.viewmodels.startVIewModels.SignUpViewModel
import com.nya.quiz.views.mainview.IncorrectNoteView
import com.nya.quiz.views.mainview.RankingView
import com.nya.quiz.views.mainview.QuizView
import com.nya.quiz.views.mainview.RetryIncorrectWordView

lateinit var rankingModel : RankingModel
lateinit var rankingViewModel: RankingViewModel
lateinit var rankingView: RankingView


/**
 * Start program
 * 콘솔 프로그램 시작 부분. State가 END_VIEW로 바뀔때까지 동작 (END_VIEW는 시작메뉴 or 메인메뉴에서 종료 입력시 State 변경. 아직 미구현)
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
 *  ** 현재 Main에 정의해놨으나, MVVM 패턴에 맞게 수정 필요. **
 *  생각나는대로 예시를 만들어본거라 수정이 필요하시면 수정하셔서 진행하시면 됩니다.
 * @param line : 사용자 입력 값 (1~4). 사용자가 입력한 메뉴 프로세스 진행
 */

fun runStartMenuProcess(line: String) {
    when (StartViewState.fromInt(line.trim().toInt())) {
        StartViewState.LOG_IN -> {
            LoginViewModel.login()
            StateManager.updateState()
        }

        StartViewState.SIGN_UP -> {
            SignUpViewModel.signUp()
        }

        StartViewState.HELP -> {
            HelpViewModel.help()
        }

        StartViewState.EXIT -> runEndMenuProcess()

        null -> println(INVALID_MESSAGE)
    }
}

/**
 * Run main menu process
 * 메인 메뉴에서 진행되는 동작 (풀이시작, 오답다시풀기, 오답노트, 통계, 랭킹, 로그아웃, 종료)
 *  ** 현재 Main에 정의해놨으나, MVVM 패턴에 맞게 수정 필요. **
 * @param line : 사용자 입력 값 (1~7). 사용자가 입력한 메뉴 프로세스 진행
 */
fun runMainMenuProcess(line: String) {
    initRankingService()
    when (MainMenuState.fromInt(line.trim().toInt())) {
        MainMenuState.START_QUIZ -> {
            val quizView = QuizView(QuizViewModel())
            quizView.show()
        }
        MainMenuState.RETRY_INCORRECT_WORD -> {
            val retryIncorrectWordView = RetryIncorrectWordView(RetryIncorrectWordViewModel())
            retryIncorrectWordView.show()
        }
        MainMenuState.INCORRECT_NOTE -> {
            val incorrectNoteView = IncorrectNoteView(IncorrectNoteViewModel())
            incorrectNoteView.showIncorrectNote()
        }
        MainMenuState.RANK -> rankingView.showRanking()
        MainMenuState.LOG_OUT -> {
            User.logout()
            StateManager.updateState()
        }

        MainMenuState.DELETE_ACCOUNT -> {
            DeleteAccountViewModel(User.getId()).deleteAccount()
            StateManager.updateState()
        }

        MainMenuState.EXIT -> runEndMenuProcess()
        null -> println(INVALID_MESSAGE)
    }
}

/**
 * Run end menu process
 * 시작 메뉴 및 메인 메뉴에서 종료 이벤트 진행시 동작
 *  ** 현재 Main에 정의해놨으나, MVVM 패턴에 맞게 수정 필요. **
 *  종료시 추가로 필요한 작업이 있는 경우가 있을 수 있어요.
 */
fun runEndMenuProcess() {
    StateManager.updateState()
    printMenuMessage()
}

fun printMenuMessage() {
    when (StateManager.getState()) {
        ViewState.START_VIEW -> println(START_MENU_MESSAGE)
        ViewState.MAIN_VIEW -> println(MAIN_MENU_MESSAGE)
        ViewState.END_VIEW -> println(END_MESSAGE)
    }
}

fun initRankingService(){
    if (!::rankingModel.isInitialized) rankingModel = RankingFactory(RankingRepository).create()
    if (!::rankingViewModel.isInitialized) rankingViewModel = RankingViewModel(rankingModel)
    if (!::rankingView.isInitialized) rankingView = RankingView(rankingViewModel)
}

fun main() {
    startProgram()
}