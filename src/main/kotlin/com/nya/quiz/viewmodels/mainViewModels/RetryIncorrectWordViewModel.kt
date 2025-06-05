package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.quizWordRegex
import com.nya.quiz.file.IncorrectNoteFileManager
import com.nya.quiz.interfaces.QuizRepository
import com.nya.quiz.models.quiz.QuizRepositoryImpl
import com.nya.quiz.models.quiz.QuizWord

class RetryIncorrectWordViewModel(
){
    private val incorrectNoteFileManager = IncorrectNoteFileManager()

    var incorrectNoteQuizWords: List<QuizWord> = emptyList()
        private set

    suspend fun loadIncorrectNoteQuizWords(userId: String) {
        incorrectNoteQuizWords = QuizRepositoryImpl.loadIncorrectNoteQuizWords(userId)
    }

    fun getRandomQuiz(counter: Int): List<QuizWord> = incorrectNoteQuizWords.shuffled().take(counter)

    fun isCorrectAnswer(userInput: String, quizWord: QuizWord): Boolean{
        return quizWord.meanings.any {userInput.trim() == it.trim()}
    }


}