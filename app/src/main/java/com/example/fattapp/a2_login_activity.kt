package com.example.fattapp

import DEBUG_BUILD
import FLAG_ISNEWUSER
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import auth
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import firebaseHelper
import kotlinx.android.synthetic.main.a2_login_activity.*


class a2_login_activity : AppCompatActivity() {
    var wantsNewUser :Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a2_login_activity)

        if(auth.currentUser != null) {
            auth.signOut()
        }
        updateLayout()
        if(DEBUG_BUILD){
            bypass_button.visibility= View.VISIBLE
        }
    }


    fun updateLayout(){
        if(wantsNewUser){
            login_button.text   = "REGISTER"
            switch_button.text  = "Existing user?"
        }else {
            login_button.text   = "LOG IN"
            switch_button.text  = "New User?"
        }
    }


    fun bypass_button_pressed(v: View){
        autenticate("ass@ass.ass", "password")
    }
    fun switch_button_pressed(v: View){
        wantsNewUser = !wantsNewUser
        updateLayout()
    }
    fun login_button_pressed(v: View){
        var mail = field_mail.text.toString()
        var pswd = field_pswd.text.toString()
        autenticate(mail,pswd)
    }




    fun autenticate(mail:String, pswd:String){
        login_button.isEnabled  = false
        switch_button.isEnabled = false
        bypass_button.isEnabled = false

        if(wantsNewUser){
            auth.createUserWithEmailAndPassword(mail, pswd)
                .addOnCompleteListener{ task -> onLoginSuccess(task)}
        }else{
            auth.signInWithEmailAndPassword(mail, pswd)
                .addOnCompleteListener{ task -> onLoginSuccess(task)}
        }

    }

    fun onLoginSuccess(task: Task<AuthResult>) {
        if(task.isSuccessful) {
            firebaseHelper.userLoggedIn()
            var intent: Intent = Intent(this, a3_main_activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra(FLAG_ISNEWUSER , wantsNewUser)
            startActivity(intent)
        }else{
            login_button.isEnabled  = true
            switch_button.isEnabled = true
            bypass_button.isEnabled = true
            Toast.makeText(applicationContext,"Login Fallito",Toast.LENGTH_SHORT).show()
        }
    }
}