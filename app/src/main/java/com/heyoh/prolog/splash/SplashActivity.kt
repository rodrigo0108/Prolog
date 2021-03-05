package com.heyoh.prolog.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.heyoh.prolog.databinding.ActivitySplashBinding
import com.heyoh.prolog.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

        Handler(Looper.getMainLooper()).postDelayed({
            val mIntent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(mIntent)
            finish()
        }, 2000)
    }
    private fun initBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}