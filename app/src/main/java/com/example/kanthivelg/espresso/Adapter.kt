package com.example.kanthivelg.espresso

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.place_card_view.view.*

class Adapter(private val data: ArrayList<Event>): RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.place_card_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {
        viewHolder.bind(data[p1])
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(event: Event) {
            view.title.text = event.title
            view.count.text = event.songsCount.toString()
        }

    }
}

