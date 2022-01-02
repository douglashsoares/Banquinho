package br.com.douglas.banquinho.database

import androidx.room.*


@Dao
interface BankDao {

    @Query("SELECT * FROM AccountHolderEntity")
    fun getAll(): List<AccountHolderEntity>

//    @Query("SELECT * FROM accountholderentity WHERE account = :searchForId")
//    fun searchId (searchForId: String)

    @Query("SELECT EXISTS(SELECT * FROM AccountHolderEntity WHERE account = :id)")
    fun isRowIsExist(id: Int): Boolean

    @Query("SELECT * FROM AccountHolderEntity WHERE account IN (:accountIds) ")
    fun loadAllByIds(accountIds: IntArray): List<AccountHolderEntity>

    @Query("SELECT * FROM accountholderentity WHERE account = :id")
    fun loadSingle(id: Int): AccountHolderEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: AccountHolderEntity)

    @Delete
    fun delete(account: AccountHolderEntity)
}