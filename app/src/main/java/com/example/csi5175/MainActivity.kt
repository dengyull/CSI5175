package com.example.csi5175

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.csi5175.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private  lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        button = findViewById(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }
}