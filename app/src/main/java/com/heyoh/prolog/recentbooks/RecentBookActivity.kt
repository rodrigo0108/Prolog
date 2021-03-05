package com.heyoh.prolog.recentbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heyoh.model.Book
import com.heyoh.prolog.R
import com.heyoh.prolog.databinding.ActivityRecentBookBinding
import com.heyoh.prolog.general.adapter.BookAdapter


class RecentBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecentBookBinding
    private var bookAdapter = BookAdapter(frontCoverOnly = false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        binding.toolbarSpecificLayout.toolbarTitleTextView.setText(R.string.recent_books)
        //binding.recentBookRecyclerView.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.size_10)))
        binding.recentBookRecyclerView.adapter = bookAdapter
        bookAdapter.list = listOf(
                Book("","Matemática y Fisica","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1396815892l/15994634.jpg", "George R.R. Martin"),
                Book("","Trigonometria","https://images-na.ssl-images-amazon.com/images/I/51ZpoP2rFaL._SX331_BO1,204,203,200_.jpg","George R.R. Martin"),
                Book("","Juego de Tronos","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1396815892l/15994634.jpg","George R.R. Martin"),
                Book("","Matemática y Fisica","https://images-na.ssl-images-amazon.com/images/I/51ZpoP2rFaL._SX331_BO1,204,203,200_.jpg","George R.R. Martin"),
                Book("","Trigonometria","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1396815892l/15994634.jpg","George R.R. Martin"),
                Book("","Juego de Tronos","https://images-na.ssl-images-amazon.com/images/I/51ZpoP2rFaL._SX331_BO1,204,203,200_.jpg","George R.R. Martin")
        )
    }
    private fun initBinding() {
        binding = ActivityRecentBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}