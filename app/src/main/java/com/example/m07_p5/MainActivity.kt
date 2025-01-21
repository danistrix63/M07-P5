package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.m07_p5.PreferencesActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.btn_register).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        findViewById<Button>(R.id.btn_recover_password).setOnClickListener {
            startActivity(Intent(this, RecoverPasswordActivity::class.java))
        }

        findViewById<Button>(R.id.btn_tracking).setOnClickListener {
            startActivity(Intent(this, TrackingActivity::class.java))
        }

        findViewById<Button>(R.id.btn_add_food).setOnClickListener {
            startActivity(Intent(this, AddFoodActivity::class.java))
        }

        findViewById<Button>(R.id.btn_preferences).setOnClickListener {
            startActivity(Intent(this, PreferencesActivity::class.java))
        }

        // Botón para ir a la actividad de Fechas de Consumo
        findViewById<Button>(R.id.btn_dates).setOnClickListener {
            startActivity(Intent(this, ConsumptionDatesActivity::class.java))
        }
    }
}
