package com.nya.quiz.views.mainview

import com.nya.quiz.commons.QUIZ_FILE_REGEX
import com.nya.quiz.models.QuizWord
import com.nya.quiz.models.rank.RankingRepositoryImpl
import com.nya.quiz.viewmodels.mainViewModels.RetryIncorrectWordViewModel

class RetryIncorrectWordView(private val viewModel: RetryIncorrectWordViewModel) {

    fun show() {
        val incorrectQuiz = RankingRepositoryImpl.profile.incorrectQuiz
        if (incorrectQuiz.isEmpty()) {
            println("저장된 오답문제가 없어 풀이가 불가능합니다.")
            return
        }
        println("=== 퀴즈 풀이를 시작합니다 ===")
        // 문제 수 선택
        val quizCounter = selectQuizCounter()
        // 유저 아이디
        val userId = RankingRepositoryImpl.profile.userId
        // 문제 로드
        viewModel.loadIncorrectNoteQuizWords(userId)
        val quizList = viewModel.getRandomQuiz(quizCounter)
        // 퀴즈 진행
        solveQuiz(quizList)
    }

    private fun selectQuizCounter(): Int {
        val incorrectQuiz = RankingRepositoryImpl.profile.incorrectQuiz
        val lastQuizListStr = incorrectQuiz.lastOrNull()
        val regex = Regex(QUIZ_FILE_REGEX)
        val incorrectSize = if (lastQuizListStr != null) regex.findAll(lastQuizListStr).count() else 0

        return if (incorrectSize < 10) {
            println("현재 오답이 10개 이하이기 때문에, 모든 오답 문제를 출제합니다 \n 현재 오답${incorrectSize}개")
            incorrectSize
        } else {
            println("문제 수를 선택하세요: 1. 10문제 2. 20문제 3. 30문제\n 현재 오답수 $incorrectSize")
            when (readLine()?.trim()) {
                "1" -> 10
                "2" -> 20
                "3" -> 30
                else -> {
                    println("잘못된 입력입니다.")
                    selectQuizCounter()
                }
            }
        }
    }

    private fun solveQuiz(quizList: List<QuizWord>){
        val totalQuiz = quizList.size
        var correct = 0
        var wrong = 0
        var isStopped = false
        var idx = 0

        while (idx < quizList.size) {
            val quiz = quizList[idx]

            println(
                "\n[${idx + 1}] ${quiz.word} : (뜻을 입력하세요, 문제수 ${totalQuiz}개)" +
                        "\nPASS는 엔터를 눌러주세요(오답처리 됩니다.)" +
                        "\n풀이를 중단하려면 0번을 입력해주세요"
            )

            val userInput = readLine()

            if (userInput?.trim() == "0") {
                if(confirmStopQuiz()) {
                    isStopped = true
                    break
                } else {
                    continue
                }
            }

            if (userInput == null){
                println("오답! 정답: ${quiz.meanings.joinToString (", ")}")
                wrong++
                idx++
            } else if (viewModel.isCorrectAnswer(userInput,quiz)){
                println("정답!")
                correct++
                idx++
            } else {
                println("오답! 정답: ${quiz.meanings.joinToString(", ")}")
                wrong++
                idx++
            }
        }

        if (isStopped) {
            println("풀이가 중단되었습니다.")
        }

        println("\n퀴즈 종료!")
        println("총 문제: $totalQuiz")
        println("맞은 문제: $correct")
        println("틀린 문제: $wrong")
    }

    private fun confirmStopQuiz(): Boolean {
        while (true) {
            println("정말 풀이를 종료하시겠습니까? (1: 예 / 2: 아니오)")
            when (readLine()?.trim()) {
                "1" -> return true  // 종료
                "2" -> return false // 계속 풀이
                else -> println("잘못된 입력입니다. 다시 입력해 주세요.")
            }
        }
    }
}