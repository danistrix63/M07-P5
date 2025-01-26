package com.example.m07_p5

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private val foodList = mutableListOf(
        FoodItem(1, "Pizza", "1 unidad", "2024-01-30"),
        FoodItem(2, "Hamburguesa", "2 unidades", "2024-02-05"),
        FoodItem(3, "Ensalada", "1 plato", "2024-02-10"),
        FoodItem(4, "Sushi", "10 piezas", "2024-02-15")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = FoodAdapter(foodList,
            onItemClick = { food ->
                val intent = Intent(requireContext(), EditFoodActivity::class.java).apply {
                    putExtra("FOOD_ID", food.id)
                    putExtra("FOOD_NAME", food.name)
                    putExtra("FOOD_QUANTITY", food.quantity)
                    putExtra("FOOD_DATE", food.date)
                }
                startActivity(intent)
            },
            onItemDelete = { position ->
                adapter.removeItem(position)
            }
        )

        recyclerView.adapter = adapter

        return view
    }
}
