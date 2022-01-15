package br.com.douglas.banquinho.model

import com.google.gson.annotations.SerializedName

data class DollarQuoteResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("bid")
    val value: String
)
