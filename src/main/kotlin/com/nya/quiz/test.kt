//// SignUpHandler.kt (또는 Main.kt 내부에 함수로 정의)
//
//// 아이디 유효성 검사 함수
//fun isValidUsername(username: String): Pair<Boolean, String> {
//    if (username.isBlank()) {
//        return false to "아이디를 입력해주세요."
//    }
//    if (username.length < 4 || username.length > 10) {
//        return false to "아이디는 4자 이상 10자 이하로 입력해주세요."
//    }
//    if (username.contains(",")) { // 파일 저장 형식에 쉼표 사용하므로 아이디에 쉼표 금지
//        return false to "아이디에는 쉼표(,)를 사용할 수 없습니다."
//    }
//    if (!username.all { it.isLetterOrDigit() }) { // 영문/숫자만 허용 (선택적)
//        return false to "아이디는 영문자와 숫자만 사용할 수 있습니다."
//    }
//    if (AccountFileHandler.isUsernameTaken(username)) {
//        return false to "이미 사용 중인 아이디입니다."
//    }
//    return true to "사용 가능한 아이디입니다."
//}
//
//// 비밀번호 유효성 검사 함수
//fun isValidPassword(password: String): Pair<Boolean, String> {
//    if (password.isBlank()) {
//        return false to "비밀번호를 입력해주세요."
//    }
//    if (password.length < 6) {
//        return false to "비밀번호는 6자 이상이어야 합니다."
//    }
//    // TODO: 더 복잡한 비밀번호 정책 추가 가능 (대소문자, 숫자, 특수문자 조합 등)
//    return true to ""
//}
//
//fun handleSignUp() {
//    println("\n----- 회원가입 -----")
//    var username = ""
//    var password = ""
//
//    // 1. 아이디 입력 및 검사
//    while (true) {
//        print("사용할 아이디를 입력하세요 (4~12자, 영문/숫자): ")
//        val inputUsername = readLine()?.trim() ?: ""
//        val (isValid, message) = isValidUsername(inputUsername)
//        println(message)
//        if (isValid) {
//            username = inputUsername
//            break
//        }
//    }
//
//    // 2. 비밀번호 입력 및 검사
//    while (true) {
//        print("사용할 비밀번호를 입력하세요 (6자 이상): ")
//        val inputPassword = readLine() ?: ""
//        val (isValid, message) = isValidPassword(inputPassword)
//        if (message.isNotBlank()) println(message)
//        if (isValid) {
//            password = inputPassword
//            break
//        }
//    }
//
//    // 3. 비밀번호 재입력 확인
//    while (true) {
//        print("비밀번호를 다시 한번 입력하세요: ")
//        val confirmPassword = readLine() ?: ""
//        if (password == confirmPassword) {
//            println("비밀번호가 확인되었습니다.")
//            break
//        } else {
//            println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.")
//        }
//    }
//
//    // 4. 모든 검사 통과 시 계정 저장
//    if (AccountFileHandler.saveAccount(username, password)) {
//        println("회원가입이 완료되었습니다! 메인 메뉴로 돌아갑니다.")
//    } else {
//        println("회원가입에 실패했습니다. 다시 시도해주세요.")
//    }
//    println("------------------")
//    // 메인 메뉴로 복귀는 main 루프에서 자연스럽게 이루어짐
//}
//
//// Main.kt
//
//fun displayMainMenu() {
//    println("\n===== 영단어 퀴즈 프로그램 =====")
//    println("1. 로그인")
//    println("2. 회원가입")
//    println("3. 도움말")
//    println("4. 종료")
//    print("선택: ")
//}
//
//fun displayHelp() {
//    println("\n----- 도움말 -----")
//    println("이 프로그램은 간단한 영단어 퀴즈 및 회원 관리 기능을 제공합니다.")
//    println("- 로그인: 등록된 계정으로 로그인합니다. (구현 예정)")
//    println("- 회원가입: 새로운 계정을 만듭니다.")
//    println("- 종료: 프로그램을 종료합니다.")
//    println("------------------")
//}
//
//fun handleLogin() {
//    println("\n----- 로그인 -----")
//    print("아이디: ")
//    val username = readLine()?.trim() ?: ""
//    print("비밀번호: ")
//    val password = readLine() ?: ""
//
//    if (AccountFileHandler.findUser(username, password)) {
//        println("로그인 성공! (환영합니다, $username 님)") // 실제 서비스 로직으로 연결
//    } else {
//        println("아이디 또는 비밀번호가 일치하지 않거나 존재하지 않는 계정입니다.")
//    }
//    println("------------------")
//}
//
//
//fun main() {
//    // 프로그램 시작 시 AccountFileHandler 초기화 (파일 존재 여부 확인 및 생성)
//    AccountFileHandler // object는 처음 접근될 때 init 블록이 실행됨
//
//    var running = true
//    while (running) {
//        displayMainMenu()
//        when (readLine()?.trim()) {
//            "1" -> {
//                // 로그인 처리
//                println("로그인 기능은 준비 중입니다.")
//                handleLogin()
//            }
//            "2" -> {
//                // 회원가입 처리
//                handleSignUp()
//            }
//            "3" -> {
//                // 도움말 표시
//                displayHelp()
//            }
//            "4" -> {
//                println("프로그램을 종료합니다.")
//                running = false // 루프 종료
//            }
//            else -> {
//                println("잘못된 입력입니다. 1, 2, 3, 4 중 하나를 입력해주세요.")
//            }
//        }
//        if (running) {
//            println("\n(계속하려면 아무 키나 누르세요...)")
//            readLine() // 화면 전환 전 잠시 멈춤 (사용자가 결과를 볼 수 있도록)
//            // 콘솔 화면 지우기 (선택적)
//            // print("\u001b[H\u001b[2J")
//            // System.out.flush()
//        }
//    }
//}

//package com.nya.quiz
//
//import com.nya.quiz.commons.StartViewState
//import com.nya.quiz.viewmodels.startVIewModels.EntryStartViewModel
//import com.nya.quiz.viewmodels.startVIewModels.HelpViewModel
//import com.nya.quiz.viewmodels.startVIewModels.LoginViewModel
//import com.nya.quiz.viewmodels.startVIewModels.SignUpViewModel
//import com.nya.quiz.views.startview.EntryMain
//
//fun main() {
//    val entryMain = EntryMain()
//    val loginViewModel = LoginViewModel()
//    val signUpViewModel = SignUpViewModel()
//    val helpViewModel = HelpViewModel()
//    val entryMainViewModel = EntryStartViewModel(
//        entryMain,
//        loginViewModel,
//        signUpViewModel,
//        helpViewModel
//    )
//
//    entryMainViewModel.selectStartMenu()
//}