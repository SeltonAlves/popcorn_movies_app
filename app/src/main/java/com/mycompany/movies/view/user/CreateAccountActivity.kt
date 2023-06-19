package com.mycompany.movies.view.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.movies.R
import com.mycompany.movies.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}