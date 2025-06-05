package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.ACCOUNT_FILE_DELIMITER
import com.nya.quiz.commons.QUIZ_FILE_REGEX
import com.nya.quiz.commons.USERSTATINFO_FILE_DELIMITER
import com.nya.quiz.file.IncorrectNoteFileManager
import com.nya.quiz.models.QuizWord
import com.nya.quiz.models.rank.RankingRepositoryImpl

class RetryIncorrectWordViewModel{
    private val incorrectNoteFileManager = IncorrectNoteFileManager()

    var incorrectNoteQuizWords: List<QuizWord> = emptyList()
        private set




    fun loadIncorrectNoteQuizWords(userId: String){
        val lines = incorrectNoteFileManager.readFile() ?: emptyList()

        val line = lines.firstOrNull { it.startsWith("$userId|") } ?: return

        val parts =  line.split(USERSTATINFO_FILE_DELIMITER)
        if (parts.size < 5) {
            incorrectNoteQuizWords = emptyList()
            return
        }

        val incorrectQuizStr = parts.subList(4, parts.size).joinToString(USERSTATINFO_FILE_DELIMITER)

        val regex = Regex(QUIZ_FILE_REGEX)
        val quizWord = regex.findAll(incorrectQuizStr).map { match ->
            val word = match.groupValues[1].trim()
            val meaningsStr = match.groupValues[2]
            val meanings = meaningsStr.split(ACCOUNT_FILE_DELIMITER)
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