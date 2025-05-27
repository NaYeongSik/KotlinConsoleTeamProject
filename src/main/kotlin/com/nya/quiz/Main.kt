package com.nya.quiz

import com.nya.quiz.commons.ViewState
import com.nya.quiz.file.QuizFileManager
import com.nya.quiz.viewmodels.mainViewModels.QuizViewModel
import com.nya.quiz.views.mainview.QuizView

fun main(){
    val quiz = QuizView(QuizViewModel())
    quiz.show()
}