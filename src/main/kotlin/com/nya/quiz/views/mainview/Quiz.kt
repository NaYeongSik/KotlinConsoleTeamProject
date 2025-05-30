package com.nya.quiz.views.mainview

import com.nya.quiz.commons.QuizCounter
import com.nya.quiz.commons.QuizTimeLimit
import com.nya.quiz.models.QuizWord
import com.nya.quiz.startProgram
import com.nya.quiz.viewmodels.mainViewModels.QuizViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class QuizView(private val viewModel: QuizViewModel){

    fun show(){
        println("=== 퀴즈 풀이를 시작합니다 ===")
        // 문제 수 선택
        val quizCounter = selectQuizCounter()
        // 시간 제한 선택
        val timeLimit = selectQuizTimeLimit()
        viewModel.setTimeLimit(timeLimit)
        // 문제 로드
        viewModel.loadQuizWords()
        val quizList = viewModel.getRandomQuiz(quizCounter)
        // 퀴즈 진행
        solveQuiz(quizList, viewModel.getTimeLimitSec())
        // 이어서 풀기
        askContinueOrFinish()
    }

    private fun selectQuizCounter(): QuizCounter {
        println("문제 수를 선택하세요: 1. 10문제   2. 20문제  3. 30문제")
        return when (readLine()?.trim()) {
            "1" -> QuizCounter.SHORT
            "2" -> QuizCounter.MIDDLE
            "3" -> QuizCounter.LONG
            else -> QuizCounter.SHORT
        }
    }

    private fun selectQuizTimeLimit(): QuizTimeLimit {
        println("문제당 제한시간을 선택하세요: 1. 15초   2. 30초   3. 45초")
        return when (readLine()?.trim()){
            "1" -> QuizTimeLimit.SHORT
            "2" -> QuizTimeLimit.MIDDLE
            "3" -> QuizTimeLimit.LONG
        else -> QuizTimeLimit.SHORT
        }
    }

    private fun solveQuiz(quizList: List<QuizWord>, timeLimitSec: Int) {
        val totalQuiz = quizList.size
        var correct = 0
        var wrong = 0
        var isStopped = false

        for ((idx, quiz) in quizList.withIndex()) {
            println("\n[${idx + 1}] ${quiz.word} : (뜻을 입력하세요, 제한시간 ${timeLimitSec}초)" +
                    "\nPASS는 엔터를 눌러주세요(오답처리 됩니다.)" +
                    "\n풀이를 중단하려면 0번을 입력해주세요")
            var userInput: String? = null
            val inputJob = CoroutineScope(Dispatchers.IO).launch {
                userInput = readLine()
            }
            var timeout = false

            viewModel.startTimer(
                timeLimitSec,
                onTick = { left -> print("\r남은 시간: ${left}초   ") },
                onTimeout = {
                    timeout = true
                    println("\n시간 초과!\n엔터를 눌러주세요")
                    inputJob.cancel()
                }
            )

            runBlocking {
                inputJob.join()
                viewModel.stopTimer()
            }

            if(userInput?.trim() == "0"){
                if(confirmStopQuiz()){
                    isStopped = true
                    break
                }else{
                    continue
                }
            }

            if (timeout || userInput == null) {
                println("오답! 정답: ${quiz.meanings.joinToString(", ")}")
                wrong++
                viewModel.saveIncorrectWord(quiz)
            } else if (viewModel.isCorrectAnswer(userInput!!, quiz)) {
                println("정답!")
                correct++
            } else {
                println("오답! 정답: ${quiz.meanings.joinToString(", ")}")
                wrong++
                viewModel.saveIncorrectWord(quiz)
            }
        }
        println("\n퀴즈 종료!")
        println("총 문제: $totalQuiz")
        println("맞은 문제: $correct")
        println("틀린 문제: $wrong")


        if (isStopped) {
            println("풀이가 중단되었습니다. 메인 메뉴로 돌아갑니다.")
        }

    }

    fun askContinueOrFinish() {
        println("퀴즈를 이어서 풀겠습니까? (1: 이어서 풀기 / 2: 종료)")
        when(readLine()?.trim()){
            "1" -> show()
            "2" -> null
            else -> {
                println("잘못된 입력입니다")
                askContinueOrFinish()
            }
        }
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