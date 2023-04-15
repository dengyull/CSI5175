package com.example.csi5175

import android.app.Application

class MyApplication: Application() {
    companion object {
        lateinit var instance: Application
    }

    init {
        instance = this
    }
}