package com.orlinskas.calculator.data.dao

import androidx.room.*
import com.orlinskas.calculator.data.Tables
import com.orlinskas.calculator.data.model.CalculatorResultModel

@Dao
interface CalculatorResultDao {

    @Query("SELECT * FROM ${Tables.CALCULATOR_RESULT_TABLE}")
    fun getCalculatorResult(): List<CalculatorResultModel>

    @Query("SELECT * FROM ${Tables.CALCULATOR_RESULT_TABLE} WHERE id=:id")
    fun getCalculatorResult(id: Int): CalculatorResultModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCalculatorResult(calculatorResultModel: CalculatorResultModel): Long

    @Delete
    fun removeCalculatorResult(calculatorResultModel: CalculatorResultModel): Int
}