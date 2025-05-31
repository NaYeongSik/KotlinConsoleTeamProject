package com.nya.quiz.views.mainview

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.commons.INCORRECT_NOTE_INIT_FAIL
import com.nya.quiz.commons.INCORRECT_NOTE_INIT_SUCCESS
import com.nya.quiz.commons.RETURN_MAIN_MENU
import com.nya.quiz.viewmodels.mainViewModels.IncorrectNoteViewModel

class IncorrectNoteView(private val incorrectNoteViewModel: IncorrectNoteViewModel){

    fun showIncorrectNote(){
        println("------------------- 오 답 노 트 -------------------")
        println(incorrectNoteViewModel.getMyIncorrectNote())
        println("--------------------------------------------------")
        showIncorrectNoteMenu()
    }

    fun showIncorrectNoteMenu(){
        println("[1] 내 오답노트 초기화  [2] 메인 메뉴로 돌아가기")
        var inputMenu = ConsoleManager.consoleLine()
        when(inputMenu.trim()){
            "1" -> {
                if (incorrectNoteViewModel.deleteMyIncorrectNote()) println(INCORRECT_NOTE_INIT_SUCCESS)
                else println(INCORRECT_NOTE_INIT_FAIL)
            }
            "2" -> println(RETURN_MAIN_MENU)
        }

    }

}