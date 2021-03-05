package com.heyoh.prolog.util.extensions

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


internal fun TextView.setTextBackgroundTintRes(@ColorRes color: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, color)
}

internal fun TextView.setTextColorRes(@ColorRes color: Int) =
    setTextColor(ContextCompat.getColor(context, color))