package br.com.douglas.banquinho.features.login

import android.nfc.Tag
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.douglas.banquinho.R
import br.com.douglas.banquinho.database.AccountHolderEntity
import br.com.douglas.banquinho.database.DatabaseUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_sign_up.*



class SignUpBottomSheet : BottomSheetDialogFragment() {

    val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()

        setupObservers()

        btnSignUp.setOnClickListener {
            val account = edtAccount.text.toString()
            val password = edtPassword.text.toString()
            val confirmPassword = edtConfirmPassword.text.toString()

            val inputVerify = viewModel.validInput(account, password, confirmPassword)

            if (inputVerify) {
                val accountExists = viewModel.checkExistingAccount(account)
                if(!accountExists){
                    viewModel.createAccount(account, password)
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.accountErrorLiveData.observe(this){
            ilAccount.error = it
        }

        viewModel.passwordErrorLiveData.observe(this){
            ilPassword.error = it
        }

        viewModel.confirmPasswordErrorLiveData.observe(this){
            ilConfirmPassword.error = it
        }

        viewModel.accountErrorEnabledLiveData.observe(this){
            ilAccount.isErrorEnabled = it
        }
        viewModel.passwordErrorEnabledLiveData.observe(this){
            ilPassword.isErrorEnabled = it
        }
        viewModel.confirmPasswordErrorEnabledLiveData.observe(this){
            ilConfirmPassword.isErrorEnabled = it
        }

        viewModel.finishSignUpLiveData.observe(this){
            if(it){
                findNavController().navigateUp()
            }
        }
    }


    private fun setupListener() {
        edtAccount.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                ilAccount.error = null
                ilAccount.isErrorEnabled = false
            }
        }


        edtPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                ilPassword.error = null
                ilPassword.isErrorEnabled = false
            }
        }

        edtConfirmPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                ilConfirmPassword.error = null
                ilConfirmPassword.isErrorEnabled = false
            }
        }

    }


}