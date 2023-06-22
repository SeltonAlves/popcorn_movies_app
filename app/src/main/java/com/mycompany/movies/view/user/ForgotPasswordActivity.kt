package com.mycompany.movies.view.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mycompany.movies.databinding.ActivityForgotPasswordBinding
import com.mycompany.movies.view.custom.AlertForgotPassword
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
        binding.imageArrow.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }

    private fun handleForgot() {
        val email = binding.editEmail.text.toString()
        viewModel.recuperationLogin(email)
    }

    private fun observe() {
        viewModel.response.observe(this) {
            if (it.status()) {
                dialogCustom()
            } else {
                binding.editEmail.setText("")
                Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables", "PrivateResource")
    private fun dialogCustom() {
       val alert = AlertForgotPassword(this)
        alert.setCancelable(false)
        alert.setOnClose {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        alert.show()
    }

}