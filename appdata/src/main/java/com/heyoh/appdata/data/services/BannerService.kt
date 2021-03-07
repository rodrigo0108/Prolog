package com.heyoh.appdata.data.services

import com.heyoh.appdata.data.api.APIUrl
import com.heyoh.model.response.BannerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BannerService {
    @GET(APIUrl.BANNER)
    suspend fun getBannerItems(@Header("Authorization")  authorization: String): Response<BannerResponse>
}