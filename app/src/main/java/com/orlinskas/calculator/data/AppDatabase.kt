package com.orlinskas.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orlinskas.calculator.data.dao.CalculatorResultDao
import com.orlinskas.calculator.data.model.CalculatorResultModel

@Database(entities = [CalculatorResultModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun calculatorResultDao(): CalculatorResultDao

}