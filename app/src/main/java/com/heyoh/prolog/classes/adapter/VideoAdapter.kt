package com.heyoh.prolog.classes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.model.Video
import com.heyoh.prolog.databinding.ItemVideoBinding
import com.heyoh.prolog.util.extensions.diffUtil

class VideoAdapter (val onCLicked: (video: Video) -> Unit) :
        RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    var list: List<Video> by diffUtil(emptyList())

    inner class ViewHolder(private val binding: ItemVideoBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video) {
            with(binding) {
                itemView.setOnClickListener {
                    onCLicked.invoke(video)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}