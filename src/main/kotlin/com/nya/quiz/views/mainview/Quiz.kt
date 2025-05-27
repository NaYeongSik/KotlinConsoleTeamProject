package com.nya.quiz.views.mainview

import com.nya.quiz.commons.QuizCounter
import com.nya.quiz.commons.QuizTimeLimit
import com.nya.quiz.models.QuizWord
import com.nya.quiz.viewmodels.mainViewModels.QuizViewModel

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
        for ((idx, quiz) in quizList.withIndex()) {
            println("\n[${idx + 1}] ${quiz.word} : (뜻을 입력하세요, 제한시간 ${timeLimitSec}초)")
            val userInput = readLine() ?: ""
            if (viewModel.isCorrectAnswer(userInput, quiz)) {
                println("정답!")
                correct++
            } else {
                println("오답! 정답: ${quiz.meanings.joinToString(",")}")
                wrong++
                viewModel.saveIncorrectWord(quiz)
            }
        }
        println("\n퀴즈 종료!")
        println("총 문제: $totalQuiz")
        println("맞은 문제: $correct")
        println("틀린 문제: $wrong")
    }
}