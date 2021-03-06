package com.heyoh.prolog.feature.general.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.heyoh.prolog.databinding.BottomSheetDialogDownloadBookBinding
import com.heyoh.prolog.util.DownloadUtil

class DownloadBookBottomSheetDialog(val contentURL: String,val fileName:String) : BottomSheetDialogFragment() {
    private var _binding: BottomSheetDialogDownloadBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDialogDownloadBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.downloadButton.setOnClickListener {
            DownloadUtil.downloadPDF(contentURL, fileName, requireContext())
            dismiss()
        }
    }
}