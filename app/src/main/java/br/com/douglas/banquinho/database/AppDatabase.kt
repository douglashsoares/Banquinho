package br.com.douglas.banquinho.database

import android.app.Application
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

object DatabaseUtil {

    val db = Room.databaseBuilder(
        App.instance,
        AppDatabase::class.java, "database-name"
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

    @Database(entities = [AccountHolderEntity::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun bankDao(): BankDao

    }
