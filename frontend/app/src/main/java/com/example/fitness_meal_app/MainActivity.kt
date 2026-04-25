package com.example.fitness_meal_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fitness_meal_app.model.RequestData
import com.example.fitness_meal_app.network.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PremiumMealApp()
        }
    }

    private fun generateMeal(
        goal: String,
        calories: String,
        diet: String,
        level: String,
        onResult: (String) -> Unit
    ) {
        val request = RequestData(goal, calories, diet, level)

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.generateMeal(request)

                val formatted = buildString {

                    append("🍽 ${response.meal_name}\n\n")

                    append("🔥 Calories: ${response.calories}\n")
                    append("💪 Protein: ${response.protein}\n")
                    append("🍞 Carbs: ${response.carbs}\n")
                    append("🥑 Fat: ${response.fat}\n\n")

                    append("🥗 Ingredients:\n")
                    response.ingredients.forEach {
                        append("• $it\n")
                    }

                    append("\n👨‍🍳 Instructions:\n")
                    response.instructions
                        .split(Regex("\\d+\\."))
                        .map { it.trim() }
                        .filter { it.isNotEmpty() }
                        .forEachIndexed { index, step ->
                            append("${index + 1}. $step\n")
                        }

                    append("\n💡 Tips:\n")
                    append(response.tips.trim())
                }

                onResult(formatted)

            } catch (e: Exception) {
                onResult("Error: ${e.message}")
                Log.e("API", e.toString())
            }
        }
    }

    @Composable
    fun PremiumMealApp() {

        val burgundy = Color(0xFF800020)
        val darkBg = Color(0xFF0B0B0B)

        var goal by remember { mutableStateOf("") }
        var calories by remember { mutableStateOf("") }
        var diet by remember { mutableStateOf("") }
        var level by remember { mutableStateOf("") }

        var result by remember { mutableStateOf("") }
        var loading by remember { mutableStateOf(false) }

        val scroll = rememberScrollState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(darkBg)
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // HEADER
                Text(
                    text = "🍷 AI Fitness Meal",
                    color = burgundy,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "Generate personalized fitness meals instantly",
                    color = Color.Gray
                )

                // INPUT CARD
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(30.dp, RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF141414)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            "INPUT",
                            color = burgundy,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(12.dp))

                        PremiumField("Goal", goal) { goal = it }
                        PremiumField("Calories", calories) { calories = it }
                        PremiumField("Diet", diet) { diet = it }
                        PremiumField("Level", level) { level = it }

                        Spacer(Modifier.height(12.dp))

                        Button(
                            onClick = {
                                loading = true
                                generateMeal(goal, calories, diet, level) {
                                    result = it
                                    loading = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = burgundy
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Generate Meal ✨")
                        }
                    }
                }

                // OUTPUT CARD
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(40.dp, RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF101010)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            "RESULT",
                            color = burgundy,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(10.dp))

                        if (loading) {
                            CircularProgressIndicator(color = burgundy)
                            Text("Generating meal...", color = Color.Gray)
                        } else {
                            Text(
                                text = result.ifEmpty {
                                    "Your AI meal will appear here..."
                                },
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun PremiumField(
        label: String,
        value: String,
        onChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            label = { Text(label, color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color(0xFF800020),
                focusedBorderColor = Color(0xFF800020),
                unfocusedBorderColor = Color.Gray
            )
        )
    }
}