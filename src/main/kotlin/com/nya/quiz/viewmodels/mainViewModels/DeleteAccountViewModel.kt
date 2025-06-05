package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.commons.INPUT_NO
import com.nya.quiz.commons.INPUT_YES
import com.nya.quiz.models.User
import com.nya.quiz.views.mainview.DeleteAccount
import com.nya.quiz.models.User.accountFile
import com.nya.quiz.models.User.accountStatFile
import com.nya.quiz.views.startview.EntryMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DeleteAccountViewModel(id: String) {
    val deleteAccount = DeleteAccount()
    val entryMain = EntryMain()

    suspend fun deleteAccount(): Boolean {
        deleteAccount.recheckMessage()
        while (true) {
            val input = withContext(Dispatchers.IO) {
                ConsoleManager.consoleLine()
            }
            if (input == INPUT_YES) {
                User.deleteAccount()
                deleteAccount.completeMessage()
                User.logout()
                return true
            }
            if (input == INPUT_NO) {
                return false
            }
            entryMain.errorMessage()
        }
    }
}