package com.heyoh.appdata.data.datastore

import com.heyoh.model.response.BannerResponse
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

interface BannerDataStore {
    suspend fun getBannerItems(): Result<BannerResponse, Failure>
}