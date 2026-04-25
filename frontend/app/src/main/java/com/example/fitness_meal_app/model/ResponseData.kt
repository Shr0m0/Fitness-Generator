package com.example.fitness_meal_app.model

data class ResponseData(
    val meal_name: String,
    val calories: String,
    val protein: String,
    val carbs: String,
    val fat: String,
    val ingredients: List<String>,
    val instructions: String,
    val tips: String
)