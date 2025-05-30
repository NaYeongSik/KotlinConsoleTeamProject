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

    override fun recordRanking(data: QuizStat): Boolean {
        runCatching {
            incorrectNoteFileManager.writeFile(data.toString())
        }.onSuccess { return true }.onFailure { return false }
        return false
    }

    override fun updateRanking(updateData: QuizStat): Boolean {
        runCatching {
            var totalData = incorrectNoteFileManager.readFile() ?: emptyList()
            var strBuilder = kotlin.text.StringBuilder()

            for (data:String in totalData){
                if (data.isNotBlank()) {
                    if (!data.split("|")[0].trim().equals(updateData.userId)) {
                        strBuilder.append(data)
                        strBuilder.append("\n")
                    } else {
                        strBuilder.append(updateData.toString())
                        strBuilder.append("\n")
                    }
                }
            }
            incorrectNoteFileManager.updateFile(strBuilder.toString())
        }.onSuccess { return true }.onFailure { return false }
        return false
    }

    override fun deleteInfo(id: String): Boolean {
        runCatching {
            var totalData = incorrectNoteFileManager.readFile() ?: emptyList()
            var strBuilder = kotlin.text.StringBuilder()

            for (data:String in totalData){
                if (data.isNotBlank()) {
                    if (!data.split("|")[0].trim().equals(id)) {
                        strBuilder.append(data)
                        strBuilder.append("\n")
                    }
                }
            }

            incorrectNoteFileManager.updateFile(strBuilder.toString())
        }.onSuccess { return true }.onFailure { return false }
        return false
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