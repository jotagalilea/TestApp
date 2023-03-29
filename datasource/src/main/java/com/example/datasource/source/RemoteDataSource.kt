package com.example.datasource.source

import com.example.datasource.error.LoginError
import com.example.datasource.request.LoginRequestDTO
import com.example.datasource.response.LoginResponseDTO
import com.example.datasource.service.Services
import com.example.libs.Either

class RemoteDataSource(
    private val services: Services
) {

    suspend fun loginAndGetImage(request: LoginRequestDTO): Either<LoginResponseDTO, LoginError> {
        val response = services.loginAndGetImage(request.username, request.password)
        return if (response.isSuccessful) {
            response.body()?.let { dto ->
                if (!dto.image.isNullOrEmpty())
                    Either.Success(dto)
                else
                    Either.Error(LoginError.NullError("Null or empty response"))
            } ?: Either.Error(LoginError.NullError("Null response"))
        } else {
            when (response.code()) {
                401 -> Either.Error(LoginError.UnauthorizedError("Unauthorized user or bad password"))
                else -> Either.Error(LoginError.GenericError("Error code ${response.code()}"))
            }
        }
    }

}