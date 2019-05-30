package com.example.kanthivelg.espresso

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        imageActivityButton.setOnClickListener {
            val intent = Intent(this, EspressoImageActivity::class.java)
            startActivity(intent)
        }

        recyclerViewActivityButton.setOnClickListener {
            val intent = Intent(this, EspressoRecyclerViewActivity::class.java)
            startActivity(intent)
        }

        radioActivityButton.setOnClickListener {
            val intent = Intent(this, EspressoRadioPickerActivity::class.java)
            startActivity(intent)
        }
    }
}
