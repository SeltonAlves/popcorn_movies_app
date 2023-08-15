package com.mycompany.movies.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mycompany.movies.databinding.FragmentFutureMoviesBinding
import com.mycompany.movies.util.OnClickListener
import com.mycompany.movies.view.activity.DetailsActivity
import com.mycompany.movies.view.adapter.MainMoviesAdapter
import com.mycompany.movies.viewmodel.fragment.FutureMoviesFragmentViewModel

class FutureMoviesFragment : Fragment() {
    private lateinit var binding: FragmentFutureMoviesBinding
    private val viewModel: FutureMoviesFragmentViewModel by viewModels()
    private val adapter = MainMoviesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFutureMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFutureMovies()
        recycler()
        observe()
        onClick()
    }

    private fun onClick() {
        val listener = object : OnClickListener {
            override fun onClick(code: Int?) {
                if (code != null) {
                    val bundle = Bundle()
                    bundle.putInt("code", code)
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }

        }
        adapter.setOnClick(listener)
    }

    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) adapter.start() else adapter.stopShimmer()
        }
        viewModel.movies.observe(viewLifecycleOwner) {
            if (viewModel.error.value != null) {
                Toast.makeText(context, viewModel.error.value, Toast.LENGTH_LONG).show()
            } else {
                adapter.setItem(it)
            }
        }
    }

    private fun recycler() {
        GridLayoutManager(context, 2)
        binding.recyclerFutureMovies.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerFutureMovies.adapter = adapter
    }
}
