package com.nya.quiz.viewmodels.startVIewModels

import com.nya.quiz.models.User
import com.nya.quiz.commons.ConsoleManager

class SignUpViewModel {

    fun signUp() {
        println("\n----- 회원가입 -----")
        var username = ""
        var password = ""

        // 1. 아이디 입력 및 검사
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

        // 2. 비밀번호 입력 및 검사
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

        // 3. 비밀번호 재입력 확인
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
        // 4. 모든 검사 통과 시 계정 저장
        User.saveAccount(username, password)
        println("회원가입이 완료되었습니다! 메인 메뉴로 돌아갑니다.")
    }
}

// 아이디 유효성 검사 함수
fun isValidUsername(username: String): Pair<Boolean, String> {
    if (username.isBlank()) {
        return false to "아이디를 입력해주세요."
    }
    if (username.length < 4 || username.length > 10) {
        return false to "아이디는 4자 이상 12자 이하로 입력해주세요."
    }
    if (!username.all { it.isLetterOrDigit() }) { // 영문/숫자만 허용 (선택적)
        return false to "아이디는 영문자와 숫자만 사용할 수 있습니다."
    }
    if (User.isUsernameTaken(username)) {
        return false to "이미 사용 중인 아이디입니다."
    }
    return true to "사용 가능한 아이디입니다."
}

// 비밀번호 유효성 검사 함수
fun isValidPassword(password: String): Pair<Boolean, String> {
    if (password.isBlank()) {
        return false to "비밀번호를 입력해주세요."
    }
    if (password.length < 6) {
        return false to "비밀번호는 6자 이상이어야 합니다."
    }
    return true to ""
}
