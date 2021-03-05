package com.heyoh.prolog.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.heyoh.prolog.R
import com.heyoh.prolog.databinding.ActivityMenuBinding
import com.heyoh.prolog.databinding.LayoutToolbarBinding
import com.heyoh.prolog.util.extensions.hideViewWithPreCheck
import com.heyoh.prolog.util.extensions.showViewWithPreCheck
import com.heyoh.prolog.profile.ProfileActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var navController: NavController
    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        with(binding.toolbarLayout) {
            when (destination.id) {
                R.id.courseOptionsFragment -> {
                    showSpecificToolbar(this)
                    toolbarTitleTextView.setText(R.string.my_courses)
                }
                R.id.myClassesFragment -> {
                    showSpecificToolbar(this)
                    toolbarTitleTextView.setText(R.string.my_class)
                }
                R.id.attendanceFragment -> {
                    showSpecificToolbar(this)
                    toolbarTitleTextView.setText(R.string.my_attendance)
                }
                R.id.notesFragment -> {
                    showSpecificToolbar(this)
                    toolbarTitleTextView.setText(R.string.my_grades)
                }
                else -> {
                    showGeneralToolbar(this)
                }
            }
        }
    }

    private fun showSpecificToolbar(layoutBinding: LayoutToolbarBinding) {
        layoutBinding.generalToolbarConstraintLayout.hideViewWithPreCheck()
        layoutBinding.specificToolbarConstraintLayout.showViewWithPreCheck()
    }

    private fun showGeneralToolbar(layoutBinding: LayoutToolbarBinding) {
        layoutBinding.generalToolbarConstraintLayout.showViewWithPreCheck()
        layoutBinding.specificToolbarConstraintLayout.hideViewWithPreCheck()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupBottomNavMenu(navController)
        binding.toolbarLayout.backFrameLayout.setOnClickListener {
            navController.popBackStack()
        }
        binding.toolbarLayout.authorShapeableImageView.setOnClickListener {
            val mIntent = Intent(this@MenuActivity, ProfileActivity::class.java)
            startActivity(mIntent)
        }
    }

    private fun initBinding() {
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav?.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        navController.removeOnDestinationChangedListener(listener)
        super.onPause()
    }
}
    