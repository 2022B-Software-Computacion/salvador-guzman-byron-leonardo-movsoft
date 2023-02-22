package com.example.messengerapprecyclerview.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.messengerapprecyclerview.Messenger
import com.example.messengerapprecyclerview.R

class MessengerHolder(view: View):RecyclerView.ViewHolder(view) {
    val nombreAmigo = view.findViewById<TextView>(R.id.msnNombreAmigo)
    val ultimoMensaje = view.findViewById<TextView>(R.id.msnUltimoMensaje)
    val fecha = view.findViewById<TextView>(R.id.msnFecha)
    val fotoAmigo = view.findViewById<ImageView>(R.id.ivFotoAmigo)
    val confLectura = view.findViewById<ImageView>(R.id.ivConfLectura)

    fun render(messengerModel: Messenger){
        nombreAmigo.text = messengerModel.nombreAmigo
        ultimoMensaje.text = messengerModel.ultimoMensaje
        fecha.text = messengerModel.fecha
        Glide.with(fotoAmigo.context).load(messengerModel.foto).into(fotoAmigo)
        Glide.with(confLectura.context).load(messengerModel.confLectura).into(confLectura)
    }
}