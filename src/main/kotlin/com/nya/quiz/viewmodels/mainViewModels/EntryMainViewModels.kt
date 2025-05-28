package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.views.startview.EntryMain
import kotlin.system.exitProcess


class EntryMainViewModels(
) {
    fun selectMainMenu(id: String) {

        val connectingId = id
        val entryMain = EntryMain()
        val quizViewModel = QuizViewModel()
        val retryIncorrectWordViewModel = RetryIncorrectWordViewModel()
        val incorrectNoteViewModel = IncorrectNoteViewModel()
        val rankingViewModel = RankingViewModel()
        val logoutViewModel = LogoutViewModel()
        val deleteAccountViewModel = DeleteAccountViewModel(connectingId)
        val consoleManager = ConsoleManager


        while (true) {
            entryMain.mainMessage()
            val selectMenu = consoleManager.consoleLine().toInt()
            when (selectMenu) {
                1 -> {
//                    quizViewModel
                }

                2 -> {
                    retryIncorrectWordViewModel
                }

                3 -> {
                    incorrectNoteViewModel
                }

                4 -> {
                    rankingViewModel
                }

                5 -> {
                    break
                }

                6 -> {
                    if (deleteAccountViewModel.deleteAccount()) break
                }

                7 -> {
                    exitProcess(0)
                }

                else -> {
                    entryMain.errorMessage()
                }
            }
        }
    }
}
