package com.heyoh.prolog.feature.attendance.customView

import android.view.View
import com.heyoh.prolog.databinding.CalendarDayLayoutBinding
import com.kizitonwose.calendarview.ui.ViewContainer

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = CalendarDayLayoutBinding.bind(view).calendarDayText

}