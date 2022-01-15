package br.com.douglas.banquinho.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitUtil {

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://economia.awesomeapi.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getService(): MoneyService {
        return buildRetrofit().create(MoneyService::class.java)
    }

}