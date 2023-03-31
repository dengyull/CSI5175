package com.example.csi5175

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.csi5175.databinding.ActivityLoginBinding
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    private  lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.loginEmail      //input Email
        //binding.loginPassword       //input Password

        //The button is the "confirm" button. The login authorize process should be run after click.


    }

    private fun loginAuthorize() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
        finish()
    }
}