package com.mycompany.movies.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mycompany.movies.databinding.FragmentSearchBinding
import com.mycompany.movies.view.adapter.SearchAdapter
import com.mycompany.movies.viewmodel.fragment.SearchFragmentViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var searchText: String? = null
    private val viewModel: SearchFragmentViewModel by viewModels()
    private val adapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        searchText = arguments?.getString("searchText")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchMovies(movies = searchText)
        observe()
        recycler()
    }

    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {

            } else if (loading == false) {

            } else {
                binding.recyclerResultSearch.visibility = View.GONE
                binding.imageSearchNotFound.visibility = View.VISIBLE
                binding.textSorry.visibility = View.VISIBLE
                binding.textInformation.visibility = View.VISIBLE
            }
        }

    }


    private fun recycler() {
    }
}