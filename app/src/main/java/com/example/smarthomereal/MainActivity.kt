package com.example.smarthomereal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.smarthomereal.Fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val favouritesFragment = FavouritesFragment()
        val thingsFragment = ThingsFragment()
        val routinesFragment = RoutinesFragment()
        val ideasFragment = IdeasFragment()
        val settingsFragment = SettingsFragment()

        setThatFragment(favouritesFragment)

        val chosenFrag = intent.getStringExtra("SELECTED_FRAGMENT")
        if(chosenFrag == "routines"){
            setThatFragment(routinesFragment)
            bottomNavigationView.selectedItemId = R.id.routinesnbtn
        }else{
            setThatFragment(favouritesFragment)
        }



        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.favouritesnbtn -> setThatFragment(favouritesFragment)
                R.id.routinesnbtn -> setThatFragment(routinesFragment)
                R.id.thingsnbtn -> setThatFragment(thingsFragment)
                R.id.ideasnbtn -> setThatFragment(ideasFragment)
                R.id.settingsnbtn -> setThatFragment(settingsFragment)
            }
            true
        }
    }

    private fun setThatFragment(fragment:Fragment) {
        val transaction = supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,fragment)
            commit()
        }
    }

}

