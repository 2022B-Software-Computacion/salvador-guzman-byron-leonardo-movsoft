package com.example.examen2_leonardo_salvador

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AplicacionAdapter : AppCompatActivity() {

    val firestoreDB = FireStoreDB()
    lateinit var idSistemaOperativo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplicacion)

        val nombreAplicacionInput = findViewById<EditText>(R.id.id_nombre_aplicacion_input)
        val descripcionAplicacionInput = findViewById<EditText>(R.id.id_descripcion_aplicacion_input)
        val precioAplicacionInput = findViewById<EditText>(R.id.id_precio_aplicacion_input)
        val crearButton = findViewById<Button>(R.id.btn_crear_aplicacion)

        idSistemaOperativo = intent.getStringExtra("idSistemaOperativo")!!
        val aplicacion = intent.getParcelableExtra<Aplicacion>("aplicacion")

        if (aplicacion != null){
            nombreAplicacionInput.setText(aplicacion.nombreAplicacion)
            descripcionAplicacionInput.setText(aplicacion.descripcionAplicacion)
            precioAplicacionInput.setText(aplicacion.precioAplicacion.toString())
            crearButton.setOnClickListener {
                val nombreAplicacion = nombreAplicacionInput.text.toString()
                val descripcionAplicacion = descripcionAplicacionInput.text.toString()
                val precioAplicacion = precioAplicacionInput.text.toString().toDouble()
                val newAplicacion = Aplicacion(aplicacion.codigo ,nombreAplicacion, descripcionAplicacion, precioAplicacion)
                firestoreDB.actualizarAplicacion(idSistemaOperativo,newAplicacion,{
                    setResult(Activity.RESULT_OK, Intent().putExtra("aplicacionActualizada", newAplicacion))
                    finish()
                }, { exception -> print("Error actualizando la aplicacion")
                })
            }
        }else{
            crearButton.setOnClickListener {
                val nombreAplicacion = nombreAplicacionInput.text.toString()
                val descripcionAplicacion = descripcionAplicacionInput.text.toString()
                val precioAplicacion = precioAplicacionInput.text.toString().toDouble()
                val aplicacion = Aplicacion(nombreAplicacion, descripcionAplicacion, precioAplicacion)
                firestoreDB.crearAplicacion(idSistemaOperativo ,aplicacion,
                    {
                        setResult(Activity.RESULT_OK, Intent().putExtra("aplicacionActualizada", aplicacion))
                        finish()
                    }
                    , { exception -> print("Error creando aplicacion") }
                )
            }
        }
    }
}