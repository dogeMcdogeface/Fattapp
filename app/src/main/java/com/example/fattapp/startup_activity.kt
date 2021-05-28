package com.example.fattapp

import DEBUG_BUILD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import auth

class startup_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startup_activity)



        //check permissions
        //check firebase
        //check account

        if(DEBUG_BUILD){
            Toast.makeText(applicationContext,"You are running a debug build of this app", Toast.LENGTH_LONG).show()
        }

        var intent:Intent = Intent(this, MainActivity::class.java)
        if(auth.currentUser == null) {intent = Intent(this, login_activity::class.java)}
        startActivity(intent)
    }
}