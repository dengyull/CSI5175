package com.example.csi5175

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.csi5175.databinding.ActivityMain2Binding
import com.example.csi5175.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerNameInput   //name input
        binding.registerEmailInput  //email input
        binding.registrerPasswordInput  //password input
        binding.registerRepeatInput //repeat input

        binding.registerConfirm.setOnClickListener {    //confirm button

        }

        binding.registerBack.setOnClickListener {   //back button

        }


    }
}