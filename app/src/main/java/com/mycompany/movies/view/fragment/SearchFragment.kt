package com.mycompany.movies.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.movies.databinding.FragmentSearchBinding
import com.mycompany.movies.model.Result
import com.mycompany.movies.view.adapter.SearchAdapter
import com.mycompany.movies.viewmodel.fragment.SearchFragmentViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var searchText: String? = null
    private val viewModel: SearchFragmentViewModel by viewModels()
    private lateinit var adapter: SearchAdapter

    companion object {
        private var list = mutableListOf<Result>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapter = SearchAdapter()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchText = arguments?.getString("searchText")
        backFragment()
        viewModel.searchMovies(searchText)
        recycler()
        observe()
    }
    private fun backFragment() {
        if (list.isNotEmpty() && searchText == null) {
            visible(false)
            adapter.setItem(list)
        }else{
            if (list.size == 20 && searchText != null){
                list.clear()
                viewModel.searchMovies(searchText)
            }
        }
    }

    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                visible(true)
            } else {
                visible(false)
            }
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            if (list.isEmpty()) {
                list = (it as List).toMutableList()
                adapter.setItem(list)
            }
        }
    }

    private fun recycler() {
        binding.recyclerResultSearch.layoutManager = LinearLayoutManager(context)
        binding.recyclerResultSearch.adapter = adapter
    }

    private fun visible(action: Boolean) {
        if (action) {
            binding.progressBar.visibility = View.VISIBLE
            binding.textInformation.visibility = View.GONE
            binding.textSorry.visibility = View.GONE
            binding.imageSearchNotFound.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.textInformation.visibility = View.GONE
            binding.textSorry.visibility = View.GONE
            binding.imageSearchNotFound.visibility = View.GONE
            binding.recyclerResultSearch.visibility = View.VISIBLE
        }
    }
}
