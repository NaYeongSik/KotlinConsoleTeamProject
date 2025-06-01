package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.QuizCounter
import com.nya.quiz.commons.QuizStat
import com.nya.quiz.file.IncorrectNoteFileManager
import com.nya.quiz.models.QuizWord

class RetryIncorrectWordViewModel{
    private val incorrectNoteFileManager = IncorrectNoteFileManager()

    var incorrectNoteQuizWords: List<QuizWord> = emptyList()
        private set


    fun loadIncorrectNoteQuizWords(userId: String){
        val lines = incorrectNoteFileManager.readFile() ?: emptyList()

        val line = lines.firstOrNull { it.startsWith("$userId|") } ?: return

        val parts =  line.split('|')
        if (parts.size < 5) {
            incorrectNoteQuizWords = emptyList()
            return
        }

        val incorrectQuizStr = parts.subList(4, parts.size).joinToString("|")

        val regex = Regex("""QuizWord\(word=([^,]+), meanings=\[([^\]]*)\]\)""")
        val quizWord = regex.findAll(incorrectQuizStr).mapNotNull { match ->
            val word = match.groupValues[1].trim()
            val meaningsStr = match.groupValues[2]
            val meanings = meaningsStr.split(',')
                .map {it.trim()}
                .filter {it.isNotEmpty()}
            QuizWord(word, meanings)
        }.toList()

        incorrectNoteQuizWords = quizWord
    }

    fun getRandomQuiz(counter: Int): List<QuizWord> = incorrectNoteQuizWords.shuffled().take(counter)

    fun isCorrectAnswer(userInput: String, quizWord: QuizWord): Boolean{
        return quizWord.meanings.any {userInput.trim() == it.trim()}
    }


}