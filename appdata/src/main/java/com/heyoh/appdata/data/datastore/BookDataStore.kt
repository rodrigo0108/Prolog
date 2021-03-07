package com.heyoh.appdata.data.datastore

import com.heyoh.model.response.BookResponseItem
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

interface BookDataStore {
    suspend fun getBooks(): Result<List<BookResponseItem>, Failure>
}