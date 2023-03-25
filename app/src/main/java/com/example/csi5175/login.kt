package com.example.csi5175

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {
    private  lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button = findViewById(R.id.login_button)

        //The button is the "confirm" button. The login authorize process should be run after click.
        button.setOnClickListener{
            loginAuthorize()
        }
        
    }    
    private fun loginAuthorize() {
        TODO("Not yet implemented")
    }
}