package com.mycompany.movies.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.mycompany.movies.databinding.FragmentHomeBinding
import com.mycompany.movies.view.adapter.MoviesPopularAdapter
import com.mycompany.movies.view.adapter.TabViewPagerAdapter
import com.mycompany.movies.viewmodel.fragment.HomeFragmentViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeFragmentViewModel by viewModels()
    private val adapter = MoviesPopularAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.moviesPopular()
        observe()
        setUpTabView()
    }

    private fun observe() {
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                adapter.startShimmerAnimation()
                recycler()
            }else{
                adapter.stopShimmerAnimation()
            }
        }

        viewModel.list.observe(viewLifecycleOwner) { movies ->
            if (movies.isNotEmpty()) {
                adapter.setItem(movies)
            } else {
                viewModel.error.observe(viewLifecycleOwner) { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUpTabView() {
        val tableLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val adapter = TabViewPagerAdapter(context as FragmentActivity)
        viewPager.adapter = adapter
        TabLayoutMediator(tableLayout,viewPager){ tab, position ->
            tab.text = adapter.getTabText(position)
        }.attach()
    }

    private fun recycler() {
        binding.recyclerPopular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerPopular.adapter = adapter


    }


}
