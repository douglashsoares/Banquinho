package br.com.douglas.banquinho.features.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.douglas.banquinho.R
import br.com.douglas.banquinho.database.LoginAccountHolder
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAccountHolderBalance()

        btnExchange.setOnClickListener(){
            findNavController().navigate(MainFragmentDirections.goToExchange())
        }
        imgOpenEye.setOnClickListener {
            if (txtCaixaPreta.visibility == View.GONE) {
                txtCaixaPreta.visibility = View.VISIBLE
            } else {
                txtCaixaPreta.visibility = View.GONE
            }
        }
    }





    private fun setAccountHolderBalance() {
        txtSaldoValor.text = LoginAccountHolder.instance.balance.toString()
    }
}