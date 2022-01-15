package br.com.douglas.banquinho.features.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.douglas.banquinho.database.AccountHolderEntity
import br.com.douglas.banquinho.database.DatabaseUtil

class LoginViewModel : ViewModel() {

    val accountErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val passwordErrorLiveData: MutableLiveData<String?> = MutableLiveData()


    fun validAccount(account: String, password: String): Boolean {
            val accountHolder = DatabaseUtil.db.bankDao().loadSingle(account.toInt())

            if (accountHolder.password == password) {
                return true
            } else {
                passwordErrorLiveData.value = "Senha Invalida"
                return false
            }

    }

    fun validInput(account: String, password: String): Boolean {

        var inputVerify = true

        if (account.isNullOrEmpty()) {
            accountErrorLiveData.postValue("Campo Vazio")
            inputVerify = false
        }
        if (password.isNullOrEmpty()) {
            passwordErrorLiveData.postValue("Campo Vazio")
            inputVerify = false
        }

        return inputVerify
    }


}