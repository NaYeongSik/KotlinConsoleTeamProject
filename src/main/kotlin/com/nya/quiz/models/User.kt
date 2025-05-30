package com.nya.quiz.models

import com.nya.quiz.commons.StateManager
import java.io.File

object User {
    private const val ACCOUNT_FILE_PATH = "src/main/resources/account.txt"
    val accountFile = File(ACCOUNT_FILE_PATH)
    var accountId = ""

    fun setId(input: String) {
        accountId = input
    }

    fun getId() = accountId

    fun logout() {
        accountId = ""
    }

    // 새 계정 저장
    fun saveAccount(username: String, password: String) {
        val accountData = "$username,$password\n"
        accountFile.appendText(accountData)
    }

    // 계정 검증
    fun loginUser(username: String, passwordToCheck: String): Boolean {
        return try {
            accountFile.useLines { lines ->
                lines.any { line ->
                    val parts = line.split(',', limit = 2)
                    val fileUsername = parts[0]
                    val filePassword = parts[1]
                    fileUsername == username && filePassword == passwordToCheck
                }
            }
        } catch (e: Exception) {
            false
        }
    }
}
