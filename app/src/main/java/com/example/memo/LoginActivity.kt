package com.example.memo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val cbRemember = findViewById<CheckBox>(R.id.cbRemember)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val sharedPref = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

        // 恢复已记住的用户名
        val savedUsernameRemembered = sharedPref.getString("remembered_username", "")
        val isRemembered = sharedPref.getBoolean("remember", false)
        if (isRemembered) {
            etUsername.setText(savedUsernameRemembered)
            cbRemember.isChecked = true
        }

        btnLogin.setOnClickListener {
            val inputUsername = etUsername.text.toString()
            val inputPassword = etPassword.text.toString()

            if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val savedUsername = sharedPref.getString("saved_username", null)
            val savedPassword = sharedPref.getString("saved_password", null)

            if (savedUsername == null || savedPassword == null) {
                // 未注册过，视为注册
                with(sharedPref.edit()) {
                    putString("saved_username", inputUsername)
                    putString("saved_password", inputPassword)
                    apply()
                }
                Toast.makeText(this, "注册成功，已登录", Toast.LENGTH_SHORT).show()
                goToMain()
            } else if (inputUsername == savedUsername && inputPassword == savedPassword) {
                // 登录成功
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()

                if (cbRemember.isChecked) {
                    with(sharedPref.edit()) {
                        putString("remembered_username", inputUsername)
                        putBoolean("remember", true)
                        apply()
                    }
                } else {
                    with(sharedPref.edit()) {
                        remove("remembered_username")
                        putBoolean("remember", false)
                        apply()
                    }
                }

                goToMain()
            } else {
                // 登录失败
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToMain() {
        val intent = Intent(this, MemoListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
