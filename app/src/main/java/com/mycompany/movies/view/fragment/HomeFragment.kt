package com.mycompany.movies.view.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.movies.databinding.FragmentHomeBinding
import com.mycompany.movies.view.adapter.MoviesPopularAdapter
import com.mycompany.movies.viewmodel.fragment.HomeFragmentViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val adapter = MoviesPopularAdapter()
    private val viewModel: HomeFragmentViewModel by viewModels()
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
    }

    private fun observe() {
        viewModel.list.observe(viewLifecycleOwner) { movies ->
            if (movies.size == 10) {
                adapter.setItem(movies)
                recycler()
            } else {
                viewModel.error.observe(viewLifecycleOwner) { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun recycler() {
        binding.recyclerPopular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerPopular.adapter = adapter
 }


}
