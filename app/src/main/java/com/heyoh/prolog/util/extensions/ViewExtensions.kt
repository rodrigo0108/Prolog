package com.heyoh.prolog.util.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.hideViewWithPreCheck() {
    if (this.isVisible) {
        this.isVisible = false
    }
}

fun View.showViewWithPreCheck() {
    if (!this.isVisible) {
        this.isVisible = true
    }
}