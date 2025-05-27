package com.nya.quiz.commons

import java.util.Scanner

/**
 * Console manager
 * 사용자 입력부분 처리 부분을 싱글톤 패턴으로 구현
 * 콘솔 관련 처리할 내용을 하나로 관리하려고 만들어봤습니다. (추가가 필요한 부분이나 수정이 필요한 부분 있을 수 있어요)
 * @constructor Create empty Console manager
 */
object ConsoleManager {
    private  val scanner  = Scanner(System.`in`)

    fun consoleLine(): String = scanner.nextLine()

}