package com.heyoh.model.response

data class SectionDetail(
    val created: String,
    val grade: Grade,
    val gradeId: Int,
    val id: Int,
    val quantity: Int,
    val section: Section,
    val sectionId: Int,
    val status: Boolean,
    val updated: String
)