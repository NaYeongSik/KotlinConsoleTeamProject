package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.file.IncorrectNoteFileManager
import com.nya.quiz.models.QuizWord

class RetryIncorrectWordViewModel{
    private val incorrectNoteFileManager = IncorrectNoteFileManager()


    private val incorrectWords = mutableListOf<QuizWord>()


}