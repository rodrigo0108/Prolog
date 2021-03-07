package com.heyoh.appdata.data.services

import com.heyoh.appdata.data.api.APIUrl
import com.heyoh.model.request.LoginRequest
import com.heyoh.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST(APIUrl.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}