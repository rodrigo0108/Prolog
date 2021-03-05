package com.heyoh.prolog.classes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.model.Material
import com.heyoh.prolog.databinding.ItemMaterialBinding
import com.heyoh.prolog.util.extensions.diffUtil

class MaterialAdapter(val onCLicked: (material: Material) -> Unit) :
        RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    var list: List<Material> by diffUtil(emptyList())

    inner class ViewHolder(private val binding: ItemMaterialBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(material: Material) {
            with(binding) {
                nameTextView.text = material.titleDocument
                itemView.setOnClickListener {
                    onCLicked.invoke(material)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMaterialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}