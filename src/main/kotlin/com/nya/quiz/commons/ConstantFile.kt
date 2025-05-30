package com.nya.quiz.commons

// 콘솔 메시지
const val START_MENU_MESSAGE = "퀴즈 콘솔프로그램 \n[1] 로그인  [2] 회원가입  [3] 도움말  [4] 종료"
const val MAIN_MENU_MESSAGE = "[1] 풀이 시작  [2] 오답 다시 풀기  [3] 오답노트  [4] 순위  [5] 로그아웃  [6] 계정 탈퇴  [7] 종료"
const val END_MESSAGE = "프로그램을 종료합니다."
const val INVALID_MESSAGE = "유효하지 않은 입력 값 입니다."


// 유효성 검사 (정규표현식)
const val START_MENU_REGEX = "^\\s*[1-4]\\s*\$" // 숫자 1~4 및 앞뒤로 공백 허용
const val MAIN_MENU_REGEX = "^\\s*[1-7]\\s*\$" // 숫자 1~7 및 앞뒤로 공백 허용