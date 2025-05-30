package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.models.User
import com.nya.quiz.views.mainview.DeleteAccount
import com.nya.quiz.models.User.accountFile
import com.nya.quiz.views.startview.EntryMain


class DeleteAccountViewModel(connectingId: String) {
    val deleteAccount = DeleteAccount()
    val entryMain = EntryMain()

    fun deleteAccount(): Boolean {
        deleteAccount.recheckMessage()
        while (true) {
            val input = ConsoleManager.consoleLine()
            if (input == "1") {
                val linesToKeep = accountFile.readLines().filter { line ->
                    line.split(',')[0] != User.getId()
                }
                accountFile.writeText(linesToKeep.joinToString(separator = "\n"))
                accountFile.appendText("\n")
                deleteAccount.completeMessage()
                User.logout()
                return true
            }
            if (input == "2") {
                return false
            }
            entryMain.errorMessage()
        }
    }
}
