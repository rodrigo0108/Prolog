package com.heyoh.prolog.feature.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.heyoh.model.Course
import com.heyoh.prolog.feature.course.adapter.CourseAdapter
import com.heyoh.prolog.databinding.FragmentCourseBinding
import com.heyoh.prolog.util.Constant.COURSE_EXTRA

class CourseFragment: Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!
    lateinit var listCourses : ArrayList<Course>
    private val adapter = CourseAdapter(::onCourseClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listCourses = requireActivity().intent.getParcelableArrayListExtra(COURSE_EXTRA)?: arrayListOf()
    }

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
        adapter.list = listCourses
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