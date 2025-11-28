package com.example.proyectorecambio

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Producto(
    val nombre: String,
    val descripcion: String,
    val descripcionLarga: String = "",
    val precio: Double,
    val categoria: String,
    var cantidad: Int = 0,
    val imagen: Int = R.drawable.turbo
) : Parcelable {
    val precioFormateado: String
        get() = "$precio €"

    val precioTotal: Double
        get() = precio * cantidad
}