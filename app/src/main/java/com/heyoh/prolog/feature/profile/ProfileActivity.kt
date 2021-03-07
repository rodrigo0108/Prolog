package com.heyoh.prolog.feature.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heyoh.prolog.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        binding.backFrameLayout.setOnClickListener { finish() }
    }

    private fun initBinding() {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}