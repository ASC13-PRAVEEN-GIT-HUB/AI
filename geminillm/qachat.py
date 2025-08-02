from dotenv import load_dotenv
import streamlit as st
import os
import google.generativeai as genai

# Load environment variables
load_dotenv()

# Get API key from .env file
genai.configure(api_key=os.getenv("GOOGLE_API_KEY"))

# Create Gemini model
model = genai.GenerativeModel("gemini-2.5-flash")
chat = model.start_chat(history=[])

def get_gemini_response(question):
    response = chat.send_message(question)  # Removed stream=True for simplicity
    return response.text  # Get full text response

# Streamlit UI
st.set_page_config(page_title="Q&A")
st.header("LLM MODEL BY PRAVEEN ")

if 'chat_history' not in st.session_state:
    st.session_state['chat_history'] = []

user_input = st.text_input("Input:", key="input")
submit = st.button("Ask the question")

if submit and user_input:
    response = get_gemini_response(user_input)

    # Save history
    st.session_state['chat_history'].append(("You", user_input))
    st.session_state['chat_history'].append(("Bot", response))

    st.subheader("The response is")
    st.write(response)

    st.subheader("The chat history is")
    for role, text in st.session_state['chat_history']:
        st.write(f"{role}: {text}")
