package com.heyoh.appdata.mapper

import com.heyoh.model.response.BannerResponse

fun BannerResponse.toDomain() = items.map {
    it.path
}