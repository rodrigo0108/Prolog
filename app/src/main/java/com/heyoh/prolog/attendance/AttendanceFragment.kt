package com.heyoh.prolog.attendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.heyoh.model.Date
import com.heyoh.model.enum.DateType
import com.heyoh.prolog.R
import com.heyoh.prolog.attendance.customView.DayViewContainer
import com.heyoh.prolog.attendance.customView.MonthViewContainer
import com.heyoh.prolog.databinding.FragmentAttendanceBinding
import com.heyoh.prolog.util.extensions.daysOfWeekFromLocale
import com.heyoh.prolog.util.extensions.getFirstThreeCharacters
import com.heyoh.prolog.util.extensions.setTextColorRes
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class AttendanceFragment : Fragment() {
    private var _binding: FragmentAttendanceBinding? = null
    private val binding get() = _binding!!
    private val args: AttendanceFragmentArgs by navArgs()
    private val listOfDates = mutableListOf(
            Date("", LocalDate.parse("2021-03-01"), DateType.Permission),
            Date("", LocalDate.parse("2021-03-03"), DateType.Absence),
            Date("", LocalDate.parse("2021-03-04"), DateType.Present))

    private val listMapped = listOfDates.groupBy { it.date }
    private val currentMonth = YearMonth.now()
    private val startMonth = currentMonth.minusMonths(10)
    private val endMonth = currentMonth.plusMonths(10)
    private val daysOfWeek = daysOfWeekFromLocale()
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.abbreviateNameCourseTextView.text = args.course.name.getFirstThreeCharacters()
        binding.fullNameCourseTextView.text = args.course.name
        binding.teacherNameTextView.text = args.course.teacherName

        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()

                if (day.owner == DayOwner.THIS_MONTH) {
                    val list = listMapped[day.date]
                    if (list != null) {
                        when (list[0].type) {
                            DateType.Permission -> {
                                textView.setTextColorRes(android.R.color.white)
                                textView.setBackgroundResource(R.drawable.bg_day_lightblue)
                            }
                            DateType.Absence -> {
                                textView.setTextColorRes(android.R.color.white)
                                textView.setBackgroundResource(R.drawable.bg_day_red)
                            }
                            DateType.Present -> {
                                textView.setTextColorRes(android.R.color.white)
                                textView.setBackgroundResource(R.drawable.bg_day_solid_selected)
                            }
                        }
                    }
                } else {
                    textView.setTextColorRes(R.color.prolog_primary_color_dark)
                    textView.background = null
                }
            }
        }
        binding.calendarView.monthHeaderBinder =
                object :MonthHeaderFooterBinder<MonthViewContainer> {
                    override fun create(view: View) = MonthViewContainer(view)
                    override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                        // Setup each header day text if we have not done that already.
                        if (container.legendLayout.tag == null) {
                            container.legendLayout.tag = month.yearMonth
                        }
                    }
                }
        binding.calendarView.monthScrollListener = {
            binding.monthYearTextView.text = getString(R.string.month_year,
                    monthTitleFormatter.format(it.yearMonth).capitalize(Locale.getDefault()),
                    it.yearMonth.year.toString())
        }
        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)
    }


}