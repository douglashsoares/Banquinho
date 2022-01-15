package br.com.douglas.banquinho.features.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.douglas.banquinho.database.AccountHolderEntity
import br.com.douglas.banquinho.database.DatabaseUtil
import kotlinx.android.synthetic.main.bottom_sheet_sign_up.*

class SignUpViewModel : ViewModel() {

    val accountErrorLiveData = MutableLiveData<String?>()
    val passwordErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val confirmPasswordErrorLiveData: MutableLiveData<String?> = MutableLiveData()

    val accountErrorEnabledLiveData = MutableLiveData<Boolean>()
    val passwordErrorEnabledLiveData = MutableLiveData<Boolean>()
    val confirmPasswordErrorEnabledLiveData = MutableLiveData<Boolean>()

    val finishSignUpLiveData = MutableLiveData<Boolean>()


    fun createAccount(account: String, password: String) {
        DatabaseUtil.db.bankDao()
            .insert(AccountHolderEntity(account.toInt(), password, balance = 0.0))

        finishSignUpLiveData.postValue(true)
    }

    fun checkExistingAccount(account: String): Boolean {
        if (DatabaseUtil.db.bankDao().isRowIsExist(account.toInt())) {
            accountErrorLiveData.value = "Conta já existe"
            return true
        } else {
            return false
        }
    }

    fun validInput(account: String, password: String, confirmPassword: String): Boolean {

        var inputVerify = true

        if (account.isNullOrEmpty()) {
            accountErrorLiveData.postValue("Não pode ser vazio")
            inputVerify = false
        } else if (account.length > 9) {
            accountErrorLiveData.postValue("Maximo 9 Caracteres")
            inputVerify = false
        } else {
            accountErrorLiveData.value = null
            accountErrorEnabledLiveData.setValue(false)
        }

        if (password.length > 15) {
            passwordErrorLiveData.postValue("Maximo 14 Caracteres")
            inputVerify = false
        } else if (password.isNullOrEmpty()) {

            passwordErrorLiveData.postValue("Nao pode ser vazio")
            inputVerify = false

        } else {
            passwordErrorLiveData.postValue(null)
            passwordErrorEnabledLiveData.postValue(false)
        }

        if (confirmPassword != password) {
            confirmPasswordErrorLiveData.postValue("Senhas Nao sao iguais")
            inputVerify = false
        } else if (confirmPassword.isNullOrEmpty()) {
            confirmPasswordErrorLiveData.postValue("Não pode ser Vazio")
            inputVerify = false
        } else {
            confirmPasswordErrorLiveData.postValue(null)
            confirmPasswordErrorEnabledLiveData.postValue(false)
        }

        return inputVerify
    }

}

