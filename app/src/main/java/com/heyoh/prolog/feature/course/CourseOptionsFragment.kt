package com.heyoh.prolog.feature.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.heyoh.prolog.databinding.FragmentCourseOptionsBinding
import com.heyoh.prolog.util.extensions.getFirstThreeCharacters

class CourseOptionsFragment : Fragment() {
    private var _binding: FragmentCourseOptionsBinding? = null
    private val binding get() = _binding!!
    private val args: CourseOptionsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myClassCardView.setOnClickListener {
            val action = CourseOptionsFragmentDirections.navigateToMyClassesFragment(args.course)
            findNavController().navigate(action)
        }

        binding.myAttendanceCardView.setOnClickListener {
            val action = CourseOptionsFragmentDirections.navigateToAttendanceFragment(args.course)
            findNavController().navigate(action)
        }
        binding.myMarkCardView.setOnClickListener {
            val action = CourseOptionsFragmentDirections.navigateToNotesFragment(args.course)
            findNavController().navigate(action)
        }

        binding.abbreviateNameCourseTextView.text = args.course.name.getFirstThreeCharacters()
        binding.fullNameCourseTextView.text = args.course.name
        binding.teacherNameTextView.text = args.course.teacherName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}