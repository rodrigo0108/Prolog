package com.heyoh.prolog.feature.course.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.model.Course
import com.heyoh.prolog.databinding.ItemCourseBinding
import com.heyoh.prolog.util.extensions.diffUtil
import com.heyoh.prolog.util.extensions.getFirstThreeCharacters

class CourseAdapter(val onCLicked: (course: Course) -> Unit) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    var list: List<Course> by diffUtil(emptyList())

    inner class ViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            with(binding) {
                abbreviateNameCourseTextView.text = course.name.getFirstThreeCharacters()
                fullNameCourseTextView.text = course.name
                teacherNameTextView.text = course.teacherName
                itemView.setOnClickListener {
                    onCLicked.invoke(course)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}