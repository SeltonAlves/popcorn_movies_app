package com.mycompany.movies.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mycompany.movies.databinding.ActivityDetailsBinding
import com.mycompany.movies.model.InfomationMovies
import com.mycompany.movies.view.adapter.ViewPagerAdapter
import com.mycompany.movies.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var code = 0
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        code = intent.extras!!.getInt("code")

        viewModel.getIdMovies(code)
        setUpTabView()
        observe()
        onClick()
    }

    private fun onClick() {
        binding.imageArrow.setOnClickListener {
            finish()
        }
    }


    private fun setUpTabView() {
        val tableLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tableLayout, viewPager) { tab, position ->
            tab.text = adapter.getTab(position)
        }.attach()


    }

    private fun observe() {


        viewModel.movies.observe(this) { movies ->
            if (movies != null) {
                viewModel.getMovies { details: InfomationMovies ->
                    binding.textNameMovies.text = details.nameMovies
                    binding.imagePoster.background = null
                    binding.imageBackground.background = null
                    binding.imagePoster.setImageBitmap(details.posterMovies)
                    binding.imageBackground.setImageBitmap(details.backdrop)
                    binding.textMovieReview.text = details.voteAverage
                    binding.shimmerLayoutImagePoster.hideShimmer()
                    binding.shimmerLayoutBackdrop.hideShimmer()

                }
            }
        }
        viewModel.error.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}