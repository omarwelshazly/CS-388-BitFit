package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the recyclerview in activity layout
        val rvFoods = findViewById<View>(R.id.recyclerView) as RecyclerView
        // Initialize contacts
        val foods = arrayListOf<FoodEntity>()
        // Create adapter passing in the sample user data
        val adapter = FoodAdapter(this, foods)
        // Attach the adapter to the recyclerview to populate items
        rvFoods.adapter = adapter
        // Set layout manager to position the items
        rvFoods.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false ).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            rvFoods.addItemDecoration(dividerItemDecoration)

        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("FOOD_LIST", foods)
            startActivity(intent)
        }

        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map() { entity ->
                    FoodEntity(
                        entity.food,
                        entity.calories,
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    adapter.notifyDataSetChanged()

                }
            }
        }

    }
}