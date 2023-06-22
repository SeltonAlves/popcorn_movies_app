package com.mycompany.movies.view.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.mycompany.movies.databinding.AlertDialogCustomBinding

class AlertForgotPassword(context: Context) : Dialog(context) {
    private lateinit var binding: AlertDialogCustomBinding
    private var onClose: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AlertDialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
    }

    fun setOnClose(listener: () ->Unit){
        onClose =listener
    }

    private fun handleClick() {
        binding.imageClose.setOnClickListener {
            onClose?.invoke()
            dismiss()
        }
    }
}