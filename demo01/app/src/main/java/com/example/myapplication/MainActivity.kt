package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var myData: EditText
    private lateinit var myButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = findViewById(R.id.my_data)
        myButton = findViewById(R.id.my_button)
        myButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                println("onClick")

                doClick()
            }
        })
    }

    private fun doClick() {
        val data = myData.text.toString()

        setDataInSharedPreferences(data)


        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("my_key", data)
        startActivity(intent)
    }

    private fun setDataInSharedPreferences(data: String) {
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("my_key", data)
        editor.apply()
    }
}

class MyViewModel : ViewModel() {
    var data: String? = null
}


