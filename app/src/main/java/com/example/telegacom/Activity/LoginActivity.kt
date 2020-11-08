package com.example.telegacom.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.telegacom.Fragment.CustomDialogFragment
import com.example.telegacom.R


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val arguments = getIntent().extras

        if (arguments is Bundle) {
            val dialog = CustomDialogFragment()
            val args = Bundle()
            if (arguments != null) {
                args.putString(
                    "message",
                    "Вы зарегистрировались со следующими данными:\nEmail: " + arguments.get("email")
                        .toString() + "\nПароль: " + arguments.get("password").toString()
                )
            }
            args.putString("title", "Регистрация")
            dialog.arguments = args
            dialog.show(supportFragmentManager, "custom")
        }

        val login_button = findViewById<TextView>(R.id.button_login_activity)
        val register_button = findViewById<TextView>(R.id.button_register_activity)

        val emailEditText = findViewById(R.id.email) as EditText
        val passwordEditText = findViewById(R.id.password) as EditText

        val email_line_1 : View = findViewById(R.id.email_line_1) as View
        val email_line_2 : View = findViewById(R.id.email_line_2) as View
        val password_line_1 : View = findViewById(R.id.password_line_1) as View
        val password_line_2 : View = findViewById(R.id.password_line_2) as View


        emailEditText.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                email_line_1.setBackgroundColor(Color.parseColor("#434343"));
                email_line_2.setBackgroundColor(Color.parseColor("#434343"));
            }
            else {
                email_line_1.setBackgroundColor(Color.parseColor("#4CAF50"));
                email_line_2.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        })

        passwordEditText.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                password_line_1.setBackgroundColor(Color.parseColor("#434343"));
                password_line_2.setBackgroundColor(Color.parseColor("#434343"));
            }
            else {
                password_line_1.setBackgroundColor(Color.parseColor("#4CAF50"));
                password_line_2.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        })



        login_button.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val email = emailEditText.text.toString()
                    val password = passwordEditText.text.toString()

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent)
                }
            })

        register_button.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                }
            })

        val checkRemember : CheckBox = findViewById(R.id.check_remember) as CheckBox

        checkRemember.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val dialog = CustomDialogFragment()
                val args = Bundle()
                args.putString("message", "Мы Вас запомнили!")
                args.putString("title", "")
                dialog.arguments = args
                dialog.show(supportFragmentManager, "custom")
            }
        }
    }
}