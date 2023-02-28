package com.example.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter (private val context: Context, private val foods: List<FoodEntity>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>()
{


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.food_layout, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: FoodAdapter.ViewHolder, position: Int) {
        val food: FoodEntity = foods[position]
        viewHolder.itemView.findViewById<TextView>(R.id.food_name_text_view).text = food.food
        viewHolder.itemView.findViewById<TextView>(R.id.calories_number).text = food.calories

    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return foods.size
    }
}