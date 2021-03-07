package com.heyoh.model.result

sealed class Result<S, F> {
    data class Success<S, F>(val value: S) : Result<S, F>()
    data class Error<S, F>(val value: F) : Result<S, F>()
}