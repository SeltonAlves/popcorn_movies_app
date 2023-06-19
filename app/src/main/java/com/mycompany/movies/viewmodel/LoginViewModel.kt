package com.mycompany.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.mycompany.movies.R
import com.mycompany.movies.model.ValidationLoginModel

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val _response = MutableLiveData<ValidationLoginModel>()
    val response: LiveData<ValidationLoginModel> = _response


    fun loginUser(email: String, password: String) {
        if (email != "" && password != "") {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                _response.value = ValidationLoginModel()
            }.addOnFailureListener {
                val message = when(it){
                    is FirebaseAuthWeakPasswordException -> R.string.password_great.toString()
                    is FirebaseAuthInvalidCredentialsException -> R.string.email_invalid.toString()
                    is FirebaseAuthUserCollisionException -> R.string.Email_registered.toString()
                    else -> R.string.error_generic.toString()
                }
                _response.value = ValidationLoginModel(message)
            }
        } else {
            _response.value = ValidationLoginModel(R.string.inconsistent_parameters.toString())
        }
    }

}