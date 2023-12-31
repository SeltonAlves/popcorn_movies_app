package com.mycompany.movies.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.movies.databinding.ActivityMainBinding
import com.mycompany.movies.view.user.LoginActivity

class ScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handlerSplashScreen()

    }

    private fun handlerSplashScreen() {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }, 3000)
    }

}