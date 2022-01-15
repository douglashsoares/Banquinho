package br.com.douglas.banquinho.retrofit

import br.com.douglas.banquinho.model.DollarQuoteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoneyService {

    @GET("json/last/{moneyType}")
    fun getDolarQuote(@Path("moneyType") moneyType: String): Call<DollarQuoteResponse>



}