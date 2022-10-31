package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView? = null
    private var AgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatepicker : Button = findViewById(R.id.btnDatepicker)
        selectedDate = findViewById(R.id.SelectedDate)
        AgeInMinutes = findViewById(R.id.AgeInMinutes)

        btnDatepicker.setOnClickListener{
            clickDatepicker()
        }
    }

    private fun clickDatepicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { view, year, month, dayOfMonth ->
            val tvSelectedDate = "$dayOfMonth/${month+1}/$year"

            selectedDate?.text = tvSelectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(tvSelectedDate)

            //Null safety Added
            // it will execute when date is entered.
            theDate?.let {
                val selectedDateInMinutes = theDate.time /60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time/60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    AgeInMinutes?.text = differenceInMinutes.toString()
                }
            }
            //Check this out tommorrow : https://developer.android.com/reference/kotlin/java/text/SimpleDateFormat
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()- 86400000
        dpd.show()
    }
}