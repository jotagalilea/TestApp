package com.example.myapplication.common

sealed interface State {

    object Waiting: State
    object Loading: State
    data class Empty(val message: String): State
    data class Error(val message: String): State
    data class Success<T>(val data: T): State

}