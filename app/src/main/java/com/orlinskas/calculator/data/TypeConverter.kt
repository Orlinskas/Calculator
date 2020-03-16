package com.orlinskas.calculator.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orlinskas.calculator.data.model.*
import com.orlinskas.calculator.extention.fromJson

class TypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun convertDictionary (dictionary: Dictionary): String {
        return gson.toJson(dictionary)
    }

    @TypeConverter
    fun convertDictionary (dictionary: String): Dictionary {
        return gson.fromJson(dictionary)
    }

    @TypeConverter
    fun convertResult(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun convertResult(result: String): Result {
        return gson.fromJson(result)
    }

    @TypeConverter
    fun convertLocalization(lozalization: Localization): String {
        return gson.toJson(lozalization)
    }

    @TypeConverter
    fun convertLocalization(lozalization: String): Localization {
        return gson.fromJson(lozalization)
    }

    @TypeConverter
    fun convertCalculationResult(calculationResult: CalculationResult): String {
        return gson.toJson(calculationResult)
    }

    @TypeConverter
    fun convertCalculationResult(calculationResult: String): CalculationResult {
        return gson.fromJson(calculationResult)
    }

    @TypeConverter
    fun convertInputValues(inputValues: InputValues): String {
        return gson.toJson(inputValues)
    }

    @TypeConverter
    fun convertInputValues(inputValues: String): InputValues {
        return gson.fromJson(inputValues)
    }

    @TypeConverter
    fun convertProduct(product: Product): String {
        return gson.toJson(product)
    }

    @TypeConverter
    fun convertProduct(product: String): Product {
        return gson.fromJson(product)
    }

    @TypeConverter
    fun convertProducts(products: List<Product>?): String? {
        return if (products == null || products.isEmpty()) {
            null
        } else {
            gson.toJson(products)
        }
    }

    @TypeConverter
    fun convertProducts(json: String?): List<Product> {
        return if (json.isNullOrEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<Product>>() {}.type
            gson.fromJson(json, type)
        }
    }

}