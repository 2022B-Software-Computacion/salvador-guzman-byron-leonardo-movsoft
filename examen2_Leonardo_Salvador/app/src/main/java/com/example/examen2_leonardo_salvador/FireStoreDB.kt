package com.example.examen2_leonardo_salvador

import com.google.firebase.firestore.FirebaseFirestore

class FireStoreDB {

    private val db = FirebaseFirestore.getInstance()

    private val sistemaOperativoCollection = db.collection("sistemaOperativos")

    //  Sistemas Operativos

    fun crearSistemaOperativo(
        sistemaOperativo: SistemaOperativo,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        sistemaOperativoCollection.add(sistemaOperativo)
            .addOnSuccessListener { documentReference ->
                val aplicacionesRef = documentReference.collection("aplicaciones")
                for (aplicacion in sistemaOperativo.aplicaciones) {
                    aplicacionesRef.add(aplicacion)
                        .addOnSuccessListener {
                            aplicacion.codigo = it.id
                        }
                        .addOnFailureListener { onFailure(it) }
                }
                onSuccess(documentReference.id)
            }
            .addOnFailureListener { onFailure(it) }
    }

    fun actualizarSistemaOperativo(
        idSistemaOperativo: String,
        sistemaOperativo: SistemaOperativo,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        sistemaOperativoCollection.document(idSistemaOperativo).set(sistemaOperativo)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun eliminarSistemaOperativo(
        idSistemaOperativo: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        sistemaOperativoCollection.document(idSistemaOperativo).delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    //  Aplicaciones

    fun crearAplicacion(
        idSistemaOperativo: String,
        aplicacion: Aplicacion,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val aplicacionesRef = sistemaOperativoCollection.document(idSistemaOperativo).collection("aplicaciones")
        aplicacionesRef.add(aplicacion)
            .addOnSuccessListener {
                aplicacion.codigo = it.id
                onSuccess()
            }
            .addOnFailureListener { onFailure(it) }
    }

    fun actualizarAplicacion(
        idSistemaOperativo: String,
        aplicacion: Aplicacion,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val aplicacionesRef = sistemaOperativoCollection.document(idSistemaOperativo).collection("aplicaciones")
        aplicacionesRef.document(aplicacion.codigo!!).set(aplicacion)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun eliminarAplicacion(
        idSistemaOperativo: String,
        idAplicacion: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val aplicacionesCollection =
            sistemaOperativoCollection.document(idSistemaOperativo).collection("aplicaciones").document(idAplicacion)
        aplicacionesCollection.get()
            .addOnSuccessListener { aplicacionDoc ->
                if (aplicacionDoc.exists()) {
                    aplicacionesCollection.delete()
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onFailure(it) }
                }
            }
            .addOnFailureListener { onFailure(it) }
    }

    fun getSistemasOperativos(onSuccess: (List<SistemaOperativo>) -> Unit, onFailure: (Exception) -> Unit) {
        sistemaOperativoCollection.get()
            .addOnSuccessListener { result ->
                val sistemaOperativos = mutableListOf<SistemaOperativo>()
                for (document in result) {
                    val sistemaOperativo = document.toObject(SistemaOperativo::class.java)
                    sistemaOperativo.codigo = document.id
                    sistemaOperativos.add(sistemaOperativo)
                    getAplicaciones(sistemaOperativo.codigo!!, onSuccess = {
                        sistemaOperativo.aplicaciones = it
                    }, onFailure = {})
                }
                onSuccess(sistemaOperativos)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getAplicaciones(
        idSistemaOperativo: String,
        onSuccess: (List<Aplicacion>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val aplicacionesCollection = sistemaOperativoCollection.document(idSistemaOperativo).collection("aplicaciones")

        aplicacionesCollection.get()
            .addOnSuccessListener { result ->
                val aplicaciones = mutableListOf<Aplicacion>()
                for (document in result) {
                    val aplicacion = document.toObject(Aplicacion::class.java)
                    aplicacion.codigo = document.id
                    aplicaciones.add(aplicacion)
                }
                onSuccess(aplicaciones)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
}