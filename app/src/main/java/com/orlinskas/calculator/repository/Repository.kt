package com.orlinskas.calculator.repository
import com.orlinskas.calculator.ApiResponse
import com.orlinskas.calculator.model.container.ResponseData
import com.orlinskas.calculator.model.container.ResponseWithListData
import com.orlinskas.calculator.model.container.ResponseWithMapData
import retrofit2.Response
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.simple.repository.Convertable
import ua.brander.meetingroom.extensions.getMessage

interface Repository<T : Convertable<R>, R> {

    companion object {
        const val NETWORK_NOT_AVAILABLE_ERROR_CODE = 1_000_000
    }

    fun convertToLocalFormat(it: T): R {
        return it.convert()
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

    fun request(response: Response<ResponseData<T>>, default: R): Either<Failure, R> {
        return request(response, { convertToLocalFormat(it) }, default)
    }

    fun request(
        response: Response<ResponseWithListData<T>>,
        default: List<R> = emptyList()
    ): Either<Failure, List<R>> {
        return request(response, { convertToLocalFormat(it) }, default)
    }

    fun request(
        response: Response<ResponseWithMapData<T>>,
        default: Map<String, R>
    ): Either<Failure, Map<String, R>> {
        return request(response, { convertToLocalFormat(it) }, default)
    }

    fun request(
        response: Response<ResponseData<T>>,
        transform: (T) -> R,
        default: R
    ): Either<Failure, R> {
        try {
            when (response.code()) {
                ApiResponse.OK.code -> {
                    val data = response.body()?.data
                    val result = if (data == null) {
                        default
                    } else {
                        transform(data)
                    }
                    updateOrStoreLocally(result)
                    return Either.Right(result)
                }
                ApiResponse.INVALID_INPUT.code, ApiResponse.INVALID_DATA.code, ApiResponse.CONFLICT_TIME.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                ApiResponse.INVALID_TOKEN.code, ApiResponse.DEACTIVATED.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                else -> {
                    return Either.Left(Failure.ServerError())
                }
            }
        } catch (exception: Throwable) {
            return Either.Left(
                Failure.ServerErrorWithDefaultData(
                    ApiResponse.NOT_FOUND.code,
                    exception.toString(),
                    default
                )
            )
        }
    }

    fun request(
        response: Response<ResponseWithListData<T>>,
        transform: (List<T>) -> List<R>,
        default: List<R>
    ): Either<Failure, List<R>> {
        try {
            when (response.code()) {
                ApiResponse.OK.code -> {
                    val data = response.body()?.data
                    val result = if (data == null) {
                        default
                    } else {
                        transform(data)
                    }

                    updateOrStoreLocally(result)
                    return Either.Right(result)
                }
                ApiResponse.INVALID_INPUT.code, ApiResponse.INVALID_DATA.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                ApiResponse.INVALID_TOKEN.code, ApiResponse.DEACTIVATED.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                else -> {
                    return Either.Left(Failure.ServerError())
                }
            }
        } catch (exception: Throwable) {
            return Either.Left(
                Failure.ServerErrorWithDefaultData(
                    ApiResponse.NOT_FOUND.code,
                    exception.toString(),
                    default
                )
            )
        }
    }

    fun request(
        response: Response<ResponseWithMapData<T>>,
        transform: (Map<String, T>) -> Map<String, R>,
        default: Map<String, R>
    ): Either<Failure, Map<String, R>> {
        try {
            when (response.code()) {
                ApiResponse.OK.code -> {
                    val data = response.body()?.data
                    val result = if (data == null) {
                        default
                    } else {
                        transform(data)
                    }

                    updateOrStoreLocally(result)
                    return Either.Right(result)
                }
                ApiResponse.INVALID_INPUT.code, ApiResponse.INVALID_DATA.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                ApiResponse.INVALID_TOKEN.code, ApiResponse.DEACTIVATED.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                else -> {
                    return Either.Left(Failure.ServerError())
                }
            }
        } catch (exception: Throwable) {
            return Either.Left(
                Failure.ServerErrorWithDefaultData(
                    ApiResponse.NOT_FOUND.code,
                    exception.toString(),
                    default
                )
            )
        }
    }

    class None
}

