package br.com.douglas.banquinho.exchange

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglas.banquinho.R
import br.com.douglas.banquinho.model.DollarQuoteResponse
import br.com.douglas.banquinho.retrofit.RetrofitUtil
import kotlinx.android.synthetic.main.fragment_exchange.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callDollarQuote()

    }

    private fun callDollarQuote() {

        val dollarQuoteCall =  RetrofitUtil.getService().getDolarQuote("USD-BRL")

        dollarQuoteCall.enqueue(object : Callback<DollarQuoteResponse>{
            override fun onResponse(
                call: Call<DollarQuoteResponse>,
                response: Response<DollarQuoteResponse>
            ) {
                response.body()?.let {
                    txtTexto.text = it.value
                }
                Log.i("Salguod","Entrou")
            }

            override fun onFailure(call: Call<DollarQuoteResponse>, t: Throwable) {
                Log.e("Salguod", "Error = $t")
            }

        })


    }

}