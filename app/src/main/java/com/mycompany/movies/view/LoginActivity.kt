package com.mycompany.movies.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mycompany.movies.databinding.ActivityLoginBinding
import com.mycompany.movies.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding.root)
        handleClick()
        observe()
    }

    private fun handleClick() {
        binding.buttonLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        viewModel.loginUser(email,password)

    }

    private fun observe() {
        viewModel.reponse.observe(this){
            if (it == true){
               startActivity(Intent(this,HomeActivity::class.java))
               finish()
            }
        }
    }
}