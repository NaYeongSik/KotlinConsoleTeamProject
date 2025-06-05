package com.nya.quiz.models

import com.nya.quiz.commons.ACCOUNT_FILE_DELIMITER
import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.commons.QuizStat
import com.nya.quiz.commons.USERSTATINFO_FILE_DELIMITER
import com.nya.quiz.commons.isValidPassword
import com.nya.quiz.commons.isValidUsername
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


import java.io.File

object User {
    private const val ACCOUNT_FILE_PATH = "src/main/resources/account.txt"
    private const val ACCOUNT_STAT_FILE_PATH = "src/main/resources/userStatInfo.txt"
    val accountFile = File(ACCOUNT_FILE_PATH)
    val accountStatFile = File(ACCOUNT_STAT_FILE_PATH)
    var accountId = ""

    fun setId(input: String) {
        accountId = input
    }

    fun getId() = accountId

    fun logout() {
        accountId = ""
    }

    // 새 계정 생성
    fun saveAccount(username: String, password: String) {
        val accountData = "$username,$password"
        val accountStatData = QuizStat().copy(userId = username)
        accountFile.appendText(accountData + "\n")
        accountStatFile.appendText(accountStatData.toString() + "\n")
    }

    // 계정 검증
    fun loginUser(username: String, passwordToCheck: String): Boolean {
        return runCatching {
            val readLines = accountFile.readLines()
            readLines.any { line ->
                val parts = line.split(',', limit = 2)
                val fileUsername = parts[0]
                val filePassword = parts[1]
                fileUsername == username && filePassword == passwordToCheck
                true
            }
        }.getOrDefault(false)
    }

    suspend fun deleteAccount() = withContext(Dispatchers.IO) {
        deleteUserAccount(accountFile, ACCOUNT_FILE_DELIMITER)
        deleteUserAccount(accountStatFile, USERSTATINFO_FILE_DELIMITER)
    }

    fun deleteUserAccount(file: File, delimiter: String) {
        val dataToKeep = file.readLines().filter { line ->
            line.split(delimiter)[0] != getId()
        }
        accountFile.writeText(dataToKeep.joinToString(separator = "\n"))
        accountFile.appendText("\n")
    }

    fun createUser() {
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
            val confirmPassword = ConsoleManager.consoleLine()
            if (password == confirmPassword) {
                println("비밀번호가 확인되었습니다.")
                saveAccount(username, password)
                println("회원가입이 완료되었습니다. 시작 메뉴로 돌아갑니다.")
                break
            } else {
                println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.")
            }
        }
    }
}
