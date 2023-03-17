package com.example.examen2_leonardo_salvador

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val firestoreDB = FireStoreDB()
    var idItemSeleccionado = 0
    lateinit var sistemaOperativos: List<SistemaOperativo>
    lateinit var sistemaOperativosNames: ArrayList<String>
    lateinit var sistemaOperativosListView: ListView

    private val actualizarSistemaOperativo =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val sistemaOperativoActualizada =
                    result.data?.getParcelableExtra<SistemaOperativo>("sistemaOperativoActualizada")

                if (sistemaOperativoActualizada != null) {
                    val index: Int =
                        sistemaOperativos.indexOfFirst { it.codigo == sistemaOperativoActualizada.codigo }

                    if (index >= 0) {
                        val aplicacionesActualizados = sistemaOperativos.toMutableList()
                        aplicacionesActualizados[index] = sistemaOperativoActualizada
                        sistemaOperativos = aplicacionesActualizados.toList()

                        sistemaOperativosNames[index] = sistemaOperativoActualizada.nombreSistemaOperativo
                        Log.e("TAG", sistemaOperativosNames.toString())

                        (sistemaOperativosListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    } else {
                        val aplicacionesActualizados = sistemaOperativos.toMutableList()
                        aplicacionesActualizados.add(sistemaOperativoActualizada)
                        sistemaOperativos = aplicacionesActualizados.toList()
                        sistemaOperativosNames.add(sistemaOperativoActualizada.nombreSistemaOperativo)
                        (sistemaOperativosListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    }
                }

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.sistemaOperativosListView = findViewById<ListView>(R.id.id_listview_SistemaOperativo)
        getSistemasOperativos()
        registerForContextMenu(sistemaOperativosListView)
        val btnCrearSistemasOperativos: Button = findViewById<Button>(R.id.btn_crear_sistemaoperativo)
        btnCrearSistemasOperativos.setOnClickListener {
            actualizarSistemaOperativo(null)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_sistemaoperativo, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                actualizarSistemaOperativo(sistemaOperativos[idItemSeleccionado])
                return true
            }
            R.id.mi_eliminar -> {
                eliminarSistemaOperativo()
                return true
            }
            R.id.mi_aplicaciones -> {
                getAplicaciones()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun actualizarSistemaOperativo(
        sistemaOperativo: SistemaOperativo?
    ) {
        val intent = Intent(this, SistemaOperativoAdapter::class.java)
        intent.putExtra("sistemaoperativo", sistemaOperativo)
        actualizarSistemaOperativo.launch(intent)
    }

    fun eliminarSistemaOperativo() {
        val sistemaoperativoIndex = idItemSeleccionado
        val sistemaoperativoId = sistemaOperativos[sistemaoperativoIndex].codigo

        firestoreDB.eliminarSistemaOperativo(sistemaoperativoId!!,
            onSuccess = {
                Toast.makeText(this, "Sistema Operativo eliminado con exito", Toast.LENGTH_SHORT)
                    .show()
                sistemaOperativos = sistemaOperativos.filterIndexed { index, _ -> index != sistemaoperativoIndex }
                sistemaOperativosNames.removeAt(sistemaoperativoIndex)
                (sistemaOperativosListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            },
            onFailure = { error -> print("Error eliminando sistema operativo") }
        )

    }

    fun getAplicaciones() {
        val sistemaoperativo = sistemaOperativos[idItemSeleccionado]

        val intent = Intent(this, SistemaOperativoAplicacion::class.java)
        intent.putExtra("idSistemaOperativo", sistemaoperativo.codigo)
        intent.putExtra("sistemaoperativoName", sistemaoperativo.nombreSistemaOperativo)
        intent.putParcelableArrayListExtra(
            "aplicaciones",
            ArrayList(sistemaoperativo.aplicaciones)
        )

        startActivity(intent)
    }

    fun getSistemasOperativos() {
        firestoreDB.getSistemasOperativos(
            onSuccess = { sistemaoperativos ->
                this.sistemaOperativos = sistemaoperativos
                sistemaOperativosNames = sistemaoperativos.map { it.nombreSistemaOperativo } as ArrayList<String>
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sistemaOperativosNames)
                sistemaOperativosListView.adapter = adapter
                adapter.notifyDataSetChanged()
            },
            onFailure = { error ->
                print("Error retornando sistemas operativos")
            }
        )
    }


}