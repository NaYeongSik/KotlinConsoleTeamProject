package com.nya.quiz.file

import com.nya.quiz.interfaces.DeletableFile
import com.nya.quiz.interfaces.ReadableFile
import com.nya.quiz.interfaces.WritableFile
import java.io.File

class IncorrectNoteFileManager(
    override val fileName: String = "src/main/resources/userStatInfo.txt"
): WritableFile,ReadableFile,DeletableFile {

    override fun deleteFile(): Boolean {
        TODO("Not yet implemented")
    }

    override fun readFile(): List<String>? {
        val file = File(fileName)
        if (!file.exists()) return null
        return file.readLines()
    }

    override fun writeFile(str: String): Boolean {
        File(fileName).writeText(str)
        return true
    }

    override fun updateFile(text: String): Boolean {
        val file = File(fileName)
        if (!file.exists()) return false
        file.appendText(text)
        return true
    }
}