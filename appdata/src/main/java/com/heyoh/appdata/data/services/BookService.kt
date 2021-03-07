package com.heyoh.appdata.data.services

import com.heyoh.appdata.data.api.APIUrl
import com.heyoh.model.response.BookResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BookService {
    @GET(APIUrl.BOOK)
    suspend fun getBooks(
            @Header("Authorization")  authorization: String,
            @Path("gradeId") gradeId: Int): Response<List<BookResponseItem>>
}