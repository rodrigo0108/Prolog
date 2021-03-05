package com.heyoh.prolog.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.prolog.databinding.ItemBannerBinding
import com.heyoh.prolog.util.extensions.diffUtil
import com.squareup.picasso.Picasso

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    var list: List<String> by diffUtil(emptyList())

    inner class ViewHolder(private val binding: ItemBannerBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(imageURL: String) {
            with(binding) {
                Picasso.get()
                    .load(imageURL)
                    .into(bannerImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}