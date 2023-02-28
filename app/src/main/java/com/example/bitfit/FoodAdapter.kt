package com.example.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter (private val context: Context, private val foods: List<FoodEntity>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>()
{
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val foodNameTextView = itemView.findViewById<TextView>(R.id.food_name_text_view)
        val caloriesNumTextView = itemView.findViewById<TextView>(R.id.calories_number)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.food_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: FoodAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val food: FoodEntity = foods.get(position)
        // Set item views based on your views and data model
        val foodNameTextView = viewHolder.foodNameTextView
        foodNameTextView.setText(food.food)
        val caloriesNumTextView = viewHolder.caloriesNumTextView
        caloriesNumTextView.setText(food.calories)

    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return foods.size
    }
}