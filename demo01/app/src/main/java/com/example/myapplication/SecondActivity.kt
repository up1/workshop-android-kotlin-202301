package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        println(intent.getStringExtra("my_key"))

        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val data = sharedPreferences.getString("my_key", "default value")
        println("Data from preferences=$data")
    }
}