package com.example.domain.interactor.base

abstract class GetUseCase<Input, Output> {

    protected abstract suspend fun buildUseCase(input: Input): Output

    suspend fun execute(input: Input): Output {
        return buildUseCase(input)
    }
}