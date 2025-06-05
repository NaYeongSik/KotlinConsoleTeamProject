package com.nya.quiz.file

import com.nya.quiz.commons.ACCOUNT_FILE_PATH
import com.nya.quiz.interfaces.ReadableFile
import com.nya.quiz.interfaces.WritableFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class AccountFileManager(): WritableFile, ReadableFile {
    val file = File(ACCOUNT_FILE_PATH)

    override suspend fun writeFile(text: String) {
        return withContext(Dispatchers.IO){
            runCatching {
                if (file.exists()) file.appendText(text)
            }.onFailure { println("데이터를 추가하지 못했습니다.") }
        }
    }

    override suspend fun updateFile(text: String) {
        return withContext(Dispatchers.IO){
            runCatching {
                if (file.exists()) file.writeText(text)
            }.onFailure { println("데이터를 수정하지 못했습니다.") }
        }
    }

    override suspend fun readFile(): List<String>? {
        return withContext(Dispatchers.IO){
            runCatching {
                if (!file.exists()) return@withContext null
                return@withContext file.bufferedReader().use { reader -> reader.readLines() }
            }.onFailure { println("정보를 가져오지 못했습니다.") }
            return@withContext null
        }
    }
}