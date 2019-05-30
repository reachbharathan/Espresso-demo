package com.example.kanthivelg.espresso

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import kotlinx.android.synthetic.main.radio_spinner_activity.*

class EspressoRadioPickerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.radio_spinner_activity)
        registerButton.setOnClickListener {
            val name =  nameEditText.text.toString()
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val gender = findViewById<RadioButton>(selectedRadioButtonId).text
            val dob = "${dobDatePicker.dayOfMonth}:${dobDatePicker.month + 1}:${dobDatePicker.year}"
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Registered")
            builder.setMessage("You are a $gender with name $name and DOB $dob")
            builder.setNeutralButton("OK") { _, _ ->}
            builder.create().show()
        }
    }

}