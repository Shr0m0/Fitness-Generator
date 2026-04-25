# 🍽️ AI Fitness Meal Generator (Full Stack AI App)

A full-stack AI-powered application that generates personalized fitness meal plans using Google Gemini. The app takes user fitness goals and dietary preferences and instantly returns structured meal recommendations with ingredients, nutrition values, and cooking instructions.

---

# 🚀 Project Description

The AI Fitness Meal Generator helps users create customized meal plans based on their fitness goals.

Users input:

- 🎯 Goal (lose weight, build muscle, stay healthy, etc.)
- 🔥 Calories target
- 🥗 Diet type (high protein, low carb, balanced, etc.)
- 💪 Fitness level (beginner, intermediate, advanced)

The AI generates:

- 🍽 Meal name
- 🔥 Calorie breakdown
- 💪 Protein / Carbs / Fat values
- 🥗 Ingredients list
- 👨‍🍳 Step-by-step cooking instructions
- 💡 Nutrition tips

The goal is to simplify healthy eating using AI personalization.

---

# 🏗 Architecture Overview

Android App (Jetpack Compose) → FastAPI Backend → Google Gemini API

Flow:

1. User enters fitness preferences in Android UI  
2. App sends HTTP request using Retrofit  
3. FastAPI backend receives request  
4. Backend builds structured prompt for Gemini  
5. Gemini generates structured meal plan  
6. Backend parses JSON response  
7. Android app displays formatted result in UI cards  

---

# 🧠 Tech Stack

## 📱 Android (Frontend)
- Kotlin + Jetpack Compose
- Material 3 UI
- Custom dark neon UI (black + burgundy theme)
- Retrofit networking
- State management with Compose `remember`
- Scrollable modern UI layout

## 🐍 Backend
- FastAPI (Python)
- Pydantic models
- CORS middleware
- Google Gemini API integration
- JSON response formatting

## 🤖 AI Model
- Google Gemini (gemini-2.5-flash-lite)
- Generates structured meal plans
- Returns nutrition + recipe data

---

# ⚙️ Setup Instructions

## 1️⃣ Clone Repository

git clone https://github.com/Shr0m0/Fitness-Generator.git 
cd fitness-generator  

---

## 2️⃣ Backend Setup

cd backend  
python -m venv venv  
venv\Scripts\activate   # Windows  

pip install -r requirements.txt  

Create `.env` file:

GEMINI_API_KEY=your_api_key_here  

Run backend:

uvicorn main:app --reload --host 0.0.0.0 --port 8000  

Backend runs at:
http://127.0.0.1:8000  

Swagger docs:
http://127.0.0.1:8000/docs  

---

## 3️⃣ Android Setup

Open project in Android Studio  

Make sure Retrofit base URL is:

http://10.0.2.2:8000/  

Run app on emulator or device.

---

# 🌐 How It Works

1. Open app  
2. Enter:
   - Goal  
   - Calories  
   - Diet  
   - Level  
3. Tap “Generate Meal”  
4. AI processes request  
5. Meal plan is returned  
6. UI displays structured recipe card  

---

# 💡 Features

- AI-powered meal generation  
- Personalized fitness recipes  
- Macro breakdown (protein, carbs, fat)  
- Ingredient lists  
- Step-by-step instructions  
- Nutrition tips  
- Premium dark UI (black + burgundy neon theme)  
- Smooth scrollable layout  
- Real-time API integration  

---

# 🎨 UI Design

- Black background (luxury theme)  
- Burgundy accent (#800020)  
- Neon-style card shadows  
- Clean spacing system  
- White readable input fields  
- Modern Compose UI structure  

---

# 👨‍💻 Author

Built using:

- Kotlin (Jetpack Compose)  
- FastAPI (Python)  
- Google Gemini API  
- Retrofit networking  

---

# ⭐ Project Goal

To demonstrate a real-world AI-powered full-stack application combining:

- Mobile development  
- Backend API engineering  
- LLM integration  
- Modern UI/UX design  