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
        else -> return true
    }
    return false
}


//fun isValid(line: String): Boolean {
//    return when(StateManager.getState()){
//        ViewState.START_VIEW -> line.trim().matches(START_MENU_REGEX.toRegex())
//        ViewState.MAIN_VIEW -> line.trim().matches(MAIN_MENU_REGEX.toRegex())
//        ViewState.END_VIEW -> false
//    }
//}
//package com.nya.quiz.commons
//
//import java.io.File
//
//class Validator() {
//
//    val regex = "[^a-zA-Z0-9]".toRegex()
//    fun createId(value: String): Boolean {
//        if (value.length <= 10 && value.contains(regex) && !value.contains(*filedata *)) {
//            checkExistId(value)
//        }else {
//            throw println("잘못된 입력입니다."))
//        }
//    }
//
//    fun checkExistId(input: String): Boolean {
//        val accountFile = File("src\\main\\resources\\account.txt")
//        var accountList = mutableListOf<T>()
//        accountFile.forEachLine { accountList.add(it.split(",")[0]) }
//        if (accountList.contains(input)) true else false
//
//    }
//
//
//    fun checkPassword(value: String): Boolean {
//        if (value.length <= 10 && value.contains(regex)) {
//            println("사용가능한 비밀번호 입니다.")
//            return true
//        }
//        println("잘못된 입력입니다.")
//        return false
//    }
//
//    fun checkPasswordRepeat(value: String): Boolean {
//        if (value.length <= 10 && value.contains(*eng, num *) && !value.contains(*filedata *)) {
//            println("비밀번호가 일치합니다.")
//            return true
//        }
//        println("잘못된 입력입니다.")
//        return false
//    }
//}
