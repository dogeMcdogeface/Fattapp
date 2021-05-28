package com.example.fattapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import database


class login_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }



    fun login_button_pressed(v: View){
        val myRef = database.getReference("message")

        myRef.setValue("Hello, Worlwefdfdd!")



        auth.createUserWithEmailAndPassword("ass@ass.ass", "password").addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {onLoginSuccess()}}

        Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()
    }


    fun onLoginSuccess(){
        var intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}