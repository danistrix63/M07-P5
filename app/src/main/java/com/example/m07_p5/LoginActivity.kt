package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupBottomNavigation(R.id.bottom_navigation, R.id.nav_home)

        val btnLogin: Button = findViewById(R.id.btn_login)
        val txtRegister: TextView = findViewById(R.id.txt_register)

        btnLogin.setOnClickListener {
            Toast.makeText(this, "Iniciando sesión...", Toast.LENGTH_SHORT).show()
        }

        txtRegister.setOnClickListener {
            try {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error al abrir RegisterActivity", e)
            }
        }
    }
}
