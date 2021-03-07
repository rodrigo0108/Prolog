package com.heyoh.appdata.mapper

import com.heyoh.model.Book
import com.heyoh.model.response.BookResponseItem

fun List<BookResponseItem>.toDomain() = map {
    Book(
        id = it.id,
        title = it.title,
        imageURL = it.image,
        authorName = it.description,
        content = it.path,
        fileName = it.name
    )
}