package com.example.smarthomereal.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.smarthomereal.R

class SelectEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_event)

        val selectEvent = findViewById<TextView>(R.id.selectEventTime)
        selectEvent.setOnClickListener {
            setTime()
        }
    }

    private fun setTime() {
        val intent = Intent(this, CreateRoutine::class.java)
        intent.putExtra("timeSet", true)
        startActivity(intent)
    }
}