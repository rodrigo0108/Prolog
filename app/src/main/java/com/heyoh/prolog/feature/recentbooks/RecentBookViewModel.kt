package com.heyoh.prolog.feature.recentbooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyoh.appdata.domain.usecase.GetBooksUseCase
import com.heyoh.appdata.mapper.toDomain
import com.heyoh.model.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecentBookViewModel(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {
    private val _model = MutableLiveData<RecentBookModel>()
    val model: LiveData<RecentBookModel> get() = _model

    fun getBooks() {
        viewModelScope.launch {
            _model.value = RecentBookModel.Loading
            val bookResult = withContext(Dispatchers.IO) {
                getBooksUseCase.invoke()
            }
            when (bookResult) {
                is Result.Success -> _model.value =
                    RecentBookModel.Success(ArrayList(bookResult.value.toDomain()))
                is Result.Error -> _model.value = RecentBookModel.Error
            }
        }
    }
}