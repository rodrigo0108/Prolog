package com.heyoh.prolog.general.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.heyoh.prolog.R
import com.heyoh.prolog.databinding.DialogLoaderBinding

class DialogLoader(ctx:Context): Dialog(ctx, R.style.DialogLoaderTheme) {

    private lateinit var binding: DialogLoaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding = DialogLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}