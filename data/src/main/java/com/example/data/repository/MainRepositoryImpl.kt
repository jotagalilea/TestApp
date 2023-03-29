package com.example.data.repository

import com.example.data.mapper.LoginDataMapper
import com.example.datasource.error.LoginError
import com.example.datasource.source.RemoteDataSource
import com.example.domain.DomainError
import com.example.domain.model.LoginRequestDomain
import com.example.domain.model.LoginResponseDomain
import com.example.domain.repository.MainRepository
import com.example.libs.Either

class MainRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val mapper: LoginDataMapper
): MainRepository {

    override suspend fun login(input: LoginRequestDomain): Either<LoginResponseDomain, DomainError.LoginDomainError> {
        val request = mapper.mapRequestDomainToDTO(input)
        return when (val response = remoteDataSource.loginAndGetImage(request)) {
            is Either.Error -> {
                when (response.error) {
                    is LoginError.GenericError ->
                        Either.Error(
                            DomainError.LoginDomainError(
                                (response.error as LoginError.GenericError).message
                            )
                        )
                    is LoginError.NullError ->
                        Either.Error(
                            DomainError.LoginDomainError(
                                (response.error as LoginError.NullError).message
                            )
                        )
                    is LoginError.UnauthorizedError ->
                        Either.Error(
                            DomainError.LoginDomainError(
                                (response.error as LoginError.UnauthorizedError).message
                            )
                        )
                }
            }
            is Either.Success -> Either.Success(mapper.mapResponseDtoToDomain(response.data))
        }
    }
}