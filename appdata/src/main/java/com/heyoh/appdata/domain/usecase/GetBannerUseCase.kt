package com.heyoh.appdata.domain.usecase

import com.heyoh.appdata.domain.repository.BannerRepository
import com.heyoh.model.response.BannerResponse
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

class GetBannerUseCase(private val bannerRepository: BannerRepository) {
    suspend operator fun invoke(): Result<BannerResponse, Failure> = bannerRepository.getBannerItems()
}