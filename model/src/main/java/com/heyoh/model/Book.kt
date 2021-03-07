package com.heyoh.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: String,
    val title: String,
    val imageURL: String,
    val fileName: String,
    val authorName: String,
    val content: String
) : Parcelable