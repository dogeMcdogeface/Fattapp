package com.example.fattapp.main_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fattapp.R
import currentUser
import firebaseHelper
import kotlinx.android.synthetic.main.a31_home_fragment.view.*

class HomeFragment : Fragment() {
    lateinit var root:View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.`a31_home_fragment`, container, false)
        root.button.setOnClickListener { view -> button_pressed(view)}

        firebaseHelper.addUpdateListener {updateLayout()}
        return root
    }

    fun updateLayout(){
        var tmpW = currentUser.data?.weight?.today
        if(tmpW != null && tmpW > 0) {
            root.weight_field.setText("")
            root.weight_field.setHint(tmpW.toString()+" (kg)")
        }
    }


    fun button_pressed(v:View) {
        var weight = root.weight_field.text.toString().toDoubleOrNull()
        if (weight != null) {
            currentUser.sendReport(weight)
        }
    }
}