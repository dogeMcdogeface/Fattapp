package com.example.fattapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class login_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }



    fun login_button_pressed(v: View){
        Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()
    }
}