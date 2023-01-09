package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val result = intent.getIntExtra("xxx", -1)
        println("My result => $result")

        readData()
    }

    private fun readData() {
        val prefs = getSharedPreferences("prefs",
            Context.MODE_PRIVATE)
        val data = prefs.getString("message", "not found")
        println("Result from shared preferences => $data")
    }
}