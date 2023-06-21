package com.mycompany.movies.view.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mycompany.movies.databinding.ActivityCreateAccountBinding
import com.mycompany.movies.viewmodel.CreateAccountViewModel

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CreateAccountViewModel::class.java]
        setContentView(binding.root)
    }
}