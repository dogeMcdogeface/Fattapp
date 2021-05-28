package com.example.fattapp.main_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fattapp.R

class GalleryFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.a32_gallery_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        textView.text = "gallery"
        return root
    }
}