package com.heyoh.appdata.mapper

import com.heyoh.model.Course
import com.heyoh.model.response.LoginResponse

fun LoginResponse.toDomain() = assing_courses.map {
    Course(id = it.id, name = it.course.name, teacherName = it.teacher.fullName)
}