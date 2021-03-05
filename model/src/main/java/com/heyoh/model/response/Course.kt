package com.heyoh.model.response

data class Course(
    val created: String,
    val description: String,
    val gradeId: Int,
    val id: Int,
    val name: String,
    val status: Boolean,
    val updated: String
)