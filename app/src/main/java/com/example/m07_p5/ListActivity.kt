package com.example.m07_p5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var fabAddFood: FloatingActionButton
    private lateinit var bottomNav: BottomNavigationView
    private val foodList = mutableListOf<FoodItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recycler_view)
        fabAddFood = findViewById(R.id.fab_add_food)
        bottomNav = findViewById(R.id.bottom_navigation)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = FoodAdapter(foodList,
            onItemClick = { food ->
                val intent = Intent(this, EditFoodActivity::class.java).apply {
                    putExtra("FOOD_ID", food.id)
                    putExtra("FOOD_NAME", food.name)
                    putExtra("FOOD_QUANTITY", food.quantity)
                    putExtra("FOOD_DATE", food.date)
                }
                startActivity(intent)
            },
            onItemDelete = { position ->
                removeFoodItem(position)
            }
        )

        recyclerView.adapter = adapter

        // Acción del FloatingActionButton para abrir AddFoodActivity
        fabAddFood.setOnClickListener {
            try {
                val intent = Intent(this, AddFoodActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("ListActivity", "Error al abrir AddFoodActivity", e)
                Toast.makeText(this, "Error al abrir la pantalla de agregar alimentos", Toast.LENGTH_SHORT).show()
            }
        }

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
                    // No hace nada porque ya estamos en ListActivity
                    true
                }
                R.id.nav_add_food -> {
                    startActivity(Intent(this, AddFoodActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
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
        bottomNav.selectedItemId = R.id.nav_list
    }

    override fun onResume() {
        super.onResume()
        loadFoodItems()
    }

    private fun loadFoodItems() {
        foodList.clear()
        val sharedPref = getSharedPreferences("food_list", Context.MODE_PRIVATE)
        val foodListString = sharedPref.getString("foods", "[]") ?: "[]"
        val jsonArray = JSONArray(foodListString)

        for (i in 0 until jsonArray.length()) {
            val foodObject = jsonArray.getJSONObject(i)
            val food = FoodItem(
                id = i,
                name = foodObject.getString("name"),
                quantity = foodObject.getString("quantity"),
                date = foodObject.getString("date")
            )
            foodList.add(food)
        }

        adapter.notifyDataSetChanged()
    }

    private fun removeFoodItem(position: Int) {
        val sharedPref = getSharedPreferences("food_list", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val foodListString = sharedPref.getString("foods", "[]") ?: "[]"
        val jsonArray = JSONArray(foodListString)

        jsonArray.remove(position)

        editor.putString("foods", jsonArray.toString())
        editor.apply()

        foodList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}
