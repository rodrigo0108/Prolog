package com.heyoh.prolog.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.heyoh.prolog.databinding.ActivityLoginBinding
import com.heyoh.prolog.general.dialog.DialogLoader
import com.heyoh.prolog.menu.MenuActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        binding.enterMaterialButton.setOnClickListener {
            DialogLoader(this).show()
            Handler(Looper.getMainLooper()).postDelayed({
                val mIntent = Intent(this@LoginActivity, MenuActivity::class.java)
                startActivity(mIntent)
                finish()
            }, 4000)
        }
    }

    private fun initBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}