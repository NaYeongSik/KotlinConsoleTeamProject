package com.nya.quiz.commons

import com.nya.quiz.models.User

/**
 * Is valid
 * 각 상태에 따라 사용자 입력 값 유효성 검사 (시작 메뉴 1~4, 메인 메뉴 1~7)
 *
 * @param line : 사용자 입력 값
 * @return : 유효성 검사 결과
 */


fun isValid(line: String): Boolean {
    return when (StateManager.getState()) {
        ViewState.START_VIEW -> line.trim().matches(START_MENU_REGEX.toRegex())
        ViewState.MAIN_VIEW -> line.trim().matches(MAIN_MENU_REGEX.toRegex())
        ViewState.END_VIEW -> false
    }
}

fun isValidUsername(username: String): Pair<Boolean, String> {
    if (username.isBlank()) {
        return false to "아이디를 입력해주세요."
    }
    if (username.length < 4 || username.length > 10) {
        return false to "아이디는 4자 이상 10자 이하로 입력해주세요."
    }
    if (!username.all { it.isLetterOrDigit() }) {
        return false to "아이디는 영문자와 숫자만 사용할 수 있습니다."
    }
    if (User.isUsernameTaken(username)) {
        return false to "이미 사용 중인 아이디입니다."
    }
    return true to "사용 가능한 아이디입니다."
}

fun isValidPassword(password: String): Pair<Boolean, String> {
    if (password.isBlank()) {
        return false to "비밀번호를 입력해주세요."
    }
    if (password.length < 6) {
        return false to "비밀번호는 6자 이상이어야 합니다."
    }
    return true to ""
}
