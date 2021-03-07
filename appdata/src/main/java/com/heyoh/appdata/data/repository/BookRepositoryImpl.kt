package com.heyoh.appdata.data.repository

import com.heyoh.appdata.data.datastore.BookDataStore
import com.heyoh.appdata.domain.repository.BookRepository
import com.heyoh.model.response.BookResponseItem
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

class BookRepositoryImpl (private val bookDataStore: BookDataStore): BookRepository {
    override suspend fun getBooks(): Result<List<BookResponseItem>, Failure> {
        return bookDataStore.getBooks()
    }
}