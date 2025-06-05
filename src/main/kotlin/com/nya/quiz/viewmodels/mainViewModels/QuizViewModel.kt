package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.*
import com.nya.quiz.file.QuizFileManager
import com.nya.quiz.interfaces.QuizRepository
import com.nya.quiz.models.quiz.QuizRepositoryImpl
import com.nya.quiz.models.quiz.QuizWord
import com.nya.quiz.models.rank.RankingRepositoryImpl
import kotlinx.coroutines.*

class QuizViewModel(
) {
    var quizWords: List<QuizWord> = emptyList()
        private set

    private var timeLimitSec: Int = QuizTimeLimit.SHORT.time
    private val incorrectWords = mutableListOf<QuizWord>()
    private var timerJob: Job? = null



    suspend fun loadQuizWords(){
        quizWords = QuizRepositoryImpl.loadQuizWords()
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

    // 퀴즈 시작 전 기존 오답 불러오는 함수
    fun loadIncorrectWordsFromProfile() {
        val lastIncorrectListStr = RankingRepositoryImpl.profile.incorrectQuiz.lastOrNull()
        if (lastIncorrectListStr != null && lastIncorrectListStr.isNotBlank()) {
            val regex = quizWordRegex()
            val parsed = regex.findAll(lastIncorrectListStr).map { match ->
                val word = match.groupValues[1].trim()
                val meanings = match.groupValues[2].split(',').map { it.trim() }
                QuizWord(word, meanings)
            }.toMutableList()
            incorrectWords.clear()
            incorrectWords.addAll(parsed)
        } else {
            incorrectWords.clear()
        }
    }

    fun saveIncorrectWord(quizWord: QuizWord) {
        if (quizWord !in incorrectWords){
            incorrectWords.add(quizWord)
        }
        // 중복 제거
        val distinctIncorrectWords = incorrectWords.distinct()
        RankingRepositoryImpl.profile.incorrectQuiz =
            RankingRepositoryImpl.profile.incorrectQuiz.dropLast(1) + listOf(distinctIncorrectWords.toString())
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