package com.heyoh.model.response

data class PeriodResponse(
    val created: String,
    val dateBegin: String,
    val dateEnd: String,
    val id: Int,
    val name: String,
    val status: Boolean,
    val updated: String
)