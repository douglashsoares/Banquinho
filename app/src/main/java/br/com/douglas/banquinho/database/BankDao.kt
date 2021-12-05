package br.com.douglas.banquinho.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BankDao {

    @Query("SELECT * FROM AccountHolderEntity")
    fun getAll(): List<AccountHolderEntity>

    @Query("SELECT * FROM accountholderentity WHERE account = :searchForId")
    fun searchId (searchForId: String)

    @Query("SELECT * FROM AccountHolderEntity WHERE account IN (:accountIds) ")
    fun loadAllByIds (accountIds:IntArray):List<AccountHolderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (account : AccountHolderEntity)
}