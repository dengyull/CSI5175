package com.example.csi5175

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
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
            val intent = Intent(this, ulogin::class.java)
            startActivity(intent)
            finish()
        }

        var sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        val myuid = sharedPref.getInt("uid", 0)
        if (myuid!=0){
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

    }
}