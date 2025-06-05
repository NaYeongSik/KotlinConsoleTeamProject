package com.nya.quiz.models

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.commons.StateManager

object User {
    var accountId = ""

    fun setId(input: String) {
        accountId = input
    }

    fun getId() = accountId

    fun logout() {
        accountId = ""
        StateManager.updateState()
    }

    // 새 계정 생성
    fun saveAccount(username: String, password: String) {
        val accountData = "$username,$password"
        val accountStatData = QuizStat().copy(userId = username)
        UserRepository.saveAccount(accountData,accountStatData.toString()+"\n") // UserRepository에서 파일 접근하도록 수정
    }

    // 계정 검증
    fun loginUser(username: String, passwordToCheck: String): Boolean {
        val totalAccountInfo = UserRepository.getTotalAccountInfo() // accountFile에 직접 연결하여 readLine() 사용하던 부분을 UserRepository에서 전체 정보를 가져오도록 수정
        for (accountData in totalAccountInfo){
            if (accountData.isNotBlank()){
                val data = accountData.split(",")
                if(data[0] == username && data[1] == passwordToCheck) return true
            }
        }
        return false
    }

}

