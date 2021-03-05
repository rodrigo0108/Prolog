package com.heyoh.prolog.general.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.model.Book
import com.heyoh.prolog.databinding.ItemBookBinding
import com.heyoh.prolog.databinding.ItemRecentBookBinding
import com.heyoh.prolog.util.extensions.diffUtil
import com.squareup.picasso.Picasso

class BookAdapter(var frontCoverOnly: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<Book> by diffUtil(emptyList())

    inner class AllCoverViewHolder(private val binding: ItemRecentBookBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            with(binding) {
                Picasso.get()
                        .load(book.imageURL)
                        .into(bookShapeableImageView)
                titleBookTextView.text = book.title
                authorBookTextView.text = book.authorName
            }
        }
    }
    inner class FrontCoverViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            with(binding) {
                Picasso.get()
                    .load(book.imageURL)
                    .into(bookShapeableImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (frontCoverOnly) {
            FrontCoverViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else{
            AllCoverViewHolder(ItemRecentBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is AllCoverViewHolder -> list[position].let {
                holder.bind(it)
            }
            is FrontCoverViewHolder ->  list[position].let {
                holder.bind(it)
            }
        }
    }
}