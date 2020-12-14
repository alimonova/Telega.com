package com.example.telegacom.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.telegacom.R
import com.example.telegacom.ViewModel.RegisterViewModel
import com.example.telegacom.ViewModelFactory.RegisterViewModelFactory
import com.example.telegacom.database.TelegaDataBase
import com.example.telegacom.database.User


class RegisterActivity : AppCompatActivity() {
    var current_user : User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.activity_register)

        val login_button = findViewById<TextView>(R.id.button_login_activity)
        val register_button = findViewById<TextView>(R.id.button_register_activity)

        val emailEditText = findViewById(R.id.email) as EditText
        val passwordEditText = findViewById(R.id.password) as EditText

        val application = this.application
        val dataSource = TelegaDataBase.getInstance(application).UserDao
        val viewModelFactory = RegisterViewModelFactory(dataSource, application)

        val registerViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(RegisterViewModel::class.java)

        registerViewModel.current_user.observe(this, Observer<User> {
            current_user = registerViewModel.current_user.value
        })

        registerViewModel.was_checked.observe(this, Observer<Boolean> {
            if (registerViewModel.was_checked.value == true) {
                if (current_user != null) {
                    Toast.makeText(
                        this,
                        "Вы успешно прошли регистрацию.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(
                        this,
                        "Что-то пошло не так.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })


        register_button.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val email = emailEditText.text.toString()
                    val password = passwordEditText.text.toString()

                    registerViewModel.register(email, password)
                    if (current_user != null) {
                        Log.i("auth", current_user!!.Email.toString())
                    }
                }
            })

        login_button.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                }
            })

        val email_line_1 : View = findViewById(R.id.email_line_1) as View
        val email_line_2 : View = findViewById(R.id.email_line_2) as View
        val password_line_1 : View = findViewById(R.id.password_line_1) as View
        val password_line_2 : View = findViewById(R.id.password_line_2) as View


        emailEditText.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                email_line_1.setBackgroundColor(Color.parseColor("#434343"));
                email_line_2.setBackgroundColor(Color.parseColor("#434343"));
            } else {
                email_line_1.setBackgroundColor(Color.parseColor("#4CAF50"));
                email_line_2.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        })

        passwordEditText.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                password_line_1.setBackgroundColor(Color.parseColor("#434343"));
                password_line_2.setBackgroundColor(Color.parseColor("#434343"));
            } else {
                password_line_1.setBackgroundColor(Color.parseColor("#4CAF50"));
                password_line_2.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        })
    }
}