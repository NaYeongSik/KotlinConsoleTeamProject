package com.nya.quiz.viewmodels.startVIewModels

import com.nya.quiz.models.User
import com.nya.quiz.commons.ConsoleManager


/*class SignUpViewModel {
    val validator = Validator()

    fun signUp() {
        println("\n----- 회원가입 -----")
        var username: String
        var password: String

        while (true) {
            print("사용할 아이디를 입력하세요 (4~12자, 영문/숫자): ")
            val inputUsername = ConsoleManager.consoleLine()
            val (isValid, message) = isValidUsername(inputUsername)
            println(message)
            if (isValid) {
                username = inputUsername
                break
            }
        }

        while (true) {
            print("사용할 비밀번호를 입력하세요 (6자 이상): ")
            val inputPassword = ConsoleManager.consoleLine()
            val (isValid, message) = isValidPassword(inputPassword)
            if (message.isNotBlank()) println(message)
            if (isValid) {
                password = inputPassword
                break
            }
        }

        while (true) {
            print("비밀번호를 다시 한번 입력하세요: ")
            val confirmPassword = readLine() ?: ""
            if (password == confirmPassword) {
                println("비밀번호가 확인되었습니다.")
                break
            } else {
                println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.")
            }
        }

        User.saveAccount(username, password)
        println("회원가입이 완료되었습니다. 시작 메뉴로 돌아갑니다.")
    }
}*/
