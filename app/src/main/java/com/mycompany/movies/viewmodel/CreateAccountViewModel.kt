package com.mycompany.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.mycompany.movies.model.ValidationModel

class CreateAccountViewModel(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private val _status = MutableLiveData<ValidationModel>()
    val status: LiveData<ValidationModel> = _status

    fun createAccount(email: String, password: String, confirmPassword: String) {
        if (email != "" && password != "" && confirmPassword != ""){
            if (password.contentEquals(confirmPassword)){
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        _status.value = ValidationModel()
                    }
                }.addOnFailureListener {

                    _status.value = ValidationModel("erro")
                }
            }

        }else{
            _status.value = ValidationModel("Parâmetros Vazios, Verifique.")
        }
    }

}