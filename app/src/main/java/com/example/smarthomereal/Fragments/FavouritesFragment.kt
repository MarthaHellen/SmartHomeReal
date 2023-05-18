package com.example.smarthomereal.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smarthomereal.Activities.SelectRoutine
import com.example.smarthomereal.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FavouritesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view=  inflater.inflate(R.layout.fragment_favourites, container, false)
        val favouritesFAB = view?.findViewById<FloatingActionButton>(R.id.favouritesFAB)
        favouritesFAB?.setOnClickListener{selectRoutine()}
        return view
    }


    private fun selectRoutine(){
        val  intent = Intent(activity, SelectRoutine::class.java )
        startActivity(intent)
    }
}

