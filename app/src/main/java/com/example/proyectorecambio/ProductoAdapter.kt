package com.example.proyectorecambio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(
    private val productos: List<Producto>,
    private val onProductoClick: (Producto) -> Unit,
    private val onFavoritoClick: (Producto) -> Unit  // Callback para cuando se haga clic
) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.titulo)
        val descripcion: TextView = view.findViewById(R.id.descripcion)
        val precio: TextView = view.findViewById(R.id.precio)
        val imagen: ImageView = view.findViewById(R.id.imagen_card)
        val iconoCorazon: ImageView = view.findViewById(R.id.icono_corazon)  // El corazón
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_catalogo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]
        holder.titulo.text = producto.nombre
        holder.descripcion.text = producto.descripcion
        holder.precio.text = producto.precioFormateado
        holder.imagen.setImageResource(producto.imagen)

        // Clic en toda la card para ver detalle
        holder.itemView.setOnClickListener {
            onProductoClick(producto)
        }

        // Clic en la imagen también navega
        holder.imagen.setOnClickListener {
            onProductoClick(producto)
        }

        // Configurar el click del corazón
        holder.iconoCorazon.setOnClickListener {
            onFavoritoClick(producto)  // Llamar al callback
        }
    }

    override fun getItemCount() = productos.size
}