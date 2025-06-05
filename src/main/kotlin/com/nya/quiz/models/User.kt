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
        UserRepository.saveAccount(accountData,accountStatData.toString()+"\n")
    }

    // 계정 검증
    fun loginUser(username: String, passwordToCheck: String): Boolean {
        val totalAccountInfo = UserRepository.getTotalAccountInfo()
        for (accountData in totalAccountInfo){
            if (accountData.isNotBlank()){
                val data = accountData.split(",")
                if(data[0] == username && data[1] == passwordToCheck) return true
            }
        }
        return false
    }

}

