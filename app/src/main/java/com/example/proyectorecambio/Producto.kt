package com.example.proyectorecambio

data class Producto(
    val nombre: String,
    val descripcion: String,
    val descripcionLarga: String = "",
    val precio: Double,
    val categoria: String,
    var cantidad: Int = 0,
    val imagen: Int = R.drawable.turbo
) : java.io.Serializable {
    val precioFormateado: String
        get() = "$precio €"

    val precioTotal: Double
        get() = precio * cantidad
}