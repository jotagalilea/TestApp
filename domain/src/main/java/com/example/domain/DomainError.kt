package com.example.domain

sealed class DomainError {

    data class LoginDomainError(val message: String): DomainError()

}