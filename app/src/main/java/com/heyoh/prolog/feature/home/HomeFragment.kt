package com.heyoh.prolog.feature.home

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.heyoh.model.Book
import com.heyoh.prolog.databinding.FragmentHomeBinding
import com.heyoh.prolog.feature.home.adapter.BannerAdapter
import com.heyoh.prolog.feature.general.adapter.BookAdapter
import com.heyoh.prolog.feature.general.dialog.DownloadBookBottomSheetDialog
import com.heyoh.prolog.feature.recentbooks.RecentBookActivity
import com.heyoh.prolog.util.Constant
import com.heyoh.prolog.util.extensions.checkPermission

class HomeFragment : Fragment() {

    private lateinit var banneradapter: BannerAdapter
    private lateinit var bookAdapter: BookAdapter

    lateinit var listBanner : ArrayList<String>
    lateinit var listBooks : ArrayList<Book>
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val bannerHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listBanner = requireActivity().intent.getStringArrayListExtra(Constant.BANNER_EXTRA)?: arrayListOf()
        listBooks = requireActivity().intent.getParcelableArrayListExtra(Constant.BOOK_EXTRA)?: arrayListOf()
    }

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
        bookAdapter = BookAdapter(frontCoverOnly = true, onClicked = ::onBookClicked)
        binding.bannerViewPager.adapter = banneradapter
        binding.booksRecyclerView.adapter = bookAdapter
        banneradapter.list = listBanner
        bookAdapter.list = listBooks

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
    private val writeExternalStoragePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> Toast.makeText(requireContext(),"¡Permiso concedido!", Toast.LENGTH_SHORT).show()
                shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                    //this option is available starting in API 23
                    Toast.makeText(requireContext(),"Permiso denegado", Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(requireContext(),"Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }

    private fun onBookClicked(contentURL: String, fileName:String){
        if (requireContext().checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            DownloadBookBottomSheetDialog(contentURL, fileName).show(parentFragmentManager, "")
        }else{
            writeExternalStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
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