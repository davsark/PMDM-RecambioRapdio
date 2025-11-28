package com.example.proyectorecambio

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectorecambio.databinding.ActivityProductDetailBinding

/**
 * Activity que muestra los detalles completos de un producto.
 *
 * Utiliza ViewBinding para acceso seguro a las vistas y recibe
 * un objeto Producto mediante Parcelable a través de Intent extras.
 */
class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var producto: Producto? = null

    companion object {
        const val EXTRA_PRODUCTO = "extra_producto"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el producto pasado desde HomeActivity
        producto = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_PRODUCTO, Producto::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PRODUCTO)
        }

        // Validar que el producto no sea null
        if (producto == null) {
            Toast.makeText(
                this,
                "Error: No se pudo cargar el producto",
                Toast.LENGTH_SHORT
            ).show()
            finish()
            return
        }

        // Configurar la interfaz con los datos del producto
        setupUI()
        setupListeners()
    }

    /**
     * Configura la interfaz de usuario con los datos del producto.
     */
    private fun setupUI() {
        producto?.let { prod ->
            with(binding) {
                // Configurar imagen
                imgProducto.setImageResource(prod.imagen)

                // Configurar textos
                tvNombre.text = prod.nombre
                tvDescripcion.text = prod.descripcion
                tvDescripcionLarga.text = prod.descripcionLarga
                tvPrecio.text = prod.precioFormateado
                tvCategoria.text = prod.categoria.uppercase()
                tvToolbarTitle.text = prod.nombre

                // Personalizar color del badge según categoría
                val categoriaColor = when (prod.categoria.lowercase()) {
                    "motor" -> getColor(R.color.azulR)
                    "frenada" -> getColor(R.color.red)
                    "alumbrado" -> getColor(R.color.amarillo)
                    else -> getColor(R.color.azulR)
                }
                tvCategoria.setBackgroundColor(categoriaColor)
            }
        }
    }

    /**
     * Configura los listeners de los botones.
     */
    private fun setupListeners() {
        // Botón volver
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Botón añadir al carrito
        binding.btnAgregarCarrito.setOnClickListener {
            producto?.let { prod ->
                // TODO: Implementar lógica de carrito compartido o pasar resultado
                Toast.makeText(
                    this,
                    "${prod.nombre} añadido al carrito",
                    Toast.LENGTH_SHORT
                ).show()

                // Opción: volver a HomeActivity
                finish()
            }
        }
    }
}
