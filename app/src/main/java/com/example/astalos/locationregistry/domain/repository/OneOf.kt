package com.example.astalos.locationregistry.domain.repository

/**
 * @author Tomasz Czura on 9/4/18.
 */
sealed class OneOf<out E, out S> {
    data class Error<out E>(val a: E) : OneOf<E, Nothing>()
    data class Success<out S>(val b: S) : OneOf<Nothing, S>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>

    fun <E> error(a: E) = OneOf.Error(a)
    fun <S> success(b: S) = OneOf.Success(b)

    fun oneOf(onError: (E) -> Any, onSuccess: (S) -> Any): Any =
            when (this) {
                is OneOf.Error -> onError(a)
                is OneOf.Success -> onSuccess(b)
            }
}