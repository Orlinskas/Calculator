package com.orlinskas.calculator.network.response

import com.google.gson.annotations.SerializedName
import com.orlinskas.calculator.data.model.*
import ua.brander.core.simple.repository.Convertable
import java.io.Serializable

data class CalculatorResponse(
    @SerializedName("dictionary")
    val dictionaryResponse: DictionaryResponse? = null,
    @SerializedName("result")
    val resultResponse: ResultResponse? = null
): Convertable<CalculatorResultModel> {

    override fun convert(): CalculatorResultModel {
        return CalculatorResultModel(
            (0 until 9999999999).random(), resultResponse?.calculationResultResponse?.convert(),
            resultResponse?.inputValuesResponse?.convert(), resultResponse?.productResponses?.map { it.convert() },
            dictionaryResponse?.ru?.convert(), resultResponse?.totalSum, dictionaryResponse?.ua?.convert()
        )
    }
}

data class DictionaryResponse(
    @SerializedName("ru")
    val ru: LocalizationResponse,
    @SerializedName("ua")
    val ua: LocalizationResponse
): Serializable, Convertable<Dictionary> {
    override fun convert(): Dictionary {
        return Dictionary(ru = ru.convert(), ua = ua.convert())
    }
}

data class ResultResponse(
    @SerializedName("calculation_result")
    val calculationResultResponse: CalculationResultResponse,
    @SerializedName("input_values")
    val inputValuesResponse: InputValuesResponse,
    @SerializedName("products")
    val productResponses: List<ProductResponse>,
    @SerializedName("total_sum")
    val totalSum: String
): Serializable, Convertable<Result> {
    override fun convert(): Result {
        return Result(calculationResult = calculationResultResponse.convert(),
            inputValues = inputValuesResponse.convert(),
            product = productResponses.map { it.convert() }, totalSum = totalSum)
    }
}

data class LocalizationResponse(
    val count: String,
    val distance: String,
    val insulation: String,
    val length: String,
    val name: String,
    val price: String,
    @SerializedName("quantity_contour")
    val quantityContour: String,
    val regulation: String,
    val sku: String,
    val step: String,
    val sum: String,
    @SerializedName("total_sum")
    val totalSum: String,
    @SerializedName("tube_length")
    val tubeLength: String,
    val width: String
): Serializable, Convertable<Localization> {
    override fun convert(): Localization {
        return Localization(count = count, distance = distance, insulation = insulation, length = length,
        name = name, price = price, quantityContour = quantityContour, regulation = regulation,
        sku = sku, step = step, sum = sum, totalSum = totalSum, tubeLength = tubeLength, width = width)
    }
}

data class CalculationResultResponse(
    @SerializedName("quantity_contour")
    val quantityContour: Int,
    @SerializedName("tube_length")
    val tubeLength: Int
): Serializable, Convertable<CalculationResult> {
    override fun convert(): CalculationResult {
        return CalculationResult(quantityContour = quantityContour, tubeLength = tubeLength)
    }
}

data class InputValuesResponse(
    val distance: Float,
    val insulation: Boolean,
    val length: Float,
    val regulation: Boolean,
    val step: Int,
    val width: Float
): Serializable, Convertable<InputValues> {
    override fun convert(): InputValues {
        return InputValues(distance, insulation, length, regulation, step, width)
    }
}

data class ProductResponse(
    val count: Int,
    val image: String,
    val name: String,
    val price: String,
    val sku: String,
    val sum: String
): Serializable, Convertable<Product> {
    override fun convert(): Product {
        return Product(count, image, name, price, sku, sum)
    }
}

data class ErrorsResponse(
    val errors: List<String>
): Serializable