package com.heyoh.model.response

data class AssingCourse(
    val assignedCourse: List<AssignedCourse>,
    val campusId: Int,
    val course: Course,
    val courseId: Int,
    val created: String,
    val id: Int,
    val period: Period,
    val periodId: Int,
    val sectionDetail: SectionDetail,
    val sectionDetailId: Int,
    val silabo: Any,
    val status: Boolean,
    val teacher: Teacher,
    val teacherId: Int,
    val updated: String,
    val video: String
)