package com.heyoh.model.response

data class TeacherResponse(
    val created: String,
    val documentNumber: String,
    val documentTypeId: Int,
    val email: String,
    val fullName: String,
    val id: Int,
    val name: String,
    val phone: String,
    val status: Boolean,
    val surname: String,
    val updated: String,
    val userId: Int
)