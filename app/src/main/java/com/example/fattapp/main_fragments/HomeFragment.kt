package com.example.fattapp.main_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fattapp.R
import currentUser
import weightReport

class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.`a31_home_fragment`, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        textView.text = "HOME!!"


        var weight = weightReport(60.0)

        //currentUser.sendReport(weight)

        return root
    }
}