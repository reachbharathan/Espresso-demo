package com.example.kanthivelg.espresso

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import kotlinx.android.synthetic.main.espresso_basic.*

class EspressoBasicActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.espresso_basic)
        coffeeImage.setOnClickListener {
            preference.text = getString(R.string.like_coffee)
            teaImage.visibility = GONE
            coffeeImage.setOnClickListener(null)
        }
        teaImage.setOnClickListener {
            preference.text = getString(R.string.like_green_tea)
            coffeeImage.visibility = GONE
            teaImage.setOnClickListener(null)
        }
    }

}