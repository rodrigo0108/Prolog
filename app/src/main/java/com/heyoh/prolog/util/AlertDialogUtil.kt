package com.heyoh.prolog.util

import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

object AlertDialogUtil{
    fun show(
            context: Context,
            title: String? = null,
            message: String? = null,
            positiveButton: String? = null,
            negativeButton: String? = null,
            customTitle: View? = null,
            @StringRes titleRes: Int? = null,
            @StringRes messageRes: Int? = null,
            @StringRes positiveButtonRes: Int? = null,
            @StringRes negativeButtonRes: Int? = null,
            positiveCallback: (() -> Unit)? = null,
            negativeCallback: (() -> Unit)? = null,
            @DrawableRes iconRes: Int? = null,
            isCancelable: Boolean = false,
            backPressedCallback: (() -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(context)

        if (titleRes != null)
            builder.setTitle(titleRes)
        else
            title?.let { builder.setTitle(it) }

        customTitle?.let {
            builder.setCustomTitle(customTitle)
        }

        if (messageRes != null)
            builder.setMessage(messageRes)
        else
            message?.let { builder.setMessage(it) }

        if (positiveButtonRes != null) {
            builder.setPositiveButton(positiveButtonRes) { _, _ ->
                positiveCallback?.invoke()
            }
        } else if (positiveButton != null) {
            builder.setPositiveButton(positiveButton) { _, _ ->
                positiveCallback?.invoke()
            }
        }

        if (negativeButtonRes != null) {
            builder.setNegativeButton(negativeButtonRes) { _, _ ->
                negativeCallback?.invoke()
            }
        } else if (negativeButton != null) {
            builder.setNegativeButton(negativeButton) { _, _ ->
                negativeCallback?.invoke()
            }
        }

        iconRes?.let {
            builder.setIcon(iconRes)
        }

        builder.setCancelable(isCancelable)
        builder.setOnKeyListener { dialog: DialogInterface, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (isCancelable){
                    dialog.cancel()
                }
                backPressedCallback?.invoke()
            }
            false //allow the backPressed caught
        }
        builder.show()
    }
}