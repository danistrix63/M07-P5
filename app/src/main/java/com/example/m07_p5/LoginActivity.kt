package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btn_login)
        val txtRegister: TextView = findViewById(R.id.txt_register)

        // Botón de iniciar sesión (pendiente de implementar funcionalidad)
        btnLogin.setOnClickListener {
            // Aquí podrías agregar la lógica de autenticación
        }

        // Botón de "Registrarse" para abrir RegisterActivity
        txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
