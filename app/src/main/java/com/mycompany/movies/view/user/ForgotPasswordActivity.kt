package com.mycompany.movies.view.user

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mycompany.movies.databinding.ActivityForgotPasswordBinding
import com.mycompany.movies.viewmodel.ForgotPasswordViewModel

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var viewModel: ForgotPasswordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
        setContentView(binding.root)

        handleClick()
        observe()

    }

    private fun handleClick() {
        binding.button.setOnClickListener {
            handleForgot()
        }
    }

    private fun handleForgot() {
        val email = binding.editEmail.text.toString()
        viewModel.recuperationLogin(email)
    }

    private fun observe() {
       viewModel.message.observe(this){
            Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_SHORT).show()
       }
    }


}