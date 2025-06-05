package com.nya.quiz.file

import com.nya.quiz.interfaces.ReadableFile
import com.nya.quiz.interfaces.WritableFile
import com.nya.quiz.models.QuizWord
import java.io.File

class QuizFileManager(
    override val fileName: String = "src/main/resources/수능필수영단어600.txt"
) : ReadableFile, WritableFile {
    private val quizFileManager = QuizFileManager()
    var quizWords: List<QuizWord> = emptyList()
        private set

    fun loadFilteredQuizTxtFile() {
        val lines = quizFileManager.readFile() ?: emptyList()
        quizWords = lines.mapNotNull { line ->
            val parts = line.split('\t')
            if (parts.size < 3) return@mapNotNull null
            val word = parts[1].trim()
            val meanings = parts[2]
                .split(',')
                .map { it.replace(Regex("\\([^)]*\\)"), "").trim() }
            QuizWord(word, meanings)
        }
    }

    // 파일을 한줄씩 리스트로 읽기
    override fun readFile(): List<String>? {
        val file = File(fileName)
        return runCatching {
            file.readLines()
        }.getOrElse {
            println("파일이 존재하지 않습니다.")
            null
        }
    }

    override fun writeFile(str: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateFile(text: String): Boolean {
        TODO("Not yet implemented")
    }
}