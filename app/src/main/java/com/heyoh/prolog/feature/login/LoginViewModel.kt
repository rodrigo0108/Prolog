package com.heyoh.prolog.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyoh.appdata.domain.usecase.GetBannerUseCase
import com.heyoh.appdata.domain.usecase.GetBooksUseCase
import com.heyoh.appdata.domain.usecase.LoginUseCase
import com.heyoh.appdata.mapper.toDomain
import com.heyoh.model.request.LoginRequest
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result
import com.heyoh.model.token.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
        private val loginUseCase: LoginUseCase,
        private val getBannerUseCase: GetBannerUseCase,
        private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _model = MutableLiveData<LoginModel>()
    val model: LiveData<LoginModel> get() = _model

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _model.value = LoginModel.Loading
            val result = withContext(Dispatchers.IO) {
                loginUseCase.invoke(loginRequest)
            }
            when (result) {
                is Result.Success -> {
                    val token = Token()
                    token.authToken = result.value.access_token
                    token.gradeId = result.value.assing_courses[0].course.gradeId
                    val bannerInvoke = async(Dispatchers.IO) {
                        getBannerUseCase.invoke()
                    }
                    val bookInvoke = async(Dispatchers.IO) {
                        getBooksUseCase.invoke()
                    }
                    val bannerResult = bannerInvoke.await()
                    val bookResult = bookInvoke.await()

                    if (bannerResult is Result.Success && bookResult is Result.Success){
                        _model.value = LoginModel.Success(
                                listCurses = ArrayList(result.value.toDomain()),
                                listBannerItems = ArrayList(bannerResult.value.toDomain()),
                                listBooks = ArrayList(bookResult.value.toDomain())
                        )
                    }else{
                        _model.value = LoginModel.Error(ErrorType.SERVER)
                    }
                }
                is Result.Error -> {
                    when (result.value) {
                        is Failure.ClientError -> {
                            with((result.value as Failure.ClientError).apiErrorResponse) {
                                if (statusCode == 403) {
                                    _model.value = LoginModel.Error(ErrorType.INVALID_CREDENTIALS, message)
                                } else {
                                    _model.value = LoginModel.Error(ErrorType.SERVER)
                                }
                            }
                        }
                        else -> {
                            _model.value = LoginModel.Error(ErrorType.SERVER)
                        }

                    }
                }
            }
        }
    }
}