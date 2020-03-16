package com.orlinskas.calculator.data

import android.content.Context
import androidx.room.Room

fun provideAppDatabase(context: Context): AppDatabase {
    val builder = Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
    //builder.addMigrations()

    return builder.build()
}