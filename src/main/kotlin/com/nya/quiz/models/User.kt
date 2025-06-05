package com.nya.quiz.models

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.commons.StateManager
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
        // runCatching으로 변환
        // runCatching은
        // 성공 실패에 대한 처리를 onSuccess등으로 체이닝 가능
        // 결과를 Result<T> 타입으로 감싸 반환
        // 명령형 분기(if,else,catch) 없이 선언적으로 에러를 다루기 가능
        // 예외 발생 시 기본값을 쉽게 지정 가능 getOrNull, getOrElse, getOrDefault 등
        // 코루틴/비동기 환경에서 권장
        return runCatching {
            // readLines는
            // 별도의 람다 블록이나 시퀀스(Sequence)를 사용할 필요 없이,
            // 파일의 모든 줄을 메모리에 담아 바로 사용할 수 있어 코드가 간단
            // 내부적으로 파일을 모두 읽고 나서 스트림을 자동으로 닫아줌.
            // 별도의 use나 close 처리를 신경 쓸 필요가 없습니다.
            // useLine()는 파일을 시퀀스로 한줄씩 처리해 메모리 효율이 더 좋지만
            // 시퀀스는 블록을 벗어나면 스트림이 닫혀버리기 때문에,
            // 시퀀스를 반환하거나 블록 밖에서 사용하려 하면 IOException가 발생할 수 있음
            // readLines는 리스트로 반환해서 함수 밖에서도 안전하게 데이터 사용 가능
            // 아주 큰 파일이여서 한줄 씩 처리하고나, 메모리 사용을 최소화 해야 한다면 useLines()가 더 적합
            val lines = accountFile.readLines()
            lines.any { line ->
                val parts = line.split(',', limit = 2)
                val fileUsername = parts[0]
                val filePassword = parts[1]
                fileUsername == username && filePassword == passwordToCheck
            }
        }.getOrDefault(false)
    }
}

