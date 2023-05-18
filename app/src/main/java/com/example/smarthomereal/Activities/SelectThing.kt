package com.example.smarthomereal.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.smarthomereal.R
import com.google.android.material.tabs.TabLayout

class SelectThing : AppCompatActivity() {
    private lateinit var TL: TabLayout
    private lateinit var VP: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_thing)

        TL = findViewById(R.id.tabLayout)
        VP = findViewById(R.id.viewPager)

        //a new tab to the TabLayout view programmatically and sets its text to "THINGS".
        TL.addTab(TL.newTab().setText("THINGS"))
        TL.addTab(TL.newTab().setText("SCENES"))
        TL.addTab(TL.newTab().setText("ROUTINES"))
        TL.tabGravity= TabLayout.GRAVITY_CENTER

        //This code sets up a ViewPager and a TabLayout to display multiple fragments in a single activity or fragment.

        val connector = Connector(this, supportFragmentManager, TL.tabCount)
        VP.adapter = connector


        VP.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(TL)) //that will update the selected tab in the TabLayout when the user swipes between pages.
        TL.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener { //that will update the current page in the ViewPager when the user selects a tab.
            override fun onTabSelected(tab: TabLayout.Tab?) { //It sets the current item of the ViewPager to the position of the selected tab.
                VP.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


    }

}