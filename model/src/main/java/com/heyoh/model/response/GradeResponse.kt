package com.heyoh.model.response

data class GradeResponse(
    val created: String,
    val description: String,
    val id: Int,
    val levelId: Int,
    val name: String,
    val status: Boolean,
    val updated: String
)