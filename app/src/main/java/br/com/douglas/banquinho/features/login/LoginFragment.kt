package br.com.douglas.banquinho.features.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.douglas.banquinho.R
import br.com.douglas.banquinho.database.AccountHolderEntity
import br.com.douglas.banquinho.database.DatabaseUtil
import br.com.douglas.banquinho.database.LoginAccountHolder
import kotlinx.android.synthetic.main.bottom_sheet_sign_up.*
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.btnSignUp
import kotlinx.android.synthetic.main.login_fragment.edtAccount
import kotlinx.android.synthetic.main.login_fragment.edtPassword
import kotlinx.android.synthetic.main.login_fragment.ilAccount
import kotlinx.android.synthetic.main.login_fragment.ilPassword

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            val account = edtAccount.text.toString()
            val password = edtPassword.text.toString()
            var inputVerify = true

            if (account.isEmpty()) {
                ilAccount.error = "Login Vazio"
                inputVerify = false
            }

            if (password.isEmpty()) {
                ilPassword.error = "Senha Vazio"
                inputVerify = false
            }
            if (inputVerify)
                if (DatabaseUtil.db.bankDao().isRowIsExist(account.toInt())) {
                    val accountHolder = DatabaseUtil.db.bankDao().loadSingle(account.toInt())

                    if (accountHolder.password == password) {
                        LoginAccountHolder.instance = accountHolder
                        findNavController().navigate(LoginFragmentDirections.goToMain())
                    } else {
                        ilPassword.error = "Senhas não são Iguais"
                    }
                }else{
                    ilAccount.error = "Conta não Existe"
                }
        }

        btnSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.goToSignUp())
        }
    }

}