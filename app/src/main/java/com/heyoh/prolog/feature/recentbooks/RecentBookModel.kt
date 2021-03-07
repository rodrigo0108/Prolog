package com.heyoh.prolog.feature.recentbooks

import com.heyoh.model.Book

sealed class RecentBookModel {
    data class Success(val listBooks: ArrayList<Book>) : RecentBookModel()
    object Error : RecentBookModel()
    object Loading : RecentBookModel()
}