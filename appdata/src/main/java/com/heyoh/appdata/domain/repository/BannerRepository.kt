package com.heyoh.appdata.domain.repository

import com.heyoh.model.response.BannerResponse
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

interface BannerRepository {
    suspend fun getBannerItems(): Result<BannerResponse, Failure>
}