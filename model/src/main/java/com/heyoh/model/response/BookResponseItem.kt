package com.heyoh.model.response

data class BookResponseItem(
    val categoryId: Int,
    val course: CourseResponse,
    val courseId: Int,
    val created: String,
    val description: String,
    val dropbox: String?=null,
    val id: String,
    val image: String,
    val isActive: Boolean,
    val mimetype: String,
    val name: String,
    val nestingLevel: String,
    val order: Int,
    val parentId: String,
    val path: String,
    val scheduledDate: String,
    val size: Int,
    val status: Boolean,
    val title: String,
    val updated: String
)