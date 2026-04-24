import os
from fastapi import FastAPI
from pydantic import BaseModel
from dotenv import load_dotenv
import google.generativeai as genai
from fastapi.middleware.cors import CORSMiddleware
import json

load_dotenv()

genai.configure(api_key=os.getenv("GEMINI_API_KEY"))

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class RequestData(BaseModel):
    goal: str
    calories: str
    diet: str
    level: str

@app.post("/generate")
async def generate(data: RequestData):

    prompt = f"""
    You are a fitness meal planning AI.

    Return ONLY valid JSON:

    {{
      "meal_name": "",
      "calories": "",
      "protein": "",
      "carbs": "",
      "fat": "",
      "ingredients": [],
      "instructions": "",
      "tips": ""
    }}

    Goal: {data.goal}
    Calories: {data.calories}
    Diet: {data.diet}
    Level: {data.level}
    """

    model = genai.GenerativeModel("gemini-2.5-flash-lite")
    response = model.generate_content(prompt)

    cleaned = response.text.replace("```json", "").replace("```", "")

    parsed = json.loads(cleaned)

    return parsed