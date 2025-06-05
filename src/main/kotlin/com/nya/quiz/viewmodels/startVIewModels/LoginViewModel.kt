package com.nya.quiz.viewmodels.startVIewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.commons.StateManager
import com.nya.quiz.models.User
import com.nya.quiz.models.rank.UserStatRepositoryImpl

object LoginViewModel {

    fun login() {
        while (true) {
            print("아이디를 입력하세요. : ")
            val inputUsername = ConsoleManager.consoleLine()
            print("비밀번호를 입력하세요. : ")
            val inputPassword = ConsoleManager.consoleLine()
            if (User.loginUser(inputUsername, inputPassword)) {
                println("로그인 되었습니다.")
                User.setId(inputUsername)
                UserStatRepositoryImpl.setMyProfile(inputUsername)
                StateManager.updateState()
                break
            } else {
                println("입력정보가 틀렸습니다. 다시 입력하세요.")
            }
        }
    }
}