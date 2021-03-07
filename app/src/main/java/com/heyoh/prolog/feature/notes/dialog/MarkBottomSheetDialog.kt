package com.heyoh.prolog.feature.notes.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.heyoh.prolog.databinding.BottomSheetDialogMarkBinding

class MarkBottomSheetDialog(val titleRes: Int) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogMarkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDialogMarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleTextView.setText(titleRes)

        binding.weightedTotalScoreCardView.setOnClickListener { dismiss() }
        binding.qualificationCriteriaCardView.setOnClickListener { dismiss() }
    }
}