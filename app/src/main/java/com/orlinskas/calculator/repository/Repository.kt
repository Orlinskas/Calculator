package com.orlinskas.calculator.repository

import com.google.gson.GsonBuilder
import com.orlinskas.calculator.ApiResponse
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.simple.repository.Convertable

interface Repository<T : Convertable<R>, R> {

    companion object {
        const val NETWORK_NOT_AVAILABLE_ERROR_CODE = 1_000_000
    }

    fun convertToLocalFormat(it: T): R {
        it.convert().apply {
            val json = GsonBuilder().serializeNulls().create().toJson(this)
            Timber.d("Transform converted to -- ( $json )")
            return this
        }
    }

    fun convertToLocalFormat(it: List<T>): List<R> {
        return it.map { it.convert() }
    }

    fun convertToLocalFormat(it: Map<String, T>): Map<String, R> {
        return it.mapValues {
            it.value.convert()
        }
    }

    fun updateOrStoreLocally(result: Any?)

    fun request(response: Response<T>, default: R): Either<Failure, R> {
        return request(response, { convertToLocalFormat(it) }, default)
    }

    fun request(
        response: Response<T>,
        transform: (T) -> R,
        default: R
    ): Either<Failure, R> {
        try {
            when (response.code()) {
                ApiResponse.OK.code -> {
                    Timber.d("Api response code -- ${response.code()}")
                    val data = response.body()

                    val json = GsonBuilder().serializeNulls().create().toJson(data)
                    Timber.d("Json data -- ( $json )")

                    val result = if (data == null) {
                        Timber.d("Use default data!!!")
                        default
                    } else {
                        Timber.d("Start transform result")
                        transform(data)
                    }
                    updateOrStoreLocally(result)
                    return Either.Right(result)
                }
                ApiResponse.INVALID_INPUT.code -> {
                    Timber.d("Api response code -- ${response.code()}")
                    var message = "CantParseError"

                    response.errorBody()?.let { errorBody ->
                        val jObjError = JSONObject(errorBody.string())
                        message = jObjError.getString("message")

                        try {
                            val key = jObjError.getJSONObject("errors").keys().next()

                            if(!key.isNullOrEmpty()) {
                                val firstError = jObjError.getJSONObject("errors").getJSONArray(key).getString(0)

                                if(!firstError.isNullOrEmpty()) {
                                    return Either.Left(Failure.ServerErrorWithDefaultData(ApiResponse.INVALID_INPUT_WITH_FIELD.code, message, Pair(key, firstError)))
                                }
                            }
                        } catch (e: Exception) { }
                    }

                    return Either.Left(Failure.ServerErrorWithDefaultData(ApiResponse.INVALID_INPUT.code, message, default))
                }
                else -> {
                    Timber.d("Unchecked error, with code ${response.code()}")
                    val message = "Unchecked error, with code ${response.code()}"
                    return Either.Left(Failure.ServerErrorWithDefaultData(0, message, default))
                }
            }
        } catch (exception: Throwable) {
            return Either.Left(
                Failure.ServerErrorWithDefaultData(
                    NETWORK_NOT_AVAILABLE_ERROR_CODE,
                    exception.toString(),
                    default
                )
            )
        }
    }

    class None
}

