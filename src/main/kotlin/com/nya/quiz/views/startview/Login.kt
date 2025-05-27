//package com.nya.quiz.views.startview
//
//import java.util.Scanner
//import com.nya.quiz.commons.Validator
//
//private var Login.inputPassword: Scanner
//private var Login.inputId: Scanner
//
//class Login {
//    constructor(
//        inputId: String = "",
//        inputPassword: String = ""
//    )
//
//    fun login() {
//        while (true) {
//            if (checkId()) {
//                if (checkPassword()) {
//                    return CheckAccount(inputId)
//                }
//            }
//        }
//    }
//
//    private fun checkId(): Validator {
//        println("아이디를 입력하세요.")
//        inputId = Scanner(System.`in`)
//        return Validator(inputId)
//    }
//
//    private fun checkPassword(): Validator {
//        println("비밀번호를 입력하세요.")
//        inputPassword = Scanner(System.`in`)
//        return Validator(inputPassword)
//    }
//}
