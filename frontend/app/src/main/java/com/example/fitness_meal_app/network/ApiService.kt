package com.example.fitness_meal_app.network

import com.example.fitness_meal_app.model.RequestData
import com.example.fitness_meal_app.model.ResponseData
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("generate")
    suspend fun generateMeal(@Body request: RequestData): ResponseData
}