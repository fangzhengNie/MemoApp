package com.example.memo
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class LoginActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername=findViewById<EditText>(R.id.etUsername)
        val etPassword=findViewById<EditText>(R.id.etPassword)
        val cbRemember=findViewById<CheckBox>(R.id.cbRemember)
        val btnLogin=findViewById<Button>(R.id.btnLogin)

        val sharedPref=getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

        val savedUsername=sharedPref.getString("username","")
        val isRemembered=sharedPref.getBoolean("remember",false)
        if(isRemembered){
            etUsername.setText(savedUsername)
            cbRemember.isChecked=true
        }
        btnLogin.setOnClickListener{
            val username=etUsername.text.toString()
            val password=etPassword.text.toString()
            if(username.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(cbRemember.isChecked){
                with(sharedPref.edit()){
                    putString("username",username)
                    putBoolean("remember",true)
                    apply()
                }
            }else{
                with(sharedPref.edit()){
                    remove("username")
                    putBoolean("remember",false)
                    apply()
                }
            }
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show()
            val intent=Intent(this,MemoListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}