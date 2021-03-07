package com.heyoh.appdata.domain.repository

import com.heyoh.model.response.BookResponseItem
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

interface BookRepository {
    suspend fun getBooks(): Result<List<BookResponseItem>, Failure>
}