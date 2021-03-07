package com.heyoh.prolog.feature.login

import com.heyoh.model.Book
import com.heyoh.model.Course

sealed class LoginModel {
    data class Success(val listCurses: ArrayList<Course>,
                       val listBannerItems: ArrayList<String>,
                       val listBooks: ArrayList<Book>) : LoginModel()

    data class Error(val type: ErrorType, val message: String? = null) : LoginModel()
    object Loading : LoginModel()
}

enum class ErrorType {
    SERVER, INVALID_CREDENTIALS
}
