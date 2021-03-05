package com.heyoh.model.response

data class LoginResponse(
    val access_token: String,
    val assing_courses: List<AssingCourse>,
    val studentEnrollment: Any,
    val user_type: String
)