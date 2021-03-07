package com.heyoh.appdata.data.datastore

import com.heyoh.model.request.LoginRequest
import com.heyoh.model.response.LoginResponse
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

interface LoginDataStore {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, Failure>
}