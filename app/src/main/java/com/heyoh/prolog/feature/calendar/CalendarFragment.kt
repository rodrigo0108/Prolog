package com.heyoh.prolog.feature.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.heyoh.model.Filter
import com.heyoh.model.Timetable
import com.heyoh.model.sealed.TimeTypeItem
import com.heyoh.prolog.R
import com.heyoh.prolog.feature.general.adapter.FilterAdapter
import com.heyoh.prolog.feature.calendar.adapter.TimetableAdapter
import com.heyoh.prolog.feature.calendar.customview.DayViewContainer
import com.heyoh.prolog.databinding.FragmentCalendarBinding
import com.heyoh.prolog.util.extensions.daysOfWeekFromLocale
import com.heyoh.prolog.util.extensions.setTextColorRes
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.InDateStyle
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.utils.yearMonth
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private val currentMonth = YearMonth.now()
    private val currentDate = LocalDate.now()
    private val startMonth = currentMonth.minusMonths(10)
    private val endMonth = currentMonth.plusMonths(10)
    private val daysOfWeek = daysOfWeekFromLocale()
    private var selectedDate: LocalDate? = null
    private val filterAdapter = FilterAdapter(::onClicked)
    private val timetableAdapter = TimetableAdapter()
    private var currentPosition = -1
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view, ::onDayClicked)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()

                if (day.owner == DayOwner.THIS_MONTH) {
                    textView.isVisible = true
                    when (day.date) {
                        selectedDate -> {
                            textView.setTextColorRes(android.R.color.white)
                            textView.setBackgroundResource(R.drawable.bg_day_solid_selected)
                        }
                        else -> {
                            textView.setTextColorRes(R.color.prolog_primary_color_dark)
                            textView.background = null
                        }
                    }
                } else {
                    textView.isVisible = false
                }
            }
        }
        binding.calendarView.monthScrollListener = {
            if (binding.calendarView.maxRowCount == 6) {
                binding.monthTextView.text = monthTitleFormatter.format(it.yearMonth)
            } else {
                val firstDate = it.weekDays.first().first().date
                val lastDate = it.weekDays.last().last().date
                if (firstDate.yearMonth == lastDate.yearMonth) {
                    binding.monthTextView.text = monthTitleFormatter.format(firstDate).capitalize(Locale.getDefault())
                } else {
                    binding.monthTextView.text = getString(
                            R.string.two_months,
                            monthTitleFormatter.format(firstDate).capitalize(Locale.getDefault()),
                            monthTitleFormatter.format(lastDate).capitalize(Locale.getDefault()))
                }
            }
        }
        binding.calendarView.updateMonthConfiguration(
                inDateStyle = InDateStyle.FIRST_MONTH,
                maxRowCount = 1,
                hasBoundaries = true
        )

        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToDate(currentDate)
        selectDate(currentDate)

        binding.filterRecyclerView.adapter = filterAdapter
        binding.timetableRecyclerView.adapter = timetableAdapter

        timetableAdapter.list = listOf(
                TimeTypeItem.Class(Timetable("", "Biología", "http:asda.com", "9:00", "0")),
                TimeTypeItem.Class(Timetable("", "Física", "http:asda.com", "10:00", "0")),
                TimeTypeItem.Class(Timetable("", "Física", "http:asda.com", "11:00", "0")),
                TimeTypeItem.Class(Timetable("", "Química", "http:asda.com", "12:00", "0")),
                TimeTypeItem.Break(Timetable("", "", "http:asda.com", "1:00", "0")),
                TimeTypeItem.Class(Timetable("", "Física", "http:asda.com", "2:00", "0")),
                TimeTypeItem.Class(Timetable("", "Química", "http:asda.com", "3:00", "0"))
        )
        filterAdapter.list = listOf(
                Filter("", "Horario"),
                Filter("", "Exámenes"),
                Filter("", "Tareas")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onDayClicked(day: CalendarDay) {
        selectDate(day.date)
    }

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.calendarView.notifyDateChanged(it) }
            binding.calendarView.notifyDateChanged(date)
        }
    }

    private fun onClicked(newPosition: Int, f: Filter) {
        if (newPosition != currentPosition) {
            filterAdapter.list = filterAdapter.list.mapIndexed { index, filter ->
                Filter(filter.id, filter.name, isItemSelected(newPosition, index, filter))
            }
        }
        currentPosition = newPosition
    }

    private fun isItemSelected(newPosition: Int, index: Int, filter: Filter): Boolean {
        return when (index) {
            newPosition -> !filter.isSelected
            currentPosition -> false
            else -> filter.isSelected
        }
    }
}