package com.mycompany.movies.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val _reponse = MutableLiveData<Boolean>()
    val reponse: LiveData<Boolean> = _reponse


     fun loginUser(email: String, password: String) {
        if (password.length >= 8) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                _reponse.value = it.isSuccessful
            }
        }else{
            _reponse.value = false
        }
    }


}