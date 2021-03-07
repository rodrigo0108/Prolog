package com.heyoh.prolog.feature.general.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.model.Filter
import com.heyoh.prolog.R
import com.heyoh.prolog.databinding.ItemFilterBinding
import com.heyoh.prolog.util.extensions.diffUtil
import com.heyoh.prolog.util.extensions.setTextBackgroundTintRes
import com.heyoh.prolog.util.extensions.setTextColorRes

class FilterAdapter(val onClicked: (position:Int, filter:Filter)->Unit) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    var list: List<Filter> by diffUtil(emptyList())

    inner class ViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filter: Filter, position: Int) {
            with(binding) {
                filterNameTextView.text = filter.name
                if (filter.isSelected){
                    filterNameTextView.setTextColorRes(android.R.color.white)
                    filterNameTextView.setTextBackgroundTintRes(R.color.prolog_primary_soft_color)
                }else{
                    filterNameTextView.setTextColorRes(R.color.prolog_filter_text_unselected_color)
                    filterNameTextView.setTextBackgroundTintRes(R.color.prolog_filter_unselected_color)
                }
                itemView.setOnClickListener {
                    onClicked.invoke(position,filter)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
                ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }
}