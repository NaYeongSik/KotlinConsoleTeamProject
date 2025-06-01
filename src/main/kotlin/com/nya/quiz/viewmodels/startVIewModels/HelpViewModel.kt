package com.nya.quiz.viewmodels.startVIewModels

object HelpViewModel {

    fun help() {
        println("이 프로그램은 수능 필수 영단어 퀴즈 프로그램입니다. ")
        println("아래는 주요 기능과 사용 방법입니다.")
        println("[1] 풀이 시작")
        println("로그인 후 메인 메뉴에서 [1]퀴즈시작을 선택합니다.")
        println("한 게임에 문제 수(10회, 20회, 30회)를 설정 후 퀴즈가 시작됩니다.")
        println("[2] 오답 다시 풀기")
        println("틀린 문제를 다시 풀어봅니다. (통계에 기록되지 않습니다)")
        println("[3] 오답노트")
        println("틀린 단어와 뜻을 보며 다시 공부합니다.")
        println("추가로 오답노트 기록을 초기화 할 수 있습니다.")
        println("[4] 순위")
        println("사용자별 정답률과 맞힌 문제 수를 기준으로 순위를 보여줍니다.")
        println("그리고 현재 나의 순위를 보여줍니다.\n")
        println("시작메뉴로 돌아갑니다.\n")
    }
}
