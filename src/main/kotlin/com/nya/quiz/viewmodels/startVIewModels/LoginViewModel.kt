package com.nya.quiz.viewmodels.startVIewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.commons.StateManager
import com.nya.quiz.models.User
import com.nya.quiz.viewmodels.mainViewModels.EntryMainViewModels

class LoginViewModel {

    fun login() {
        val entryMainViewModels = EntryMainViewModels()
        // 1. 아이디 입력 및 검사
        while (true) {
            print("아이디를 입력하세요. : ")
            val inputUsername = ConsoleManager.consoleLine()
            print("비밀번호를 입력하세요. : ")
            val inputPassword = ConsoleManager.consoleLine()
            if (User.loginUser(inputUsername, inputPassword)) {
                println("로그인 되었습니다.")
                StateManager.updateState("1")
                    entryMainViewModels.selectMainMenu(inputUsername)
                    break
            } else {
                println("입력정보가 틀렸습니다. 다시 입력하세요.")
            }
        }
    }
}
