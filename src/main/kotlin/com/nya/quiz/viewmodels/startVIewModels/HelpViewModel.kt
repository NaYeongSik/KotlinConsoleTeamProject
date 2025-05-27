package com.nya.quiz.viewmodels.startVIewModels

import com.nya.quiz.views.startview.Help

class HelpViewModel {
    private val help = Help()
    fun help(){
        help.announce()
        help.undo()
    }
}
