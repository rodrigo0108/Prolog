package com.heyoh.model.response

data class SectionDetailResponse(
        val created: String,
        val grade: GradeResponse,
        val gradeId: Int,
        val id: Int,
        val quantity: Int,
        val section: SectionResponse,
        val sectionId: Int,
        val status: Boolean,
        val updated: String
)