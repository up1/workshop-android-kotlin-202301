package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var myButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myButton = findViewById(R.id.button)
        myButton.setOnClickListener {
            println("Clicked ...")
            doSth()
        }
    }

    private fun doSth() {
        createDataInSharedPreferences()


        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("xxx", 123)
        startActivity(intent)

        // Delete current activity
        finish()
    }

    private fun createDataInSharedPreferences() {
        val prefs = getSharedPreferences("prefs",
            Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("message", "Hello sth")
        editor.apply()
    }


}

