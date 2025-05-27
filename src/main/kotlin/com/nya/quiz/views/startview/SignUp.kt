//package com.nya.quiz.views.startview
//
//import com.nya.quiz.commons.Validator
//import java.io.File
//import java.util.Scanner
//import kotlin.collections.contains
//
//class SignUp() {
//    var inputId: String = ""
//    var inputPassword: String = ""
//    val validator = Validator()
//
//    fun newId() {
//        while (true) {
//            validator.createId(checkId())
//            validator.checkExistId(inputId)
//            validator.checkPassword(checkPassword())
//            inputPassword == checkPasswordRepeat()
//            newAccount(inputId, inputPassword)
//        }
//
//    }
//}
//
//
//private fun checkId(): String {
//    println("아이디를 입력하세요.")
//    var inputId = Scanner(System.`in`).toString()
//    return inputId
//}
//
//private fun checkPassword(): String {
//    println("비밀번호를 입력하세요.")
//    var inputPassword = Scanner(System.`in`).toString()
//    return inputPassword
//}
//
//private fun checkPasswordRepeat(): String {
//    println("비밀번호를 한번 더 입력하세요.")
//    var inputPassword = Scanner(System.`in`).toString()
//    return inputPassword
//}
//
//private fun newAccount(input: String, inputPassword: String) {
//    println("계정이 생성되었습니다.")
//
//    val accountFile = File("src\\main\\resources\\account.txt")
//    if (!accountFile.exists()) {
//        val path = System.getProperty("user.dir") + "src\\main\\resources"
//
//
//        val write = "${input},${inputPassword}"
//        accountFile.appendText(write)
//    }
//}
