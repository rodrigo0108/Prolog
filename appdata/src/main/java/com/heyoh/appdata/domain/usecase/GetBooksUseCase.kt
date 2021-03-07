package com.heyoh.appdata.domain.usecase

import com.heyoh.appdata.domain.repository.BookRepository
import com.heyoh.model.response.BookResponseItem
import com.heyoh.model.result.Failure
import com.heyoh.model.result.Result

class GetBooksUseCase (private val bookRepository: BookRepository) {
    suspend operator fun invoke(): Result<List<BookResponseItem>, Failure> = bookRepository.getBooks()
}