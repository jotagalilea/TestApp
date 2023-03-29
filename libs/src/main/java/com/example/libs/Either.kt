package com.example.libs

sealed class Either<out T, out E> {
    data class Success<out T>(val data: T): Either<T, Nothing>()
    data class Error<out E>(val error: E): Either<Nothing, E>()
}