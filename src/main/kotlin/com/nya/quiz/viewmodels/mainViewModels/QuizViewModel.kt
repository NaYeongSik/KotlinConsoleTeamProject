package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.QuizCounter
import com.nya.quiz.commons.QuizTimeLimit
import com.nya.quiz.commons.ViewState
import com.nya.quiz.file.QuizFileManager
import com.nya.quiz.models.QuizWord
import com.nya.quiz.models.SolveQuiz
import kotlinx.coroutines.*

class QuizViewModel {
    private val quizFileManager = QuizFileManager()
    var quizWords: List<QuizWord> = emptyList()
    private set

    private var timeLimitSec: Int = QuizTimeLimit.SHORT.time

    private val incorrectWords = mutableListOf<QuizWord>()

    private var timerJob: Job? = null


    fun loadQuizWords(){
        val lines = quizFileManager.readFile() ?: emptyList()
        quizWords = lines.mapNotNull { line ->
            val parts = line.split('\t')
            if (parts.size < 3) return@mapNotNull null
            val word = parts[1].trim()
            val meanings = parts[2]
                .split(',')
                .map { it.replace(Regex("\\([^)]*\\)"), "").trim() }
            QuizWord(word, meanings)
        }
    }

    fun setTimeLimit(timeLimit: QuizTimeLimit){
        timeLimitSec = timeLimit.time
    }

    fun getTimeLimitSec(): Int = timeLimitSec

    fun startTimer(
        timeLimitSec: Int,
        onTick: (Int) -> Unit,
        onTimeout: () -> Unit
    ){
        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            var timeLeft = timeLimitSec
            while (timeLeft > 0) {
            }
        }
    }

    fun getRandomQuiz(counter: QuizCounter): List<QuizWord> = quizWords.shuffled().take(counter.quizNum)

    fun isCorrectAnswer(userInput: String, quizWord: QuizWord): Boolean {
        return quizWord.meanings.any { userInput.trim() == it.trim() }
    }

    fun getCorrectAnswerCount() = quizWords.size

    fun saveIncorrectWord(quizWord: QuizWord) {
        if (quizWord !in incorrectWords){
            incorrectWords.add(quizWord)
        }
    }

    fun getIncorrectWords(): List<QuizWord> = incorrectWords

    fun stopSolving(){
        ViewState.MAIN_VIEW
    }

    fun continueSolving(){

    }

    fun finishSolving(){

    }

}
// 문제수 설정 / 시간 설정 / 풀이 중단 / 이어서 풀기 / 풀이 종료 / 답변 저장 / 틀린 문제 저장 /