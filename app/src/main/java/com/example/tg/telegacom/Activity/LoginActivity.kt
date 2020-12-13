package com.example.tg.telegacom.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tg.telegacom.Fragment.CustomDialogFragment
import com.example.tg.telegacom.ViewModel.LoginViewModel
import com.example.tg.telegacom.R
import com.example.tg.telegacom.ViewModelFactory.LoginViewModelFactory
import com.example.tg.telegacom.database.TelegaDataBase
import com.example.tg.telegacom.database.User
import com.example.tg.telegacom.database.UserDao
import com.example.tg.telegacom.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var userDao : UserDao
    var current_user : User? = null
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userDao = TelegaDataBase.getInstance(application).UserDao
        val arguments = getIntent().extras

        val application = this.application
        val dataSource = TelegaDataBase.getInstance(application).UserDao
        val viewModelFactory = LoginViewModelFactory(dataSource, application)

        val loginViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(LoginViewModel::class.java)

        loginViewModel.current_user.observe(this, Observer<User> {
            current_user = loginViewModel.current_user.value
        })

        loginViewModel.was_checked.observe(this, Observer<Boolean> {
            if (loginViewModel.was_checked.value == true) {
                if (current_user != null) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("current_user_id", current_user!!.Id.toString());
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

                    //val user = userDao.authorization("email", "pass")
                    loginViewModel.authorize(email, password)
                        if (current_user != null) {
                            Log.i("auth", current_user!!.Email.toString())
                        }
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