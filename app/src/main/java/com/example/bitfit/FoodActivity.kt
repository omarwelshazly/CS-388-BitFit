package com.example.bitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_layout)

        var tempFood : FoodEntity
        val foods = intent.getSerializableExtra("FOOD_LIST") as ArrayList<FoodEntity>
        val button = findViewById<Button>(R.id.recordButton)
        button.setOnClickListener{
            val inputFoodName = findViewById<EditText>(R.id.foodNameInput).text.toString()
            val inputCaloriesNum = findViewById<EditText>(R.id.calaories_number_input).text.toString()
            tempFood = FoodEntity(inputFoodName, inputCaloriesNum)
            foods.add(tempFood)
            lifecycleScope.launch(IO) {
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(inputFoodName, inputCaloriesNum)
                )
            }
            findViewById<EditText>(R.id.foodNameInput).text.clear()
            findViewById<EditText>(R.id.calaories_number_input).text.clear()
            finish()
        }
    }
}