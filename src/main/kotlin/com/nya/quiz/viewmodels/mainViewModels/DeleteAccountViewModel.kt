package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.models.User
import com.nya.quiz.views.mainview.DeleteAccount
import com.nya.quiz.models.User.accountFile
import com.nya.quiz.models.User.accountStatFile
import com.nya.quiz.views.startview.EntryMain


class DeleteAccountViewModel(id: String) {
    val deleteAccount = DeleteAccount()
    val entryMain = EntryMain()

    fun deleteAccount(): Boolean {
        deleteAccount.recheckMessage()
        while (true) {
            val input = ConsoleManager.consoleLine()
            if (input == "1") {
                val accountsToKeep = accountFile.readLines().filter { line ->
                    line.split(',')[0] != User.getId()
                }
                accountFile.writeText(accountsToKeep.joinToString(separator = "\n"))
                accountFile.appendText("\n")
                val statusToKeep = accountStatFile.readLines().filter { line ->
                    line.split('|')[0] != User.getId()
                }
                accountStatFile.writeText(statusToKeep.joinToString(separator = "\n"))
                accountStatFile.appendText("\n")

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
