package com.example.m07_p5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class PreferencesActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var switchDarkMode: Switch
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Cargar SharedPreferences
        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)

        // Aplicar el modo oscuro antes de cargar el layout
        if (sharedPreferences.getBoolean("dark_mode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        // Configurar Toolbar y DrawerLayout (Menú lateral)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navigation_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, findViewById(R.id.toolbar),
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar navegación lateral
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navegarSiNoEstaEn(MainActivity::class.java)
                R.id.nav_list -> navegarSiNoEstaEn(ListActivity::class.java)
                R.id.nav_settings -> {} // Ya estamos en esta actividad
            }
            drawerLayout.closeDrawers()
            true
        }

        // Configurar BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navegarSiNoEstaEn(MainActivity::class.java)
                R.id.nav_list -> navegarSiNoEstaEn(ListActivity::class.java)
                R.id.nav_settings -> {} // Ya estamos en esta actividad
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.nav_settings
    }

    private fun navegarSiNoEstaEn(destino: Class<*>) {
        if (this::class.java != destino) {
            Log.d("BottomNav", "Navegando a ${destino.simpleName}")
            val intent = Intent(this, destino)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
