package com.mycompany.movies.view.user

import android.content.Intent
import android.os.Bundle
import android.view.View.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.mycompany.movies.databinding.ActivityLoginBinding
import com.mycompany.movies.view.HomeActivity
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

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }


    private fun handleClick() {
        binding.buttonLogin.setOnClickListener {
            handleLogin()
        }
        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            finish()
        }
        binding.createAccount.setOnClickListener {
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }
    }

    private fun handleLogin() {
        val email = binding.editEmail.text.toString().lowercase()
        val password = binding.editPassword.text.toString()
        viewModel.loginUser(email,password)

    }

    private fun observe() {
        viewModel.response.observe(this){
         if (it.status()){
             startActivity(Intent(this, HomeActivity::class.java))
            finish()
         }else{
             Toast.makeText(applicationContext,it.message(), Toast.LENGTH_LONG).show()
         }
        }
    }
}