package br.com.douglas.banquinho.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountHolderEntity(
    @PrimaryKey val account: Int,
    @ColumnInfo(name = "password") val password: String
)
