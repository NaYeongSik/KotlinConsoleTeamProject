package com.nya.quiz.models

// AccountFileHandler.kt (또는 같은 파일에 object로 정의)
import java.io.File
import java.io.IOException

object User {
    private const val ACCOUNT_FILE_PATH = "src/main/resources/account.txt" // 파일 경로
    private val accountFile = File(ACCOUNT_FILE_PATH)

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
        } catch (e: IOException) {
            false
        }
    }

    // 새 계정 저장
    fun saveAccount(username: String, password: String) {
        val accountData = "$username,$password\n" // 아이디,비밀번호 형식으로 저장
        accountFile.appendText(accountData) // 파일 끝에 추가
    }

    // (로그인 기능 구현 시 필요)
    fun loginUser(username: String, passwordToCheck: String): Boolean { // 또는 suspend
        return try {
            accountFile.useLines { lines -> // 파일 자동 닫기
                lines.any { line ->
                    val parts = line.split(',')
                        val fileUsername = parts[0]
                        val filePassword = parts[1]
                        fileUsername == username && filePassword == passwordToCheck
                    }
                }
        } catch (e: IOException) {
            false
        }
    }
}
