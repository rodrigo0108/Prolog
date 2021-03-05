package com.heyoh.prolog.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.heyoh.model.Course
import com.heyoh.prolog.R
import com.heyoh.prolog.course.adapter.CourseAdapter
import com.heyoh.prolog.databinding.FragmentCourseBinding

class CourseFragment: Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!
    private val adapter = CourseAdapter(::onCourseClicked)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.courseRecyclerView.adapter = adapter
        adapter.list = listOf(
                Course("","Física","Prof: Cristian Vizcaya"),
                Course("","Biología","Prof: Cristian Vizcaya"),
                Course("","Lenguaje","Prof: Cristian Vizcaya"),
                Course("","Matemática","Prof: Cristian Vizcaya"),
                Course("","Química","Prof: Cristian Vizcaya"),
                Course("","Razonamiento Verbal","Prof: Cristian Vizcaya"),
                Course("","Razonamiento Matemático","Prof: Cristian Vizcaya"))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onCourseClicked(course:Course){
        val action = CourseFragmentDirections.navigateToCourseOptionsFragment(course)
        findNavController().navigate(action)
    }
}