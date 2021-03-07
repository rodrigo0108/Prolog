package com.heyoh.prolog.feature.attendance.customView

import android.view.View
import com.heyoh.prolog.databinding.HeaderCalendarBinding
import com.kizitonwose.calendarview.ui.ViewContainer

class MonthViewContainer(view: View) : ViewContainer(view) {
    val legendLayout = HeaderCalendarBinding.bind(view).legendLayout.root
}