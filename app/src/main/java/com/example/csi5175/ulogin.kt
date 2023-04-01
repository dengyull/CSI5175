package com.example.csi5175

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.csi5175.backend.persistence.AppDatabase

class ulogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulogin)

        var loginButton = findViewById<Button>(R.id.login_button)
        var registerButton = findViewById<TextView>(R.id.register_button)

        loginButton.setOnClickListener{
            loginAuthorize()

        }
        registerButton.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivityForResult(intent, REGISTER_REQUEST_CODE)
        }
    }

    private fun loginAuthorize() {
        var db = AppDatabase.getAppDatabase(applicationContext)
        var email = findViewById<TextView>(R.id.login_email)
        var password = findViewById<TextView>(R.id.login_password)
        var uid = db?.userDao()?.getUserByEmailAndPassword(email.text.toString(),password.text.toString())?.uid
        if(uid != null){
            var sharedPref : SharedPreferences = getPreferences(Context.MODE_PRIVATE);
            sharedPref.edit().putInt("uid", uid).apply()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(applicationContext, "login fail", Toast.LENGTH_LONG).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REGISTER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("result")

            if (result == "registration_successful") {
                loginAuthorize()
            }
        }
    }

    companion object {
        const val REGISTER_REQUEST_CODE = 123
    }
}