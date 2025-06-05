package com.nya.quiz.interfaces

import com.nya.quiz.models.quiz.QuizWord

interface QuizRepository {
    suspend fun loadQuizWords(): List<QuizWord>
    suspend fun loadIncorrectNoteQuizWords(userId: String): List<QuizWord>
}