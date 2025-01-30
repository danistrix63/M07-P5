package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle

class MainActivity : AppCompatActivity() {

    private lateinit var txtCalories: TextView
    private lateinit var txtMacros: TextView
    private lateinit var listFoodHistory: ListView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asignar vistas
        txtCalories = findViewById(R.id.txt_calories)
        txtMacros = findViewById(R.id.txt_macros)
        listFoodHistory = findViewById(R.id.list_food_history)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navigation_view)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

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

        // Manejo del menú lateral
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navegarSiNoEstaEn(MainActivity::class.java)
                R.id.nav_list -> navegarSiNoEstaEn(ListActivity::class.java)
                R.id.nav_profile -> navegarSiNoEstaEn(LoginActivity::class.java)
                R.id.nav_tracking -> navegarSiNoEstaEn(TrackingActivity::class.java)
                R.id.nav_dates -> navegarSiNoEstaEn(ConsumptionDatesActivity::class.java)
                R.id.nav_add_food -> navegarSiNoEstaEn(AddFoodActivity::class.java)
                R.id.nav_settings -> navegarSiNoEstaEn(PreferencesActivity::class.java)
                R.id.nav_logout -> finishAffinity()
            }
            drawerLayout.closeDrawers()
            true
        }

        // Configurar el BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navegarSiNoEstaEn(MainActivity::class.java)
                R.id.nav_list -> navegarSiNoEstaEn(ListActivity::class.java)
                R.id.nav_settings -> navegarSiNoEstaEn(PreferencesActivity::class.java)
            }
            true
        }

        // Marcar la pestaña activa en el BottomNavigationView
        bottomNavigationView.selectedItemId = when (this::class.java.simpleName) {
            "MainActivity" -> R.id.nav_home
            "ListActivity" -> R.id.nav_list
            "PreferencesActivity" -> R.id.nav_settings
            else -> R.id.nav_home
        }

        // Simular datos de prueba
        actualizarDashboard()
    }

    private fun navegarSiNoEstaEn(destino: Class<*>) {
        if (this::class.java != destino) {
            Log.d("BottomNav", "Navegando a ${destino.simpleName}")
            val intent = Intent(this, destino)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
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

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.nav_home
    }
}