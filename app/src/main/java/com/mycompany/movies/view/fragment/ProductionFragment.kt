package com.mycompany.movies.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mycompany.movies.R
import com.mycompany.movies.databinding.FragmentProductionBinding

class ProductionFragment : Fragment() {
    private lateinit var binding: FragmentProductionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductionBinding.inflate(inflater,container,false)
        return binding.root
    }

}