package com.nya.quiz.models.rank

import com.nya.quiz.commons.DataMapper
import com.nya.quiz.commons.QuizStat
import com.nya.quiz.file.IncorrectNoteFileManager
import com.nya.quiz.interfaces.rank.UserStatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object UserStatRepositoryImpl : UserStatRepository {

    private val incorrectNoteFileManager: IncorrectNoteFileManager = IncorrectNoteFileManager()

    lateinit var profile: QuizStat

    override fun getTotalUserInfo(): List<QuizStat> {
        val quizStatsList = mutableListOf<QuizStat>()
        runBlocking(Dispatchers.IO) {
            var totalData = incorrectNoteFileManager.readFile() ?: emptyList()

            for (data: String in totalData) {
                if (data.isNotBlank()) quizStatsList.add(DataMapper.transToQuizStat(data))
            }
        }
        return quizStatsList
    }

    override fun updateUserInfo(updateData: QuizStat): Boolean {
        runCatching {
            runBlocking(Dispatchers.IO) {
                var totalData = incorrectNoteFileManager.readFile() ?: emptyList()
                var strBuilder = kotlin.text.StringBuilder()

                for (data: String in totalData) {
                    if (data.isNotBlank()) {
                        if (data.split("|")[0].trim() != updateData.userId) {
                            strBuilder.append(data)
                            strBuilder.append("\n")
                        } else {
                            strBuilder.append(updateData.toString())
                            strBuilder.append("\n")
                        }
                    }
                }
                incorrectNoteFileManager.updateFile(strBuilder.toString())
            }

        }.onSuccess { setMyProfile(updateData.userId); return true }.onFailure { return false }
        return false
    }

    override fun deleteInfo(id: String): Boolean {
        runCatching {
            runBlocking(Dispatchers.IO) {
                var totalData = incorrectNoteFileManager.readFile() ?: emptyList()
                var strBuilder = StringBuilder()

                for (data: String in totalData) {
                    if (data.isNotBlank()) {
                        if (data.split("|")[0].trim() != id) {
                            strBuilder.append(data)
                            strBuilder.append("\n")
                        } else {
                            strBuilder.append(QuizStat(id).toString())
                            strBuilder.append("\n")
                        }
                    }
                }

                incorrectNoteFileManager.updateFile(strBuilder.toString())
            }

        }.onSuccess { setMyProfile(id); return true }.onFailure { return false }
        return false
    }

    fun setMyProfile(userId: String){
        var totalList = getTotalUserInfo()
        profile = totalList.find {
                it ->
            it.userId == userId
        }?: QuizStat(userId)
    }


}