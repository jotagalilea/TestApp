package com.example.datasource.service

import com.example.datasource.response.LoginResponseDTO
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface Services {

    companion object {
        const val BASE_URL = "https://mockedendpoint.url"
        private const val LOGIN_ENDPOINT = "image.php"
        private const val AUTH_KEY = "authorization"
        private const val USER_KEY = "username"
    }


    @POST(LOGIN_ENDPOINT)
    @FormUrlEncoded
    suspend fun loginAndGetImage(
        @Field(USER_KEY) user: String,
        @Header(AUTH_KEY) auth: String
    ): Response<LoginResponseDTO>

}