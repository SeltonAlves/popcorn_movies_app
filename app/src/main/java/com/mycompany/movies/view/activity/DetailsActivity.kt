package com.mycompany.movies.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.movies.databinding.ActivityDetailsBinding
import com.mycompany.movies.util.Constraints
import com.mycompany.movies.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var code: Int? = null
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        code = savedInstanceState?.getInt(Constraints.KEY_CODE_MOVIES)

        setUpTabView()
        observe()
    }




    private fun setUpTabView() {
        val tableLayout = binding.tabLayout
        val viewPager = binding.viewPager



    }

    private fun observe() {

    }
}