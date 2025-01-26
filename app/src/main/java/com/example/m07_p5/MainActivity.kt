package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configurar DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navigation_view)
        bottomNav = findViewById(R.id.bottom_navigation)

        val toggle = androidx.appcompat.app.ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Manejo del menú lateral (NavigationView)
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.nav_list -> startActivity(Intent(this, ListActivity::class.java))
                R.id.nav_profile -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.nav_tracking -> startActivity(Intent(this, TrackingActivity::class.java))
                R.id.nav_dates -> startActivity(Intent(this, ConsumptionDatesActivity::class.java))
                R.id.nav_add_food -> startActivity(Intent(this, AddFoodActivity::class.java))
                R.id.nav_settings -> startActivity(Intent(this, PreferencesActivity::class.java))
                R.id.nav_logout -> {
                    finishAffinity() // Cierra la app completamente
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        // Manejo del Bottom Navigation
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
        when (javaClass.simpleName) {
            "MainActivity" -> bottomNav.selectedItemId = R.id.nav_home
            "ListActivity" -> bottomNav.selectedItemId = R.id.nav_list
            "PreferencesActivity" -> bottomNav.selectedItemId = R.id.nav_settings
        }
    }
}
