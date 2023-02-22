package com.example.messengerapprecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.messengerapprecyclerview.Messenger
import com.example.messengerapprecyclerview.R

class MessengerAdapter(private val mensajesList:List<Messenger>): RecyclerView.Adapter<MessengerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessengerHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MessengerHolder(layoutInflater.inflate(R.layout.item_mesenger, parent, false))
    }

    override fun getItemCount(): Int {
        return mensajesList.size
    }

    override fun onBindViewHolder(holder: MessengerHolder, position: Int) {
        val item = mensajesList[position]
        holder.render(item)
    }
}