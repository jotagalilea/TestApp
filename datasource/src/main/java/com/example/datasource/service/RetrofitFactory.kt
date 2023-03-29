package com.example.datasource.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    fun create(): Services {
        return Retrofit.Builder()
            .baseUrl(Services.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Services::class.java)
    }

}