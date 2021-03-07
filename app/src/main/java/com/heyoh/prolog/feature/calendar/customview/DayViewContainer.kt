package com.heyoh.prolog.feature.calendar.customview

import android.view.View
import com.heyoh.prolog.databinding.CalendarDayLayoutBinding
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.ViewContainer

class DayViewContainer(view: View, dayClicked: (day: CalendarDay) -> Unit) : ViewContainer(view) {
    lateinit var day: CalendarDay // Will be set when this container is bound.
    val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
    init {
        view.setOnClickListener {
            if (day.owner == DayOwner.THIS_MONTH) {
                dayClicked.invoke(day)
            }
        }
    }
}