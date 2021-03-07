package com.heyoh.prolog.feature.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.model.Timetable
import com.heyoh.model.sealed.TimeTypeItem
import com.heyoh.prolog.databinding.ItemBreakBinding
import com.heyoh.prolog.databinding.ItemCourseCalendarBinding
import com.heyoh.prolog.util.extensions.diffUtil
import com.heyoh.prolog.util.extensions.getFirstThreeCharacters

class TimetableAdapter  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<TimeTypeItem> by diffUtil(emptyList())

    companion object{
        const val VIEW_TYPE_CLASS = 0
        const val VIEW_TYPE_BREAK = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position]){
            is TimeTypeItem.Class -> VIEW_TYPE_CLASS
            is TimeTypeItem.Break -> VIEW_TYPE_BREAK
            else -> throw IllegalStateException()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         return when(viewType){
            VIEW_TYPE_CLASS-> ClassViewHolder(ItemCourseCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_BREAK-> BreakViewHolder(ItemBreakBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalStateException()
         }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ClassViewHolder -> (list[position] as TimeTypeItem.Class).timetable.let {
                holder.bind(it)
            }
            is BreakViewHolder ->  (list[position] as TimeTypeItem.Break).timetable.let {
                holder.bind(it)
            }
        }
    }
    inner class ClassViewHolder(private val binding: ItemCourseCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(timetable: Timetable) {
            with(binding) {
                hourTextView.text = timetable.timeClass
                abbreviateNameCourseTextView.text = timetable.courseName.getFirstThreeCharacters()
                fullNameCourseTextView.text = timetable.courseName
            }
        }
    }
    inner class BreakViewHolder(private val binding: ItemBreakBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(timetable: Timetable) {
            with(binding) {
                hourTextView.text = timetable.timeClass
            }
        }
    }
}