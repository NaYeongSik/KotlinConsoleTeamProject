package com.nya.quiz.models

import java.io.File
import java.io.IOException

object User {
    private const val ACCOUNT_FILE_PATH = "src/main/resources/account.txt"
    val accountFile = File(ACCOUNT_FILE_PATH)

    // 아이디 중복 검사
    fun isUsernameTaken(username: String): Boolean {
        return try {
            accountFile.useLines { lines -> // 파일 자동 닫기
                lines.any { line ->
                    val parts = line.split(',')
                    val fileUsername = parts[0]
                    fileUsername == username
                }
            }
        } catch (e: Exception) {
            false
        }
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
                    val parts = line.split(',')
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
