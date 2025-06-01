package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.QuizCounter
import com.nya.quiz.commons.QuizStat
import com.nya.quiz.commons.QuizTimeLimit
import com.nya.quiz.commons.ViewState
import com.nya.quiz.file.IncorrectNoteFileManager
import com.nya.quiz.file.QuizFileManager
import com.nya.quiz.models.QuizWord
import com.nya.quiz.models.rank.RankingRepositoryImpl
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
            while (timeLeft >= 0) {
                withContext(Dispatchers.Default) { onTick(timeLeft)}
                delay(1000)
                timeLeft--
            }
            withContext(Dispatchers.Default) { onTimeout() }
        }
    }

    fun stopTimer(){
        timerJob?.cancel()
    }

    fun getRandomQuiz(counter: QuizCounter): List<QuizWord> = quizWords.shuffled().take(counter.quizNum)

    fun isCorrectAnswer(userInput: String, quizWord: QuizWord): Boolean {
        return quizWord.meanings.any { userInput.trim() == it.trim() }
    }


    fun saveIncorrectWord(quizWord: QuizWord) {
        if (quizWord !in incorrectWords){
            incorrectWords.add(quizWord)
            RankingRepositoryImpl.profile.incorrectQuiz = RankingRepositoryImpl.profile.incorrectQuiz.plus(incorrectWords.toString())
        }
    }
    fun saveCorrectCount(correct: Int){
        RankingRepositoryImpl.profile.correctCount += correct
    }
    fun saveIncorrectCount(incorrect: Int){
        RankingRepositoryImpl.profile.incorrectCount += incorrect
    }

    // 정답률 계산
    fun calculateCorrectRate(correct: Int, total: Int): Float {
        if (total == 0) return 0f
        return (correct.toFloat() / total.toFloat()) * 100f
    }

    //정답률 저장
    fun saveCalculateCorrectRate(correctRate: Float){
        RankingRepositoryImpl.profile.correctRate = correctRate
    }
}