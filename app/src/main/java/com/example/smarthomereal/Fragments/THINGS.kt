package com.example.smarthomereal.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.smarthomereal.Activities.CreateRoutine
import com.example.smarthomereal.R

class THINGS : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_t_h_i_n_g_s, container, false)
        val firstLL = view.findViewById<LinearLayout>(R.id.notification_LinearLayout)
        val secondLL = view.findViewById<LinearLayout>(R.id.display_text)
        val TV = view.findViewById<TextView>(R.id.invisible_text)

        firstLL.setOnClickListener{
            firstLL.visibility = View.GONE
            secondLL.visibility = View.VISIBLE
            TV.requestFocus()

            secondLL.setOnClickListener {
                val intent = Intent(requireContext(), CreateRoutine::class.java)
                intent.putExtra("createNotification", true)
                startActivity(intent)
            }

        }
        return view
    }

}