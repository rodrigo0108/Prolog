package com.heyoh.model.sealed

import com.heyoh.model.Timetable

sealed class TimeTypeItem {
    data class Class(val timetable: Timetable) : TimeTypeItem()
    data class Break(val timetable: Timetable) : TimeTypeItem()
}