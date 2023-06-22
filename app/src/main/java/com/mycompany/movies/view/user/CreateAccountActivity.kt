package com.mycompany.movies.view.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mycompany.movies.databinding.ActivityCreateAccountBinding
import com.mycompany.movies.view.HomeActivity
import com.mycompany.movies.viewmodel.CreateAccountViewModel

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CreateAccountViewModel::class.java]
        setContentView(binding.root)

        handleClick()
        observe()
    }

    private fun handleClick() {
        binding.buttonCreateAccount.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val passwordConfirm = binding.editConfirmPassword.text.toString()
        viewModel.createAccount(email = email, password = password, confirmPassword = passwordConfirm)
    }

    private fun observe() {
        viewModel.status.observe(this){
            if (it.status()){
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,it.message(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}
