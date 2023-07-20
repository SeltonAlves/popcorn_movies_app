package com.mycompany.movies.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mycompany.movies.R
import com.mycompany.movies.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextSearch.isClickable = true
        binding.editTextSearch.isFocusable = false

        handleClick()
        initNavigation()
    }
    @SuppressLint("ResourceType")
    private fun handleClick() {
        binding.editTextSearch.setOnClickListener {
            binding.editTextSearch.isFocusableInTouchMode = true
            binding.editTextSearch.isFocusable = true
        }
        binding.editTextSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val searchText = binding.editTextSearch.text.toString()
                if (searchText.isNotBlank()) {
                    val args = Bundle().apply {
                        putString("searchText", searchText)
                    }
                    navController.navigate(R.id.search, args)
                }

                binding.editTextSearch.isFocusableInTouchMode = true
                binding.editTextSearch.isFocusable = true
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navigationBottom, navController)

        binding.navigationBottom.setOnItemSelectedListener { item ->
            return@setOnItemSelectedListener when (item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.home)
                    true
                }

                R.id.search -> {
                    navController.navigate(R.id.search)
                    true
                }

                R.id.user -> {
                    navController.navigate(R.id.user)
                    true
                }

                else -> false
            }
        }

    }
}