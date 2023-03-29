package com.example.myapplication.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.LoginUseCase
import com.example.domain.model.LoginResponseDomain
import com.example.libs.Either
import com.example.myapplication.R
import com.example.myapplication.common.State
import com.example.myapplication.mapper.LoginUiMapper
import com.example.myapplication.ui.model.LoginUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val loginUseCase: LoginUseCase,
    private val mapper: LoginUiMapper,
    private val application: Application
) : AndroidViewModel(application) {

    private val _loginSharedFlow = MutableSharedFlow<State>()
    val loginSharedFlow = _loginSharedFlow.asSharedFlow()

    private var loginModel: LoginUiModel? = null


    fun login(user: String, pass: String) = viewModelScope.launch {
        _loginSharedFlow.emit(State.Loading)
        loginUseCase.execute(mapper.mapLoginInputToRequestDomain(user, pass)).also { response ->
            when (response) {
                is Either.Success<LoginResponseDomain> -> {
                    with(response.data.image) {
                        if (this.isNotEmpty()) {
                            loginModel = mapper.mapDomainLoginResponseToLoginUI(response.data)
                            _loginSharedFlow.emit(State.Success(application.getString(R.string.state_success)))
                        }
                        else {
                            _loginSharedFlow.emit(State.Empty(application.getString(R.string.state_empty)))
                        }
                    }
                }
                is Either.Error -> {
                    _loginSharedFlow.emit(State.Error(response.error.message))
                }
            }
        }
    }

    fun getLoginModel() = loginModel

}