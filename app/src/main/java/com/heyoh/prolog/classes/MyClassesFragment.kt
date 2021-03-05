package com.heyoh.prolog.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.heyoh.model.*
import com.heyoh.prolog.classes.adapter.MaterialAdapter
import com.heyoh.prolog.classes.adapter.TaskAdapter
import com.heyoh.prolog.classes.adapter.VideoAdapter
import com.heyoh.prolog.databinding.FragmentMyClassesBinding
import com.heyoh.prolog.util.extensions.getFirstThreeCharacters
import com.heyoh.prolog.general.adapter.FilterAdapter

class MyClassesFragment : Fragment() {
    private var _binding: FragmentMyClassesBinding? = null
    private val binding get() = _binding!!
    private val args: MyClassesFragmentArgs by navArgs()
    private val materialAdapter = MaterialAdapter(::onMaterialClicked)
    private val taskAdapter = TaskAdapter(::onTaskClicked)
    private val videoAdapter = VideoAdapter(::onVideoClicked)
    private val filterAdapter = FilterAdapter(::onFilterClicked)
    private var currentPosition = -1

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyClassesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.abbreviateNameCourseTextView.text = args.course.name.getFirstThreeCharacters()
        binding.fullNameCourseTextView.text = args.course.name
        binding.teacherNameTextView.text = args.course.teacherName
        binding.monthTextView.text = "Agosto"
        binding.dayRangeTextView.text = "/ 20 feb al 25 feb"


        binding.filterRecyclerView.adapter = filterAdapter
        binding.materialRecyclerView.adapter = materialAdapter
        binding.tasksRecyclerView.adapter = taskAdapter
        binding.videoRecyclerView.adapter = videoAdapter

        filterAdapter.list = listOf(
                Filter("", "Clase 1"),
                Filter("", "Clase 2"),
                Filter("", "Clase 3"),
                Filter("", "Clase 4"))
        materialAdapter.list = listOf(
                Material("", "Poleas y fuerzas.pdf", ""),
                Material("", "Resistencias y fuerzas.pdf", ""))

        taskAdapter.list = listOf(
                Task("", "Tarea 1", "Poleas y fuerzas.pdf", ""),
                Task("", "Tarea 2", "Resistencias y fuerzas.pdf", ""))

        videoAdapter.list = listOf(
                Video("", "", ""),
                Video("", "", ""))

        onFilterClicked(0, Filter("", "Clase 1"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onFilterClicked(newPosition: Int, f: Filter) {
        if (newPosition != currentPosition) {
            filterAdapter.list = filterAdapter.list.mapIndexed { index, filter ->
                Filter(filter.id, filter.name, isItemSelected(newPosition, index, filter))
            }
        }
        currentPosition = newPosition
        updateTitleClass(f.name)
    }

    private fun onMaterialClicked(material: Material) {

    }

    private fun onTaskClicked(task: Task) {

    }

    private fun onVideoClicked(video: Video) {

    }

    private fun isItemSelected(newPosition: Int, index: Int, filter: Filter): Boolean {
        return when (index) {
            newPosition -> !filter.isSelected
            currentPosition -> false
            else -> filter.isSelected
        }
    }

    private fun updateTitleClass(name: String) {
        binding.numberClassTextView.text = name
    }
}