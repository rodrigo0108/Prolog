package com.heyoh.model.result

import com.heyoh.model.response.ApiErrorResponse

sealed class Failure {
    object NetworkConnection: Failure()
    object ServerError: Failure()
    object Unexpected: Failure()
    data class ClientError(
        val apiErrorResponse: ApiErrorResponse
    ): Failure()
}