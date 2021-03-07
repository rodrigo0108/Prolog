package com.heyoh.model.response

data class AssignedCourseResponse(
    val courseDetailId: Int,
    val created: String,
    val id: Int,
    val status: Boolean,
    val studentId: Int,
    val updated: String
)