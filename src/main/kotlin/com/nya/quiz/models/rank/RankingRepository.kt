package com.nya.quiz.models.rank

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.file.IncorrectNoteFileManager
import com.nya.quiz.interfaces.rank.RankingRepository

object RankingRepository : RankingRepository{

    private val incorrectNoteFileManager: IncorrectNoteFileManager = IncorrectNoteFileManager()

    override fun getTotalRanking(): List<QuizStat> {
        var totalData = incorrectNoteFileManager.readFile() ?: emptyList()
        val quizStatsList = mutableListOf<QuizStat>()

        for (data:String in totalData){
            if (data.isNotBlank()) quizStatsList.add(convertToQuizStat(data))
        }
        return quizStatsList
    }

    override fun recordRanking(id: String, score: Float) {
        TODO("Not yet implemented")
        // TODO: 파일에 해당 계정의 점수 수정하기
    }

    override fun deleteInfo(id: String) {
        TODO("Not yet implemented")
        // TODO: 파일에 해당 계정의 정보 지우기
    }

    fun findMyRanking(dataList: List<String>, id: String): QuizStat{
        for (data:String in dataList){
            if (data.isNotBlank()) {
                if (data.split("|")[0].trim().equals(id)) {
                    return convertToQuizStat(data)
                }
            }
        }
        return QuizStat()
    }

    fun convertToQuizStat(data: String): QuizStat{
        var dataList = data.split("|")
        var userId = dataList[0].trim()
        var correctRate = dataList[1].trim().toFloat()
        var correctCount = dataList[2].trim().toInt()
        var incorrectCount = dataList[3].trim().toInt()

        val incorrectQuiz = if (dataList.size > 4) {
            dataList.subList(4, dataList.size).map { it.trim() }.filter { it.isNotEmpty() }
        } else {
            emptyList<String>()
        }
        return QuizStat(userId,correctRate,correctCount,incorrectCount,incorrectQuiz)
    }
}