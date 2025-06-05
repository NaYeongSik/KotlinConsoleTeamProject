package com.nya.quiz.models

import com.nya.quiz.file.AccountFileManager
import com.nya.quiz.file.IncorrectNoteFileManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


object UserRepository {
    val accountFileManager = AccountFileManager()
    val incorrectNoteFileManager = IncorrectNoteFileManager()

    fun saveAccount(account: String,userStat: String){
        runBlocking(Dispatchers.IO) {
            accountFileManager.writeFile(account)
            incorrectNoteFileManager.writeFile(userStat)
        }
    }

    fun getTotalAccountInfo(): List<String>{
        return runBlocking(Dispatchers.IO) {
             accountFileManager.readFile() ?: emptyList()
        }
    }

    fun updateAccountFile(account: String, userStat: String){
        runBlocking(Dispatchers.IO) {
            accountFileManager.updateFile(account)
            incorrectNoteFileManager.updateFile(userStat)
        }
    }

}