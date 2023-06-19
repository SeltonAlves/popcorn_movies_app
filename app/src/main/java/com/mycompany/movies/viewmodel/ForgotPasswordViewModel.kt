package com.mycompany.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordViewModel(application: Application): AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val _message = MutableLiveData<String>()
    val message :LiveData<String> = _message

    fun recuperationLogin(email: String) {
        if (email != ""){
            auth.sendPasswordResetEmail(email).addOnSuccessListener {
                _message.value = "VERIFIQUE SUA CAIXA DE MESSAGEM DO E-MAIL INFORMADO POR FAVOR."
            }.addOnFailureListener {
                _message.value = "Erro, verifique o email Informado, por favor."
            }
        }

    }


}