package com.example.examen2_leonardo_salvador

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts


class SistemaOperativoAplicacion : AppCompatActivity() {
    lateinit var listViewAplicaciones: ListView
    lateinit var nombreSistemaOperativoTextView: TextView

    val firestoreDB = FireStoreDB()

    var idItemSeleccionado = 0
    var aplicaciones: List<Aplicacion>? = null
    lateinit var nombreAplicaciones: ArrayList<String>
    lateinit var nombreSistemaOperativo: String
    lateinit var idSistemaOperativo: String
    private val actualizarAplicacion =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { aplicacionActualizado ->

            if (aplicacionActualizado.resultCode == Activity.RESULT_OK) {
                val updatedAplicacion =
                    aplicacionActualizado.data?.getParcelableExtra<Aplicacion>("aplicacionActualizado")

                if (updatedAplicacion != null) {
                    val index: Int =
                        aplicaciones!!.indexOfFirst { it.codigo == updatedAplicacion.codigo }

                    if (index >= 0) {
                        val updatedAplicaciones = aplicaciones!!.toMutableList()
                        updatedAplicaciones[index] = updatedAplicacion
                        aplicaciones = updatedAplicaciones.toList()
                        nombreAplicaciones[index] =
                            updatedAplicacion.nombreAplicacion + " " + updatedAplicacion.descripcionAplicacion
                        (listViewAplicaciones.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    } else {
                        val updatedAplicaciones = aplicaciones!!.toMutableList()
                        updatedAplicaciones.add(updatedAplicacion)
                        aplicaciones = updatedAplicaciones.toList()
                        nombreAplicaciones.add(updatedAplicacion.nombreAplicacion + " " + updatedAplicacion.descripcionAplicacion)
                        (listViewAplicaciones.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistemaoperativo_aplicaciones)
        val btnCreateAplicacion = findViewById<Button>(R.id.id_btn_createAplicacion)
        btnCreateAplicacion.setOnClickListener {
            editarCreateAplicacion(null)
        }
        nombreSistemaOperativoTextView = findViewById<TextView>(R.id.id_textview_nombreSistemaOperativo)
        listViewAplicaciones = findViewById<ListView>(R.id.id_listview_aplicaciones)

        idSistemaOperativo = intent.getStringExtra("idSistemaOperativo")!!
        nombreSistemaOperativo = intent.getStringExtra("sistemaoperativoName")!!
        aplicaciones = intent.getParcelableArrayListExtra<Aplicacion>("aplicaciones")

        nombreAplicaciones = aplicaciones?.map { it.nombreAplicacion + " " + it.descripcionAplicacion } as ArrayList<String>

        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                nombreAplicaciones as List<String>
            )

        listViewAplicaciones.adapter = adapter

        nombreSistemaOperativoTextView.text = nombreSistemaOperativo
        adapter.notifyDataSetChanged()

        registerForContextMenu(listViewAplicaciones)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_aplicaciones, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.aplicacion_editar -> {
                editarCreateAplicacion(aplicaciones!![idItemSeleccionado])
                return true
            }
            R.id.aplicacion_eliminar -> {
                eliminarAplicacion()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun editarCreateAplicacion(
        aplicacion: Aplicacion?
    ) {
        val intent = Intent(this, AplicacionAdapter::class.java)
        intent.putExtra("idSistemaOperativo", idSistemaOperativo)
        intent.putExtra("aplicacion", aplicacion)

        actualizarAplicacion.launch(intent)
    }

    fun eliminarAplicacion() {

        val aplicacionIndex = idItemSeleccionado
        val aplicacionID = aplicaciones!![idItemSeleccionado].codigo!!
        firestoreDB.eliminarAplicacion(
            idSistemaOperativo, aplicacionID,
            onSuccess = {
                Toast.makeText(this, "Aplicacion eliminada con exito", Toast.LENGTH_SHORT)
                    .show()
                aplicaciones = aplicaciones?.filterIndexed { index, _ -> index != aplicacionIndex }
                nombreAplicaciones.removeAt(aplicacionIndex)
                (listViewAplicaciones.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            },
            onFailure = { error ->
                Toast.makeText(
                    this,
                    "Error al eliminar a la aplicacion: $error",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Error al eliminar", error.toString())
            }
        )
    }


}
