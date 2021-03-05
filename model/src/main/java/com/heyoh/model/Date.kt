package com.heyoh.model

import com.heyoh.model.enum.DateType
import java.time.LocalDate

data class Date(val id: String, val date: LocalDate, val type: DateType)