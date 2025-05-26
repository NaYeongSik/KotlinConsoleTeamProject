package com.nya.quiz.commons

/**
 * Is valid
 * 각 상태에 따라 사용자 입력 값 유효성 검사 (시작 메뉴 1~4, 메인 메뉴 1~7)
 *
 * @param line : 사용자 입력 값
 * @return : 유효성 검사 결과
 */
fun isValid(line: String): Boolean {
    when(StateManager.getState()){
        ViewState.START_VIEW -> return line.trim().matches(START_MENU_REGEX.toRegex())
        ViewState.MAIN_VIEW -> return line.trim().matches(MAIN_MENU_REGEX.toRegex())
        ViewState.END_VIEW -> return false
    }
    return false
}