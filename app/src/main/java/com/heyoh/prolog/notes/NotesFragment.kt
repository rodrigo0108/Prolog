package com.heyoh.prolog.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.heyoh.model.Evaluation
import com.heyoh.prolog.R
import com.heyoh.prolog.databinding.FragmentMyNotesBinding
import com.heyoh.prolog.util.extensions.getFirstThreeCharacters
import com.heyoh.prolog.notes.adapter.EvaluationAdapter
import com.heyoh.prolog.notes.dialog.MarkBottomSheetDialog

class NotesFragment : Fragment() {
    private var _binding: FragmentMyNotesBinding? = null
    private val binding get() = _binding!!
    private val evaluationAdapter = EvaluationAdapter()
    private val args: NotesFragmentArgs by navArgs()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyNotesBinding.inflate(inflater, container, false)
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

        binding.evaluationRecyclerView.adapter = evaluationAdapter
        evaluationAdapter.list = listOf(
                Evaluation("","Clase 1","Nombre de la evuluación","12-02-2020","18"),
                Evaluation("","Clase 2","Nombre de la evuluación","12-02-2020","14")
        )
        binding.weightedTotalScoreCardView.setOnClickListener {
            MarkBottomSheetDialog(R.string.weighted_total).show(childFragmentManager, "")
        }
        binding.totalScoreCardView.setOnClickListener {
            MarkBottomSheetDialog(R.string.total).show(childFragmentManager, "")
        }
    }
}