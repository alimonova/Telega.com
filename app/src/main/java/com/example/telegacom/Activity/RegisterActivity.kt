package com.example.telegacom.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.telegacom.Fragment.CustomDialogFragment
import com.example.telegacom.Activity.MainActivity
import com.example.telegacom.R


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val login_button = findViewById<TextView>(R.id.button_login_activity)
        val register_button = findViewById<TextView>(R.id.button_register_activity)

        val emailEditText = findViewById(R.id.email) as EditText
        val passwordEditText = findViewById(R.id.password) as EditText

        register_button.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val email = emailEditText.text.toString()
                    val password = passwordEditText.text.toString()

                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent)
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