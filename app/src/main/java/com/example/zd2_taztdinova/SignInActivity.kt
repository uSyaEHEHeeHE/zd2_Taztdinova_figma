package com.example.zd2_taztdinova

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val inputEmail: AppCompatEditText = findViewById(R.id.edit_email)
        val inputPassword: AppCompatEditText = findViewById(R.id.edit_password)
        val buttonLogin: AppCompatButton = findViewById(R.id.btn_sign_in)

        val preferences = getSharedPreferences("app_cinema_prefs", Context.MODE_PRIVATE)

        val cachedEmail = preferences.getString("saved_email", "")
        val cachedPassword = preferences.getString("saved_password", "")

        if (!cachedEmail.isNullOrEmpty() && !cachedPassword.isNullOrEmpty()) {
            inputEmail.setText(cachedEmail)
            inputPassword.setText(cachedPassword)
        }

        buttonLogin.setOnClickListener {
            val emailText = inputEmail.text.toString().trim()
            val passText = inputPassword.text.toString().trim()

            if (emailText.isEmpty() || passText.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!emailText.contains("@") || !emailText.contains(".")) {
                Toast.makeText(this, "Неверный формат адреса почты", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passText.length != 8) {
                Toast.makeText(this, "Пароль должен содержать 8 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!cachedEmail.isNullOrEmpty() && cachedEmail == emailText) {
                if (cachedPassword != passText) {
                    Toast.makeText(this, "Введен неверный пароль", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            preferences.edit()
                .putString("saved_email", emailText)
                .putString("saved_password", passText)
                .apply()

            val openMain = Intent(this, MainActivity::class.java)
            startActivity(openMain)
        }
    }
}
