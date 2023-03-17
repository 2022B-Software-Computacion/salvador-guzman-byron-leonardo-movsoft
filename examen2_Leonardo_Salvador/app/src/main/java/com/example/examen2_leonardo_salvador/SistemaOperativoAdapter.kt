package com.example.examen2_leonardo_salvador

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SistemaOperativoAdapter : AppCompatActivity() {

    val firestoreDB = FireStoreDB()
    lateinit var idSistemaOperativo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistemaoperativo)

        val nombreSistemaOperativoInput = findViewById<EditText>(R.id.id_nombre_sistemaoperativo_input)
        val precioSistemaOperativoInput = findViewById<EditText>(R.id.id_precio_sistemaoperativo_input)
        val descripcionSistemaOperativoInput = findViewById<EditText>(R.id.id_descripcion_sistemaoperativo_input)
        val crearButton = findViewById<Button>(R.id.btn_crear_sistemaoperativo)

        val sistemaOperativo = intent.getParcelableExtra<SistemaOperativo>("sistemaoperativo")

        if (sistemaOperativo != null) {
            idSistemaOperativo = sistemaOperativo.codigo!!
            nombreSistemaOperativoInput.setText(sistemaOperativo.nombreSistemaOperativo)
            precioSistemaOperativoInput.setText(sistemaOperativo.precioSistemaOperativo.toString())
            descripcionSistemaOperativoInput.setText(sistemaOperativo.descripcionSistemaOperativo)
            crearButton.setOnClickListener {
                val nombreSistemaOperativo = nombreSistemaOperativoInput.text.toString()
                val precioSistemaOperativo = precioSistemaOperativoInput.text.toString().toInt()
                val descripcionSistemaOperativo = descripcionSistemaOperativoInput.text.toString()
                val newSistemaOperativo = SistemaOperativo(sistemaOperativo.codigo, nombreSistemaOperativo, precioSistemaOperativo, descripcionSistemaOperativo)
                firestoreDB.actualizarSistemaOperativo(idSistemaOperativo, newSistemaOperativo, {
                    setResult(
                        Activity.RESULT_OK,
                        Intent().putExtra("sistemaOperativoActualizada", newSistemaOperativo)
                    )
                    finish()
                }, { exception -> print("Error actualizando sistema operativo") }
                )
            }
        } else {
            crearButton.setOnClickListener {
                val nombreSistemaOperativo = nombreSistemaOperativoInput.text.toString()
                val precioSistemaOperativo = precioSistemaOperativoInput.text.toString().toInt()
                val descripcionSistemaOperativo = descripcionSistemaOperativoInput.text.toString()
                val sistemaOperativo = SistemaOperativo(nombreSistemaOperativo, precioSistemaOperativo, descripcionSistemaOperativo, emptyList())

                firestoreDB.crearSistemaOperativo(sistemaOperativo,
                    {
                        sistemaOperativo.codigo = it
                        setResult(
                            Activity.RESULT_OK,
                            Intent().putExtra("sistemaOperativoActualizada", sistemaOperativo)
                        )
                        finish()
                    }, { exception -> print("Error creando el sistema operativo") }
                )
            }
        }
    }
}