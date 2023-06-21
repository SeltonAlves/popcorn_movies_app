package com.mycompany.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.mycompany.movies.model.ValidationModel


class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private val _response = MutableLiveData<ValidationModel>()
    val response: LiveData<ValidationModel> = _response

    fun recuperationLogin(email: String) {
        if (email != "") {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    _response.value = ValidationModel()
                }
            }.addOnFailureListener {
                _response.value = ValidationModel("Erro, Verifique o email Informado.")
            }
        } else {
            _response.value =
                ValidationModel("Erro, entre em Contato com os Nossos Desenvolvedores.")
        }

    }


}