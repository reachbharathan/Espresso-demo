package com.example.kanthivelg.espresso

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.recyclerView

class EspressoRecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = Adapter(getDataSet())
//        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getDataSet(): ArrayList<Event> {
        val dataSet = ArrayList<Event>()
        for (i in 0 until 30) {
            val album = Event("Title $i", i)
            dataSet.add(album)
        }
        return dataSet
    }
}
