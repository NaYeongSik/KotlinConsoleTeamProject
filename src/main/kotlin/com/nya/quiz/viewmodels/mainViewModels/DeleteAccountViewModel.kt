package com.nya.quiz.viewmodels.mainViewModels

import com.nya.quiz.commons.ConsoleManager
import com.nya.quiz.models.User
import com.nya.quiz.models.UserRepository
import com.nya.quiz.models.rank.UserStatRepositoryImpl
import com.nya.quiz.views.mainview.DeleteAccount
import com.nya.quiz.views.startview.EntryMain


class DeleteAccountViewModel() {
    val deleteAccount = DeleteAccount()
    val entryMain = EntryMain()

    fun deleteAccount(): Boolean {
        deleteAccount.recheckMessage()
        while (true) {
            val input = ConsoleManager.consoleLine()
            if (input == "1") {
                val totalAccountData = UserRepository.getTotalAccountInfo()
                val totalUserStatData = UserStatRepositoryImpl.getTotalUserInfo()
                val accountStr = StringBuilder()
                val userStatStr = StringBuilder()

                for (accountData in totalAccountData){
                    if (accountData.split(",")[0] != User.getId()) accountStr.append(accountData + "\n")
                }

                for (userStatData in totalUserStatData){
                    if (userStatData.userId != User.getId()) userStatStr.append(userStatData.toString() + "\n")
                }

                UserRepository.updateAccountFile(accountStr.toString(),userStatStr.toString())

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
