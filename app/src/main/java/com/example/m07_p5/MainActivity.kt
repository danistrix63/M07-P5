package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle

class MainActivity : AppCompatActivity() {

    private lateinit var txtCalories: TextView
    private lateinit var txtMacros: TextView
    private lateinit var listFoodHistory: ListView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asignar vistas
        txtCalories = findViewById(R.id.txt_calories)
        txtMacros = findViewById(R.id.txt_macros)
        listFoodHistory = findViewById(R.id.list_food_history)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navigation_view)

        // Configurar la Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Habilitar el botón del menú lateral
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Manejo del menú lateral con todas las opciones correctas
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.nav_list -> startActivity(Intent(this, ListActivity::class.java))
                R.id.nav_profile -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.nav_tracking -> startActivity(Intent(this, TrackingActivity::class.java))
                R.id.nav_dates -> startActivity(Intent(this, ConsumptionDatesActivity::class.java))
                R.id.nav_add_food -> startActivity(Intent(this, AddFoodActivity::class.java))
                R.id.nav_settings -> startActivity(Intent(this, PreferencesActivity::class.java))
                R.id.nav_logout -> finishAffinity()
            }
            drawerLayout.closeDrawers()
            true
        }

        // Simular datos de prueba
        actualizarDashboard()
    }

    private fun actualizarDashboard() {
        val caloriasConsumidas = 1800
        val carbohidratos = 220
        val proteinas = 90
        val grasas = 60
        val historialAlimentos = listOf("Manzana - 95 kcal", "Pollo - 250 kcal", "Arroz - 200 kcal")

        txtCalories.text = "Calorías consumidas hoy: $caloriasConsumidas kcal"
        txtMacros.text = "Carbohidratos: ${carbohidratos}g | Proteínas: ${proteinas}g | Grasas: ${grasas}g"

        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_list_item_1, historialAlimentos)
        listFoodHistory.adapter = adapter
    }
}