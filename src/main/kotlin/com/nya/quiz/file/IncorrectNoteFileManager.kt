package com.nya.quiz.file

import com.nya.quiz.interfaces.DeletableFile
import com.nya.quiz.interfaces.ReadableFile
import com.nya.quiz.interfaces.WritableFile

class IncorrectNoteFileManager(
    override val fileName: String
): WritableFile,ReadableFile,DeletableFile {

    override fun deleteFile(): Boolean {
        TODO("Not yet implemented")
    }

    override fun readFile(): List<String>? {
        TODO("Not yet implemented")
    }

    override fun writeFile(): Boolean {
        TODO("Not yet implemented")
    }
}