package com.heyoh.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Course (val id:String, val name:String, val teacherName:String) : Parcelable