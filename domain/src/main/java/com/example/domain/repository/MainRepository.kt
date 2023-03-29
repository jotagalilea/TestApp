package com.example.domain.repository

import com.example.domain.DomainError
import com.example.domain.model.LoginRequestDomain
import com.example.domain.model.LoginResponseDomain
import com.example.libs.Either

interface MainRepository {

    suspend fun login(input: LoginRequestDomain): Either<LoginResponseDomain, DomainError.LoginDomainError>

}