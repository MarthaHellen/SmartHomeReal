package com.example.smarthomereal.Activities

import Database.DatabaseHandler
import Models.RoutineModel
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import com.example.smarthomereal.Fragments.RoutinesFragment
import com.example.smarthomereal.MainActivity
import com.example.smarthomereal.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class CreateRoutine : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences //  key-value storage mechanism  allows you to store and retrieve persistent data
    private lateinit var routineNameText: EditText
    private val SHARED_PREFS_KEY = "MySharedPreferences" //Share or retrieve persistent data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)

        //a functionality for the clear button
        val clearBtn = findViewById<AppCompatImageView>(R.id.clearBtn);
        clearBtn.setOnClickListener{
            finish()
        }

        //identifying buttons
        val addActionButton = findViewById<FloatingActionButton>(R.id.AddActionButton)
        val addEventButton = findViewById<FloatingActionButton>(R.id.AddEvent)
        routineNameText = findViewById(R.id.Routine)

        addEventButton.setOnClickListener{addEvent()}
        addActionButton.setOnClickListener { addAction() }

        sharedPreferences = getSharedPreferences(
            SHARED_PREFS_KEY,
            Context.MODE_PRIVATE
        )// we can retrieve or access data that has been entered

        fun onDestroy() {
            super.onDestroy()
            sharedPreferences.edit().clear().apply()
        }


        //loading the prefs
        getNotificationPref()
        getTimePref()
        getRoutinePref()

        val notificationCreated = intent.getBooleanExtra("createNotification", false)
        if (notificationCreated) {
            displayAlertBox()
            intent.putExtra("notificationCreated", false) // set value to false so that it does not repeat
        }
        val NewTimeCreated = intent.getBooleanExtra("timeSet", false)
        if (NewTimeCreated) {
            displayTimePicker()
            intent.putExtra("timeSet", false) // set timeSet value to false
        }


    }


    private fun getRoutinePref() {
        val routine = sharedPreferences.getString("routineName", null)
        routineNameText.setText(routine)
    }

    //functions for adding time
    private fun displayTimePicker() {
        val calendar = Calendar.getInstance()
        val Hrs = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val amPm = calendar.get(Calendar.AM_PM)

        val timePicker = TimePickerDialog(this,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                val hourText = if (selectedHour == 0 || selectedHour == 12) "12" else (selectedHour % 12).toString()
                val minuteText = if (selectedMinute < 10) "0$selectedMinute" else "$selectedMinute"
                val amPmText = if (amPm == Calendar.AM) "AM" else "PM"
                var time = "$hourText:$minuteText $amPmText"

                swapLayout2()

                // save the value to shared preferences
                val editor = sharedPreferences.edit()
                editor.putString("TimePreference", time)
                editor.apply()


            }, Hrs, min, false
        )

        timePicker.show()
    }
    private fun getTimePref() {
        var time = sharedPreferences.getString("TimePreference", null)
        if (time != null){
            swapLayout2()
        }

    }
    private fun swapLayout2() {
        var time =""
        val addEventLayout = layoutInflater.inflate(R.layout.newtime, null)
        val EventsContainer = findViewById<ViewGroup>(R.id.AddEventContainer)
        val AddEvent = findViewById<TextView>(R.id.addEventChild)
        val index = EventsContainer.indexOfChild(AddEvent)
        EventsContainer.removeView(AddEvent)
        EventsContainer.addView(addEventLayout, index)
        addEventLayout.id = R.id.addEventChild
        findViewById<TextView>(R.id.newTime).text = "The time is $time"
    }

    //functions for the add action
    private fun displayAlertBox() {
        val notify = AlertDialog.Builder(this)
        notify.setTitle("Enter a value")

        val input = EditText(this) //allows you to enter a value
        notify.setView(input)

        notify.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            var noticeText = input.text.toString() //gets the text written and stores it in this variable
            swapLayout()
            // save the value to shared preferences
            val editor = sharedPreferences.edit()
            editor.putString("NotificationPreference", noticeText)
            editor.apply()

            loadingDialogBox(input.text.toString())

        }
        notify.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        notify.show()
    }
    private fun loadingDialogBox(input: String) {
        val loading = AlertDialog.Builder(this)
        loading.setTitle("Creating new routine")

        val PB = ProgressBar(this)
        loading.setView(PB)

        val dialog = loading.create()
        dialog.show()
        //loading.create().show()
        addRoutineRecord()
    }
    private fun addRoutineRecord() {
        val routine = sharedPreferences.getString("routineName", null)
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if(routine?.isNotEmpty() == true){
            val status = databaseHandler.addRoutine(RoutineModel(0, routine,"Never"))
            if (status > -1){
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                sharedPreferences.edit().clear().apply()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("SELECTED_FRAGMENT", "routines")
                startActivity(intent)
            }
        }else{
            Toast.makeText(applicationContext, "Error creating routine", Toast.LENGTH_LONG).show()
        }
    }
    private fun getNotificationPref() {
        var noticeText = sharedPreferences.getString("NotificationPreference", null)
        if (noticeText != null){

            swapLayout()

        }
    }
    private fun swapLayout() {
        var noticeText = ""
        val addActionLayout = layoutInflater.inflate(R.layout.notification,null)
        addActionLayout.findViewById<TextView>(R.id.AddNotification).text ="Send Notification: $noticeText"
        val Actionscontainer = findViewById<ViewGroup>(R.id.addActionContainer)
        val Addaction : TextView = findViewById(R.id.addActions)
        val index = Actionscontainer.indexOfChild(Addaction)
        Actionscontainer.removeView(Addaction)
        Actionscontainer.addView(addActionLayout, index)
        addActionLayout.id = R.id.addActions
    }
    private fun addAction() {
        val intent = Intent(this, SelectThing::class.java)
        startActivity(intent)
    }
    private fun addEvent() {
        sharedPreferences.edit().putString("routineName", routineNameText.text.toString())
            .apply()
        val intent = Intent(this, SelectEvent::class.java)
        startActivity(intent)
    }
}