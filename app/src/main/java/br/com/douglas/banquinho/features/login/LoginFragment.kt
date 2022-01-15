package br.com.douglas.banquinho.features.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
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

    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupObservers()

        btnLogin.setOnClickListener() {
            val account = edtAccount.text.toString()
            val password = edtPassword.text.toString()

            var inputVerify = viewModel.validInput(account, password)

            if (inputVerify) {
                if (viewModel.validAccount(account, password)) {
                    val accountHolder = DatabaseUtil.db.bankDao().loadSingle(account.toInt())
                    LoginAccountHolder.instance = accountHolder
                    findNavController().navigate(LoginFragmentDirections.goToMain())
                }
            }
        }

        btnSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.goToSignUp())
        }
    }

    private fun setupObservers() {
        viewModel.accountErrorLiveData.observe(viewLifecycleOwner) {
            ilAccount.error = it
        }
        viewModel.passwordErrorLiveData.observe(viewLifecycleOwner) {
            ilPassword.error = it
        }
    }


}