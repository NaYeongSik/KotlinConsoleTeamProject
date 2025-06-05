package com.nya.quiz.file

import com.nya.quiz.interfaces.ReadableFile
import com.nya.quiz.interfaces.WritableFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.File

class QuizFileManager(
   private val fileName: String = "src/main/resources/수능필수영단어600.txt"
): ReadableFile, WritableFile {

    // 파일을 한줄씩 리스트로 읽기
    override suspend fun readFile(): List<String>? = withContext(Dispatchers.IO) {
        val file = File(fileName)
        if (!file.exists()) return@withContext null
        kotlin.runCatching{ file.readLines() }
            .onFailure { it.printStackTrace() }
            .getOrNull()
    }


    override fun writeFile(str: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateFile(text: String): Boolean {
        TODO("Not yet implemented")
    }
}