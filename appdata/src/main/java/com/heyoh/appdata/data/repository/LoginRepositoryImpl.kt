package com.heyoh.appdata.data.repository

import com.heyoh.appdata.data.datastore.LoginDataStore
import com.heyoh.appdata.domain.repository.LoginRepository
import com.heyoh.model.request.LoginRequest
import com.heyoh.model.response.LoginResponse
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

class LoginRepositoryImpl(private val loginDataStore: LoginDataStore) : LoginRepository{
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, Failure> {
        return loginDataStore.login(loginRequest)
    }
}