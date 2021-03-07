package com.heyoh.model.response

data class MetaResponse(
    val currentPage: String,
    val itemCount: Int,
    val itemsPerPage: String,
    val totalItems: Int,
    val totalPages: Any
)