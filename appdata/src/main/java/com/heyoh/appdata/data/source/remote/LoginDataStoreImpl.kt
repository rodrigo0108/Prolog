package com.heyoh.appdata.data.source.remote

import com.heyoh.appdata.data.api.APIUrl
import com.heyoh.appdata.data.datastore.LoginDataStore
import com.heyoh.appdata.data.services.LoginService
import com.heyoh.model.request.LoginRequest
import com.heyoh.model.response.ApiErrorResponse
import com.heyoh.model.response.LoginResponse
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class LoginDataStoreImpl: LoginDataStore {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, Failure> {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(APIUrl.BASE)
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofitBuilder.create(LoginService::class.java)
        return try {
            val moshi = Moshi.Builder().build()

            val result = service.login(loginRequest)
            if (result.isSuccessful && result.body()!=null) {
                Result.Success(result.body()!!)
            } else {
                val errorAdapter = moshi.adapter(ApiErrorResponse::class.java)
                val apiErrorResponse = errorAdapter.fromJson(result.errorBody()?.string()?:"")
                return if (apiErrorResponse!=null){
                    Result.Error(Failure.ClientError(apiErrorResponse))
                }else{
                    Result.Error(Failure.Unexpected)
                }
            }
        } catch (ex: Exception) {
            Result.Error(Failure.Unexpected)
        }
    }
}