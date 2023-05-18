package com.example.smarthomereal.Activities

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.smarthomereal.Fragments.ROUTINES
import com.example.smarthomereal.Fragments.SCENES
import com.example.smarthomereal.Fragments.THINGS

//The connector is used to provide the ViewPager with the fragments to display for each tab.
//context -> the fragment using the connector
//FM -> manages the fragments
//totaltabs -> store total number of tabs displayed
class Connector (var context: Context, FM: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(FM) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                THINGS()
            }
            1->{
                SCENES()
            }
            2->{
                ROUTINES()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int{
        return totalTabs
    }
}