package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatepicker : Button = findViewById(R.id.btnDatepicker)
        selectedDate = findViewById(R.id.SelectedDate)

        btnDatepicker.setOnClickListener(){

            clickDatepicker()
        }
    }

    fun clickDatepicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { view, year, month, dayOfMonth ->
            var tvSelectedDate = "$dayOfMonth/${month+1}/$year"

            selectedDate?.text = tvSelectedDate

            //Check this out tommorrow : https://developer.android.com/reference/kotlin/java/text/SimpleDateFormat
            },
            year,
            month,
            day
        ).show()
    }
}