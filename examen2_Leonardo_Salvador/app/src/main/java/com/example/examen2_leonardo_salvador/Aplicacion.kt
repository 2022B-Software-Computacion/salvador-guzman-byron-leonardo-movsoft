package com.example.examen2_leonardo_salvador

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.Exclude

data class Aplicacion(
    @Exclude @JvmField var codigo: String?,
    val nombreAplicacion: String = "",
    val descripcionAplicacion: String = "",
    val precioAplicacion: Double = 0.0
): Parcelable {

    constructor(
        nombreAplicacion: String,
        descripcionAplicacion: String,
        precioAplicacion: Double
    ) : this(
        null,
        nombreAplicacion,
        descripcionAplicacion,
        precioAplicacion
    )
    constructor() : this(null, "", "", 0.0)

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(codigo)
        parcel.writeString(nombreAplicacion)
        parcel.writeString(descripcionAplicacion)
        parcel.writeDouble(precioAplicacion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Aplicacion> {
        override fun createFromParcel(parcel: Parcel): Aplicacion {
            return Aplicacion(parcel)
        }

        override fun newArray(size: Int): Array<Aplicacion?> {
            return arrayOfNulls(size)
        }
    }

}
