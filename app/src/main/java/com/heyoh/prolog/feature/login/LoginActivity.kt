package com.heyoh.prolog.feature.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.heyoh.model.request.LoginRequest
import com.heyoh.prolog.R
import com.heyoh.prolog.databinding.ActivityLoginBinding
import com.heyoh.prolog.feature.general.dialog.DialogLoader
import com.heyoh.prolog.feature.menu.MenuActivity
import com.heyoh.prolog.util.AlertDialogUtil
import com.heyoh.prolog.util.Constant.BANNER_EXTRA
import com.heyoh.prolog.util.Constant.BOOK_EXTRA
import com.heyoh.prolog.util.Constant.COURSE_EXTRA
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var dialogLoader: DialogLoader
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogLoader = DialogLoader(this)
        initBinding()
        initObservable()
        binding.enterMaterialButton.setOnClickListener {
            viewModel.login(LoginRequest(
                    username = binding.userNameEditText.text.toString(),
                    password = binding.passwordEditText.text.toString()))
        }
    }

    private fun initObservable() {
        viewModel.model.observe(this, Observer(::loginResult))
    }

    private fun loginResult(model: LoginModel) {
        when (model) {
            is LoginModel.Success -> {
                val mIntent = Intent(this@LoginActivity, MenuActivity::class.java)
                mIntent.putExtra(COURSE_EXTRA, model.listCurses)
                mIntent.putExtra(BANNER_EXTRA, model.listBannerItems)
                mIntent.putExtra(BOOK_EXTRA, model.listBooks)
                startActivity(mIntent)
                finish()
            }
            is LoginModel.Error -> {
                showProgress(false)
                when (model.type) {
                    ErrorType.INVALID_CREDENTIALS -> {
                        AlertDialogUtil.show(this,
                                titleRes = R.string.login_failed,
                                message = model.message,
                                positiveButtonRes = R.string.understood)
                    }
                    ErrorType.SERVER -> {
                        AlertDialogUtil.show(this,
                                titleRes = R.string.server_error,
                                messageRes = R.string.try_again,
                                positiveButtonRes = R.string.understood)
                    }
                }
            }
            is LoginModel.Loading ->{
                showProgress(true)
            }
        }
    }

    private fun showProgress(isProgress: Boolean) {
        if (isProgress) {
            dialogLoader.show()
        } else {
            dialogLoader.hide()
        }
    }

    private fun initBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}