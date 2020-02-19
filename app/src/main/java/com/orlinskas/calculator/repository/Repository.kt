package com.orlinskas.calculator.repository
import retrofit2.Response
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.simple.repository.Convertable
import ua.brander.meetingroom.data.storage.model.ResponseData
import ua.brander.meetingroom.data.storage.model.ResponseWithListData
import ua.brander.meetingroom.data.storage.model.ResponseWithMapData
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

    fun convertToLocalFormat(it: Map<String, List<T>>): Map<String, List<R>> =
        it.keys.zip((it.values.map { list -> list.map { it.convert() } })).toMap()

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
        default: Map<String, List<R>>
    ): Either<Failure, Map<String, List<R>>> {
        return request(response, { convertToLocalFormat(it) }, default)
    }

    fun request(
        response: Response<ResponseData<T>>,
        transform: (T) -> R,
        default: R
    ): Either<Failure, R> {
        try {
            when (response.code()) {
                OK.code -> {
                    val data = response.body()?.data
                    val result = if (data == null) {
                        default
                    } else {
                        transform(data)
                    }
                    updateOrStoreLocally(result)
                    return Either.Right(result)
                }
                INVALID_INPUT.code, INVALID_DATA.code, CONFLICT_TIME.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                INVALID_TOKEN.code, DEACTIVATED.code -> {
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
                    NOT_FOUND.code,
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
                OK.code -> {
                    val data = response.body()?.data
                    val result = if (data == null) {
                        default
                    } else {
                        transform(data)
                    }

                    updateOrStoreLocally(result)
                    return Either.Right(result)
                }
                INVALID_INPUT.code, INVALID_DATA.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                INVALID_TOKEN.code, DEACTIVATED.code -> {
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
                    NOT_FOUND.code,
                    exception.toString(),
                    default
                )
            )
        }
    }

    fun request(
        response: Response<ResponseWithMapData<T>>,
        transform: (Map<String, List<T>>) -> Map<String, List<R>>,
        default: Map<String, List<R>>
    ): Either<Failure, Map<String, List<R>>> {
        try {
            when (response.code()) {
                OK.code -> {
                    val data = response.body()?.data
                    val result = if (data == null) {
                        default
                    } else {
                        transform(data)
                    }

                    updateOrStoreLocally(result)
                    return Either.Right(result)
                }
                INVALID_INPUT.code, INVALID_DATA.code -> {
                    return Either.Left(
                        Failure.ServerErrorWithDescription(
                            response.code(),
                            response.errorBody()?.getMessage()
                        )
                    )
                }
                INVALID_TOKEN.code, DEACTIVATED.code -> {
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
                    NOT_FOUND.code,
                    exception.toString(),
                    default
                )
            )
        }
    }

    class None
}

