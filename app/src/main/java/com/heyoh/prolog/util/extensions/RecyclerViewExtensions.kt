package com.heyoh.prolog.util.extensions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.diffUtil(
        initialValue: List<T>,
        crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
        crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
        kotlin.properties.Delegates.observable(initialValue) { _, old, new ->
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int = old.size

                override fun getNewListSize(): Int = new.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        areItemsTheSame(old[oldItemPosition], new[newItemPosition])

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        areContentsTheSame(old[oldItemPosition], new[newItemPosition])

            }).dispatchUpdatesTo(this@diffUtil)
        }
