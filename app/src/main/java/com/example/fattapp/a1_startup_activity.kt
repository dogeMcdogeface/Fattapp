package com.example.fattapp

import DEBUG_BUILD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import auth
import com.google.firebase.auth.ktx.auth

class a1_startup_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a1_startup_activity)

        auth.signOut()

        //check permissions
        //check firebase
        //check account

        if(DEBUG_BUILD){
            Toast.makeText(applicationContext,"You are running a debug build of this app", Toast.LENGTH_LONG).show()
        }
        println("sadadsasds")
        println(auth.currentUser?.uid.toString())


        var intent:Intent = Intent(this, a3_main_activity::class.java)
        if(auth.currentUser == null) {intent = Intent(this, a2_login_activity::class.java)}
        startActivity(intent)
    }
}