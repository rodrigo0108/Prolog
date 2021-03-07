package com.heyoh.model.response

data class AssingCourseResponse(
        val assignedCourse: List<AssignedCourseResponse>,
        val campusId: Int,
        val course: CourseResponse,
        val courseId: Int,
        val created: String,
        val id: String,
        val period: PeriodResponse,
        val periodId: Int,
        val sectionDetail: SectionDetailResponse,
        val sectionDetailId: Int,
        val silabo: Any,
        val status: Boolean,
        val teacher: TeacherResponse,
        val teacherId: Int,
        val updated: String,
        val video: String
)