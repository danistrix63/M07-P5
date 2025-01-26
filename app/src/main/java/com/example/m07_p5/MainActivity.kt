package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_login)?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.btn_register)?.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        findViewById<Button>(R.id.btn_recover_password)?.setOnClickListener {
            startActivity(Intent(this, RecoverPasswordActivity::class.java))
        }

        findViewById<Button>(R.id.btn_tracking)?.setOnClickListener {
            startActivity(Intent(this, TrackingActivity::class.java))
        }

        findViewById<Button>(R.id.btn_add_food)?.setOnClickListener {
            startActivity(Intent(this, AddFoodActivity::class.java))
        }

        findViewById<Button>(R.id.btn_preferences)?.setOnClickListener {
            startActivity(Intent(this, PreferencesActivity::class.java))
        }

        findViewById<Button>(R.id.btn_dates)?.setOnClickListener {
            startActivity(Intent(this, ConsumptionDatesActivity::class.java))
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_list -> {
                    loadFragment(ListFragment())
                    true
                }
                R.id.nav_settings -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }

        // Cargar fragmento inicial (Home)
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
