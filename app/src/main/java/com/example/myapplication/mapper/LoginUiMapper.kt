package com.example.myapplication.mapper

import android.graphics.BitmapFactory
import com.example.domain.model.LoginRequestDomain
import com.example.domain.model.LoginResponseDomain
import com.example.myapplication.ui.model.LoginUiModel

class LoginUiMapper {

    fun mapLoginInputToRequestDomain(user: String, pass: String) = LoginRequestDomain(
        username = user,
        password = pass
    )

    fun mapDomainLoginResponseToLoginUI(response: LoginResponseDomain) = LoginUiModel(
        image = BitmapFactory.decodeByteArray(response.image, 0, response.image.size)
    )
}