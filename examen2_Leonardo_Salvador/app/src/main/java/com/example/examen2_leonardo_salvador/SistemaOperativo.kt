package com.example.examen2_leonardo_salvador

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.Exclude

data class SistemaOperativo(
    @Exclude @JvmField var codigo: String?,
    val nombreSistemaOperativo: String,
    val precioSistemaOperativo: Int,
    val descripcionSistemaOperativo: String,
    @Exclude @JvmField var aplicaciones: List<Aplicacion> = emptyList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createTypedArrayList(Aplicacion)!!
    )

    constructor(
        nombreSistemaOperativo: String,
        precioSistemaOperativo: Int,
        descripcionSistemaOperativo: String,
        aplicaciones: List<Aplicacion>
    ) : this(
        null,
        nombreSistemaOperativo,
        precioSistemaOperativo,
        descripcionSistemaOperativo,
        aplicaciones
    )

    constructor() : this(null, "", 0, "", emptyList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(codigo)
        parcel.writeString(nombreSistemaOperativo)
        parcel.writeInt(precioSistemaOperativo)
        parcel.writeString(descripcionSistemaOperativo)
        parcel.writeTypedList(aplicaciones)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SistemaOperativo> {
        override fun createFromParcel(parcel: Parcel): SistemaOperativo {
            return SistemaOperativo(parcel)
        }

        override fun newArray(size: Int): Array<SistemaOperativo?> {
            return arrayOfNulls(size)
        }
    }
}