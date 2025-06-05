package com.nya.quiz.viewmodels.startVIewModels

import com.nya.quiz.models.User
import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.commons.isValidUsername
import com.nya.quiz.commons.isValidPassword

object SignUpViewModel {

    fun signUp() {
        println("\n----- 회원가입 -----")
        User.createUser()
    }
}
