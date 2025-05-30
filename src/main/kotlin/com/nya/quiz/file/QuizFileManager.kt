package com.nya.quiz.file

import com.nya.quiz.interfaces.ReadableFile
import com.nya.quiz.interfaces.WritableFile
import java.io.File

class QuizFileManager(
    override val fileName: String = "src/main/resources/수능필수영단어600.txt"
): ReadableFile, WritableFile {

    // 파일을 한줄씩 리스트로 읽기
    override fun readFile(): List<String>? {
        val file = File(fileName)
        if (!file.exists()) return null
        return file.readLines()
    }

    override fun writeFile(str: String): Boolean {
        TODO("Not yet implemented")
    }
}