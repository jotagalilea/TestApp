package com.example.domain.interactor

import com.example.domain.DomainError
import com.example.domain.interactor.base.GetUseCase
import com.example.domain.model.LoginRequestDomain
import com.example.domain.model.LoginResponseDomain
import com.example.domain.repository.MainRepository
import com.example.libs.Either

class LoginUseCase(
    private val repository: MainRepository
): GetUseCase<LoginRequestDomain, Either<LoginResponseDomain, DomainError.LoginDomainError>>() {

    override suspend fun buildUseCase(input: LoginRequestDomain): Either<LoginResponseDomain, DomainError.LoginDomainError> {
        return repository.login(input)
    }
}