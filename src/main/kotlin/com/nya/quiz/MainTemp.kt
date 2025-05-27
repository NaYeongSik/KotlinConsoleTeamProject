package com.nya.quiz

import com.nya.quiz.viewmodels.startVIewModels.EntryStartViewModel
import com.nya.quiz.viewmodels.startVIewModels.HelpViewModel
import com.nya.quiz.viewmodels.startVIewModels.LoginViewModel
import com.nya.quiz.viewmodels.startVIewModels.SignUpViewModel
import com.nya.quiz.views.startview.EntryMain

fun main() {
    val entryMain = EntryMain()
    val loginViewModel = LoginViewModel()
    val signUpViewModel = SignUpViewModel()
    val helpViewModel = HelpViewModel()
    val entryStartViewModel = EntryStartViewModel(
        entryMain,
        loginViewModel,
        signUpViewModel,
        helpViewModel
    )
    entryStartViewModel.selectStartMenu()
}