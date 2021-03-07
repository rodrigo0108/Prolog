package com.heyoh.prolog.feature.recentbooks

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import com.heyoh.prolog.R
import com.heyoh.prolog.databinding.ActivityRecentBookBinding
import com.heyoh.prolog.feature.general.adapter.BookAdapter
import com.heyoh.prolog.feature.general.dialog.DialogLoader
import com.heyoh.prolog.feature.general.dialog.DownloadBookBottomSheetDialog
import com.heyoh.prolog.util.AlertDialogUtil
import com.heyoh.prolog.util.GridSpacingItemDecoration
import com.heyoh.prolog.util.extensions.checkPermission
import org.koin.android.viewmodel.ext.android.viewModel


class RecentBookActivity : AppCompatActivity() {

    private val viewModel: RecentBookViewModel by viewModel()
    private lateinit var dialogLoader: DialogLoader

    private lateinit var binding: ActivityRecentBookBinding
    private var bookAdapter = BookAdapter(frontCoverOnly = false, onClicked = ::onBookClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initObservable()
        setUpToolbar()
        binding.recentBookRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                0,
                resources.getDimensionPixelSize(R.dimen.size_10),
                true,
                0
            )
        )
        binding.recentBookRecyclerView.adapter = bookAdapter
        dialogLoader = DialogLoader(this)
        viewModel.getBooks()

    }

    private fun setUpToolbar() {
        binding.toolbarSpecificLayout.toolbarTitleTextView.setText(R.string.recent_books)
    }

    private fun initBinding() {
        binding = ActivityRecentBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initObservable() {
        viewModel.model.observe(this, Observer(::booksResult))
    }

    private fun booksResult(model: RecentBookModel) {
        showProgress(model is RecentBookModel.Loading)
        when (model) {
            is RecentBookModel.Success -> bookAdapter.list = model.listBooks
            is RecentBookModel.Error -> {
                AlertDialogUtil.show(
                    this,
                    titleRes = R.string.server_error,
                    messageRes = R.string.try_again,
                    positiveButtonRes = R.string.understood
                )
            }
        }
    }

    private val writeExternalStoragePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> Toast.makeText(this, "¡Permiso concedido!", Toast.LENGTH_SHORT).show()
                shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                    //this option is available starting in API 23
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }

    private fun showProgress(isProgress: Boolean) {
        if (isProgress) {
            dialogLoader.show()
        } else {
            dialogLoader.hide()
        }
    }

    private fun onBookClicked(contentURL: String, fileName: String) {
        if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            DownloadBookBottomSheetDialog(contentURL, fileName).show(supportFragmentManager, "")
        } else {
            writeExternalStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }
}