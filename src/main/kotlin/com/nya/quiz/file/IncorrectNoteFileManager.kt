package com.nya.quiz.file

import com.nya.quiz.interfaces.DeletableFile
import com.nya.quiz.interfaces.ReadableFile
import com.nya.quiz.interfaces.WritableFile
import java.io.File

class IncorrectNoteFileManager(
    override val fileName: String = "src/main/resources/userStatInfo.txt"
): WritableFile,ReadableFile,DeletableFile {

    val file = File(fileName)

    override fun deleteFile(): Boolean {
        TODO("Not yet implemented")
    }

    override fun readFile(): List<String>? {
        runCatching {
            if (!file.exists()) return null
            return file.bufferedReader().use { reader -> reader.readLines() }
        }.onFailure { println("정보를 가져오지 못했습니다.") }
        return null
    }

    override fun writeFile(text: String): Boolean {
        runCatching {
            if (!file.exists()) return false
            file.appendText(text)
        }.onFailure { println("데이터를 추가하지 못했습니다.") }
        return false
    }

    override fun updateFile(text: String): Boolean {
        runCatching {
            if (!file.exists()) return false
            file.writeText(text)
        }.onFailure { println("데이터를 수정하지 못했습니다.") }
        return false
    }
}