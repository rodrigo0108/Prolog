package com.heyoh.appdata.data.repository

import com.heyoh.appdata.data.datastore.BannerDataStore
import com.heyoh.appdata.domain.repository.BannerRepository
import com.heyoh.model.response.BannerResponse
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

class BannerRepositoryImpl(private val bannerDataStore: BannerDataStore): BannerRepository {
    override suspend fun getBannerItems(): Result<BannerResponse, Failure> {
        return bannerDataStore.getBannerItems()
    }
}