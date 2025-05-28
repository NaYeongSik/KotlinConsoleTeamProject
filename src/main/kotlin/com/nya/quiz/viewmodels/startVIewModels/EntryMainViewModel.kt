package com.nya.quiz.viewmodels.startVIewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.views.startview.EntryMain

class EntryStartViewModel(
    val entryMain: EntryMain,
    val loginViewModel: LoginViewModel,
    val signUpViewModel: SignUpViewModel,
    val helpViewModel: HelpViewModel,
    ) {
    fun selectStartMenu() {

        val consoleManager = ConsoleManager
        while (true) {
            entryMain.startMessage()
            val selectMenu = consoleManager.consoleLine().toInt()
            when (selectMenu) {

                1 -> {
                    loginViewModel.login()
                }

                2 -> {
                    signUpViewModel.signUp()
                }

                3 -> {
                    helpViewModel.help()
                }

                0 -> {
                    break
                }

                else -> {
                    entryMain.errorMessage()
                }
            }
        }
    }
}
