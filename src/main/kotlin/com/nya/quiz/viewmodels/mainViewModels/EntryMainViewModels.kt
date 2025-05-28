package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.views.startview.EntryMain
import kotlin.system.exitProcess


class EntryMainViewModels(
) {
    fun selectMainMenu(id: String) {

        val entryMain = EntryMain()
        val quizViewModel = QuizViewModel()
        val retryIncorrectWordViewModel = RetryIncorrectWordViewModel()
        val incorrectNoteViewModel = IncorrectNoteViewModel()
        //val rankViewModel = RankViewModel()
        val logoutViewModel = LogoutViewModel()
        val deleteAccountViewModel = DeleteAccountViewModel()
        val consoleManager = ConsoleManager
        val connectingId = id
        while (true) {
            entryMain.mainMessage()
            val selectMenu = consoleManager.consoleLine().toInt()
            when (selectMenu) {
                1 -> {
                    quizViewModel
                }

                2 -> {
                    retryIncorrectWordViewModel
                }

                3 -> {
                    incorrectNoteViewModel
                }

                4 -> {
                    //rankViewModel
                }

                5 -> {
                    break
                }

                6 -> {
                    deleteAccountViewModel
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
