package com.example.smarthomereal.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import com.example.smarthomereal.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SelectRoutine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_routine)

        val selectRoutinebtn = findViewById<FloatingActionButton>(R.id.selectRoutineBtn)
        selectRoutinebtn.setOnClickListener{createRoutine()}

        val backbtn = findViewById<AppCompatImageView>(R.id.backbtn)
        backbtn.setOnClickListener{
            finish()
        }
    }

    private fun createRoutine() {
        val intent= Intent(this, CreateRoutine::class.java)
        startActivity(intent)
    }


}