package com.mycompany.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.mycompany.movies.R
import com.mycompany.movies.model.ValidationModel

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val _response = MutableLiveData<ValidationModel>()
    val response: LiveData<ValidationModel> = _response


    fun loginUser(email: String, password: String) {
        if (email != "" && password != "") {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                _response.value = ValidationModel()
            }.addOnFailureListener {
                val message = when(it){
                    is FirebaseAuthWeakPasswordException -> "senha maior que 6 digitos"
                    is FirebaseAuthInvalidCredentialsException -> "Email invalido ou senha invalida."
                    is FirebaseNetworkException -> "Erro de Conexão verifique se está ligado o WIFI ou Dados Móveis"
                    is FirebaseAuthUserCollisionException -> "Email já cadastrado."
                    else -> "Entre em Contato com os Desenvolvedores."
                }
                _response.value = ValidationModel(message)
            }
        } else {
            _response.value = ValidationModel(R.string.inconsistent_parameters.toString())
        }
    }

}