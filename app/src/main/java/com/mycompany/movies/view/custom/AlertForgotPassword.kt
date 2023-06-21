package com.mycompany.movies.view.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.mycompany.movies.databinding.AlertDialogCustomBinding

class AlertForgotPassword(context: Context) : Dialog(context) {
    private lateinit var binding: AlertDialogCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AlertDialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
    }


    private fun handleClick() {
        binding.imageClose.setOnClickListener {
            dismiss()
        }
    }
}