package com.nya.quiz.views.startview

// 어느 상황에서든 특정 커맨드입력시 중단기능 추가할것
class EntryMain() {
    fun startMessage() {
        println("메뉴를 선택하세요.\n[1]로그인 [2]회원가입 [3]도움말 [0]종료")
    }

    fun mainMessage() {
        println("메뉴를 선택하세요.\n[1]퀴즈시작 [2]오답풀기 [3]오답노트 [4]순위 [5]로그아웃 [6]계정탈퇴 [7]종료")
    }
    fun errorMessage(){
        println("다시 입력하세요.")
    }

}
