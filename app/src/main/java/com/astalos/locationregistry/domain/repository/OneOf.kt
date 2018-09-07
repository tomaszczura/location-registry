package com.astalos.locationregistry.domain.repository

/**
 * @author Tomasz Czura on 9/4/18.
 */
sealed class OneOf<out E, out S> {
    data class Error<out E>(val error: E) : OneOf<E, Nothing>()
    data class Success<out S>(val data: S) : OneOf<Nothing, S>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>

    fun <E> error(error: E) = Error(error)
    fun <S> success(data: S) = Success(data)

    fun oneOf(onError: (E) -> Any, onSuccess: (S) -> Any): Any =
            when (this) {
                is Error -> onError(error)
                is Success -> onSuccess(data)
            }
}