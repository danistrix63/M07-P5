package com.example.m07_p5

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupBottomNavigation(R.id.bottom_navigation, R.id.nav_home)

        val btnRegister: Button = findViewById(R.id.btn_register)

        btnRegister.setOnClickListener {
            Toast.makeText(this, "Registrando usuario...", Toast.LENGTH_SHORT).show()
        }
    }
}
