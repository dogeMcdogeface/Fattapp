package com.example.fattapp.main_fragments

import User
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fattapp.R
import currentUser
import kotlinx.android.synthetic.main.a34_user_info_fragment.*
import kotlinx.android.synthetic.main.a34_user_info_fragment.view.*

class a34_userInfo : Fragment() {
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.a34_user_info_fragment, container, false)
        root.save_button.setOnClickListener { view -> save_button_pressed(view)}


        return root
    }

    fun save_button_pressed(v: View){

        var name    :String = root.name_field.text.toString()
        var height  :String = root.height_field.text.toString()
        var age     :String = root.age_field.text.toString()
        var sex     :String = "M"
        if(root.sex_field.checkedRadioButtonId == R.id.radioButton_female){
            sex = "F"
        }

        var newInfo = User(name, height, age, sex)
        currentUser.info = newInfo
        Toast.makeText(getActivity()?.getApplicationContext(),"Info saved",Toast.LENGTH_SHORT).show()


    }
}
