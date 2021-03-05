package com.heyoh.prolog.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.heyoh.model.Book
import com.heyoh.prolog.databinding.FragmentHomeBinding
import com.heyoh.prolog.home.adapter.BannerAdapter
import com.heyoh.prolog.general.adapter.BookAdapter
import com.heyoh.prolog.recentbooks.RecentBookActivity

class HomeFragment : Fragment() {

    private lateinit var banneradapter: BannerAdapter
    private lateinit var bookAdapter: BookAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val bannerHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        banneradapter = BannerAdapter()
        bookAdapter = BookAdapter(frontCoverOnly = true)
        binding.bannerViewPager.adapter = banneradapter
        binding.booksRecyclerView.adapter = bookAdapter
        banneradapter.list = listOf("https://www.instasent.com/blog/wp-content/uploads/2019/08/5a15760d6eb11.jpg",
                "https://st3.depositphotos.com/5572200/19461/v/1600/depositphotos_194617050-stock-illustration-summer-sale-banner-sample-text.jpg",
                "https://i.ytimg.com/vi/WsRjUu0ktfQ/maxresdefault.jpg")
        bookAdapter.list = listOf(
            Book("","","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1396815892l/15994634.jpg", ""),
            Book("","","https://images-na.ssl-images-amazon.com/images/I/51ZpoP2rFaL._SX331_BO1,204,203,200_.jpg",""),
            Book("","","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1396815892l/15994634.jpg",""),
            Book("","","https://images-na.ssl-images-amazon.com/images/I/51ZpoP2rFaL._SX331_BO1,204,203,200_.jpg",""),
            Book("","","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1396815892l/15994634.jpg",""),
            Book("","","https://images-na.ssl-images-amazon.com/images/I/51ZpoP2rFaL._SX331_BO1,204,203,200_.jpg","")
        )

        binding.bannerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bannerHandler.removeCallbacks(bannerRunnable)
                bannerHandler.postDelayed(bannerRunnable, 3000)
            }
        })
        binding.seeAllButton.setOnClickListener {
            val mIntent = Intent(requireActivity(), RecentBookActivity::class.java)
            startActivity(mIntent)
        }
        TabLayoutMediator(binding.dotsTabLayout,binding.bannerViewPager){ _, _-> }.attach()
    }

    private val bannerRunnable = Runnable {
        if (binding.bannerViewPager.currentItem == banneradapter.list.size - 1) {
            binding.bannerViewPager.setCurrentItem(0, false)
        } else {
            binding.bannerViewPager.currentItem = binding.bannerViewPager.currentItem + 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        bannerHandler.removeCallbacks(bannerRunnable)
    }


}