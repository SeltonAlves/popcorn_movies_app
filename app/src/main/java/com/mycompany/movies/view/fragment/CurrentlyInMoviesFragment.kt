package com.mycompany.movies.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mycompany.movies.databinding.FragmentCurrentlyInMoviesBinding
import com.mycompany.movies.view.adapter.MainMoviesAdapter
import com.mycompany.movies.viewmodel.fragment.FutureMoviesFragmentViewModel


class CurrentlyInMoviesFragment : Fragment() {

    private lateinit var binding : FragmentCurrentlyInMoviesBinding
    private val viewModel: FutureMoviesFragmentViewModel by viewModels()
    private val adapter = MainMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentlyInMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentlyInMovies()
        recycler()
        observe()
    }

    private fun observe() {
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                adapter.start()
            }else{
                adapter.stopShimmer()
            }
        }
        viewModel.movies.observe(viewLifecycleOwner) { list ->
            if (viewModel.error.value == "") {
                Toast.makeText(context, viewModel.error.value, Toast.LENGTH_SHORT).show()
            } else {
                adapter.setItem(list)
            }
        }
    }


    private fun recycler() {
        val layout = GridLayoutManager(context, 2)
        binding.recyclerCurrently.layoutManager = layout
        binding.recyclerCurrently.adapter = adapter
    }

}