package com.raywenderlich.android.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_two, container, false)

        view.findViewById<View>(R.id.recommended).setOnClickListener {
            FragmentOne.setRecommended()
        }

        view.findViewById<View>(R.id.strict).setOnClickListener {
            FragmentOne.setStrict()
        }
        return view
    }
}



