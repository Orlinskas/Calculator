package com.orlinskas.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.orlinskas.calculator.data.dao.CalculatorResultDao
import com.orlinskas.calculator.data.model.CalculatorResultModel

@Database(entities = [CalculatorResultModel::class], version = 1, exportSchema = false)

@TypeConverters(com.orlinskas.calculator.data.TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun calculatorResultDao(): CalculatorResultDao

}