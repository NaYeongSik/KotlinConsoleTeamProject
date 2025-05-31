package com.nya.quiz.views.mainview

import com.nya.quiz.commons.ConsoleManager
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
            "1" -> if (incorrectNoteViewModel.deleteMyIncorrectNote()) println("내 오답노트를 초기화 했습니다.\n") else println("내 오답노트 초기화를 실패했습니다.\n")
            "2" -> "메인 메뉴로 돌아갑니다.\n"
            else -> "올바른 메뉴를 입력해주세요.\n"
        }

    }

}