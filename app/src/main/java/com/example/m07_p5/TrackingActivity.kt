package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class TrackingActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)

        bottomNav = findViewById(R.id.bottom_navigation)

        // Configuración del Bottom Navigation
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (javaClass.simpleName != "MainActivity") {
                        startActivity(Intent(this, MainActivity::class.java))
                        overridePendingTransition(0, 0)
                        finish()
                    }
                    true
                }
                R.id.nav_list -> {
                    if (javaClass.simpleName != "ListActivity") {
                        startActivity(Intent(this, ListActivity::class.java))
                        overridePendingTransition(0, 0)
                        finish()
                    }
                    true
                }
                R.id.nav_tracking -> {
                    // No hace nada porque ya estamos en TrackingActivity
                    true
                }
                R.id.nav_settings -> {
                    if (javaClass.simpleName != "PreferencesActivity") {
                        startActivity(Intent(this, PreferencesActivity::class.java))
                        overridePendingTransition(0, 0)
                        finish()
                    }
                    true
                }
                else -> false
            }
        }

        // Marcar la pestaña activa en el BottomNavigationView
        bottomNav.selectedItemId = R.id.nav_tracking
    }
}
