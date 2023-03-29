package com.example.datasource.error

sealed class LoginError {

    data class UnauthorizedError(val message: String): LoginError()
    data class GenericError(val message: String): LoginError()
    data class NullError(val message: String): LoginError()

}