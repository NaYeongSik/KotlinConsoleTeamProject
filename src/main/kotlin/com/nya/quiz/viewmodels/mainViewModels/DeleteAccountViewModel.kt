package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.views.mainview.DeleteAccount
import com.nya.quiz.models.User.accountFile
import com.nya.quiz.views.startview.EntryMain


class DeleteAccountViewModel(connectingId: String) {
    val deleteAccount = DeleteAccount()
    val entryMain = EntryMain()
    val currentId = connectingId

    fun deleteAccount(): Boolean {
        deleteAccount.recheckMessage()
        while (true) {
            val input = ConsoleManager.consoleLine()
            if (input == "1") {
                accountFile.useLines { lines ->
                    lines.forEach { line ->
                        val parts = line.split(',')[0]
                        if (parts == currentId) {
                            accountFile.writeText(lines.joinToString("\n"))
                        }
                    }
                }
                deleteAccount.completeMessage()
                return true
            }
            if (input == "2") {
                return false
            }
            entryMain.errorMessage()
        }
    }
}
