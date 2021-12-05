package br.com.douglas.banquinho.features.login

import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.douglas.banquinho.R
import br.com.douglas.banquinho.database.DatabaseUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_sign_up.*


class SignUpBottomSheet : BottomSheetDialogFragment() {


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

        btnSignUp.setOnClickListener {
            val account = edtAccount.text.toString()
            val password = edtPassword.text.toString()
            val confirmPassword = edtConfirmPassword.text.toString()
            var inputVerify = true

            if (account.isNullOrEmpty()) {
                ilAccount.error = "Nao pode ser vazio"
                inputVerify = false
            } else if (account.length > 9) {
                ilAccount.error = "Maximo 9 Caracteres"
                inputVerify = false
            } else {
                ilAccount.error = null
                ilAccount.isErrorEnabled = false
            }

            if (password.length > 15) {
                ilPassword.error = "Maximo 14 Caracteres"
                inputVerify = false
            } else if (password.isNullOrEmpty()) {
                ilPassword.error = "Nao pode ser vazio"
                inputVerify = false

            } else {
                ilPassword.error = null
                ilPassword.isErrorEnabled = false
            }

            if (confirmPassword != password) {
                ilConfirmPassword.error = "Senhas devem ser iguais"
                inputVerify = false
            } else if (confirmPassword.isNullOrEmpty()) {
                ilConfirmPassword.error = "Nao pode ser vazio"
                inputVerify = false
            } else {
                ilConfirmPassword.error = null
                ilConfirmPassword.isErrorEnabled = false
            }

            if(DatabaseUtil.db.bankDao().searchId(account).toString() == account){




            }

                if (inputVerify) {
                //toDo Nao pode Haver Duas CONTAS IGUAIS

                //toDo Adicionar no Banco de Dados
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