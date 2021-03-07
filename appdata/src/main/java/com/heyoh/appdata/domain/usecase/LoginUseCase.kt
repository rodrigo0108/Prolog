package com.heyoh.appdata.domain.usecase

import com.heyoh.appdata.domain.repository.LoginRepository
import com.heyoh.model.request.LoginRequest
import com.heyoh.model.response.LoginResponse
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

class LoginUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(loginRequest: LoginRequest): Result<LoginResponse, Failure> = loginRepository.login(loginRequest)
}