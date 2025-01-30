package com.example.m07_p5

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditFoodActivity : BaseActivity() {

    private lateinit var editFoodName: EditText
    private lateinit var editFoodQuantity: EditText
    private lateinit var editFoodDate: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_food)

        setupBottomNavigation(R.id.bottom_navigation, R.id.nav_list)

        editFoodName = findViewById(R.id.edit_food_name)
        editFoodQuantity = findViewById(R.id.edit_food_quantity)
        editFoodDate = findViewById(R.id.edit_food_date)
        btnSave = findViewById(R.id.btn_save_food)
        btnDelete = findViewById(R.id.btn_delete_food)

        val foodId = intent.getIntExtra("FOOD_ID", -1)
        val foodName = intent.getStringExtra("FOOD_NAME") ?: ""
        val foodQuantity = intent.getStringExtra("FOOD_QUANTITY") ?: ""
        val foodDate = intent.getStringExtra("FOOD_DATE") ?: ""

        editFoodName.setText(foodName)
        editFoodQuantity.setText(foodQuantity)
        editFoodDate.setText(foodDate)

        btnSave.setOnClickListener {
            Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnDelete.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Eliminar alimento")
            .setMessage("¿Estás seguro de que quieres eliminar este alimento?")
            .setPositiveButton("Sí") { _, _ ->
                Toast.makeText(this, "Alimento eliminado", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}
