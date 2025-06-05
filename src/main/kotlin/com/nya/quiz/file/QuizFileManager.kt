package com.nya.quiz.file

import com.nya.quiz.commons.WORD_NOTE_FILE_PATH
import com.nya.quiz.interfaces.ReadableFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class QuizFileManager(): ReadableFile{

    // 파일을 한줄씩 리스트로 읽기
    override suspend fun readFile(): List<String>? {
        return withContext(Dispatchers.IO){
            val file = File(WORD_NOTE_FILE_PATH)
            if (!file.exists()) return@withContext null
            return@withContext file.readLines()
        }

    }
}