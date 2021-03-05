package com.heyoh.model.response

data class AssignedCourse(
    val courseDetailId: Int,
    val created: String,
    val id: Int,
    val status: Boolean,
    val studentId: Int,
    val updated: String
)