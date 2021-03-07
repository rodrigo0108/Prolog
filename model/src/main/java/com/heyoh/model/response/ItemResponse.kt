package com.heyoh.model.response

data class ItemResponse(
    val created: String,
    val id: Int,
    val mimetype: String,
    val name: String,
    val path: String,
    val size: Int,
    val status: Boolean,
    val updated: String
)