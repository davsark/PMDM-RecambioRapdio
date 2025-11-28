package com.example.proyectorecambio

import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectorecambio.databinding.ActivityHomeBinding
import android.content.Intent
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val productosEnCarrito = mutableListOf<Producto>()
    private lateinit var productosCatalogo: List<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ==================================================================
        // DATOS DEL CATÁLOGO
        // ==================================================================

        productosCatalogo = listOf(
            // MOTOR
            Producto(
                nombre = "Turbo K03",
                descripcion = "Repuesto original para motores 1.8T",
                descripcionLarga = "Turbocompresor de alto rendimiento K03 diseñado específicamente para motores 1.8T. " +
                        "Fabricado con materiales de primera calidad que garantizan durabilidad y rendimiento óptimo. " +
                        "Incluye todos los componentes necesarios para la instalación. Compatible con múltiples modelos " +
                        "de Volkswagen, Audi y Seat. Mejora significativamente la potencia y respuesta del motor. " +
                        "Probado bajo estrictos controles de calidad. Incluye garantía de 2 años.",
                precio = 250.0,
                categoria = "Motor",
                imagen = R.drawable.turbo
            ),
            Producto(
                nombre = "Filtro de Aire",
                descripcion = "Filtro de alto rendimiento K&N",
                descripcionLarga = "Filtro de aire de alto flujo K&N fabricado con múltiples capas de algodón " +
                        "impregnado en aceite especial. Proporciona hasta un 50% más de flujo de aire que los filtros " +
                        "convencionales. Lavable y reutilizable, con una vida útil de hasta 1.6 millones de kilómetros. " +
                        "Mejora la aceleración y la potencia del motor. Fácil instalación sin modificaciones. " +
                        "Incluye kit de limpieza y recarga. Garantía de 10 años o 1.6 millones de km.",
                precio = 65.0,
                categoria = "Motor",
                imagen = R.drawable.filtro_aire
            ),
            Producto(
                nombre = "Bujía Iridium",
                descripcion = "Bujía de alto rendimiento NGK",
                descripcionLarga = "Bujía de alto rendimiento NGK con electrodo de iridio. El iridio es 6 veces " +
                        "más duro y 8 veces más resistente que el platino. Proporciona una ignición más eficiente " +
                        "y constante. Reduce el consumo de combustible hasta un 5%. Mayor duración que las bujías " +
                        "convencionales (hasta 100,000 km). Mejora el arranque en frío. Compatible con sistemas " +
                        "de encendido modernos. Temperatura de trabajo optimizada para máximo rendimiento.",
                precio = 12.0,
                categoria = "Motor",
                imagen = R.drawable.bujia
            ),
            Producto(
                nombre = "Aceite 5W30",
                descripcion = "Aceite sintético Castrol Edge 5L",
                descripcionLarga = "Aceite motor totalmente sintético Castrol EDGE 5W-30 con Tecnología Fluid " +
                        "TITANIUM FST. Ofrece máxima protección contra el desgaste del motor bajo condiciones " +
                        "extremas de presión y temperatura. Reduce la fricción interna del motor optimizando " +
                        "el rendimiento. Mantiene el motor limpio con tecnología de dispersión de partículas. " +
                        "Cumple con las especificaciones ACEA C3, API SN/CF. Formato de 5 litros. Ideal para " +
                        "vehículos de alto rendimiento y conducción deportiva.",
                precio = 45.0,
                categoria = "Motor",
                imagen = R.drawable.aceite
            ),

            // FRENADA
            Producto(
                nombre = "Pastillas de Freno",
                descripcion = "Compuesto cerámico EBC RedStuff",
                descripcionLarga = "Pastillas de freno deportivas EBC RedStuff con compuesto cerámico de última " +
                        "generación. Diseñadas para uso en calle y ocasional en pista. Excelente rendimiento en " +
                        "frío y extraordinario en caliente (temperatura de trabajo hasta 750°C). Reducen " +
                        "significativamente el polvo de freno. Menor ruido y vibración. No dañan los discos. " +
                        "Compatible con sistemas ABS y ESP. Incluye sensor de desgaste. Frenada progresiva " +
                        "y predecible. Ideal para conducción deportiva diaria.",
                precio = 110.0,
                categoria = "Frenada",
                imagen = R.drawable.pastillas_freno
            ),
            Producto(
                nombre = "Discos de Freno",
                descripcion = "Discos ventilados Brembo 320mm",
                descripcionLarga = "Discos de freno ventilados Brembo de 320mm de diámetro fabricados en hierro " +
                        "fundido de alta calidad con tratamiento UV. Diseño de ventilación optimizada para " +
                        "máxima disipación del calor. Superficie de frenado rectificada con precisión láser. " +
                        "Reducen el fadening (pérdida de eficacia) en frenadas intensas. Compatible con pastillas " +
                        "originales y deportivas. Balanceados dinámicamente para eliminar vibraciones. Incluye " +
                        "tornillería de montaje. Excelente durabilidad y consistencia de frenada.",
                precio = 180.0,
                categoria = "Frenada",
                imagen = R.drawable.discos_freno
            ),
            Producto(
                nombre = "Latiguillos Metálicos",
                descripcion = "Kit latiguillos trenzados Goodridge",
                descripcionLarga = "Kit completo de latiguillos de freno trenzados en acero inoxidable Goodridge. " +
                        "Proporciona una sensación de pedal más firme y directa. Elimina la expansión de las " +
                        "mangueras de goma bajo presión. Mejora la respuesta del sistema de frenado. Fabricados " +
                        "con núcleo de PTFE (teflón) y malla exterior de acero inoxidable trenzado. Resistentes " +
                        "a la abrasión y productos químicos. Incluye todos los racores necesarios. Homologación " +
                        "TÜV. Fácil instalación. Acabado en color acero o negro.",
                precio = 85.0,
                categoria = "Frenada",
                imagen = R.drawable.latiguillos
            ),
            Producto(
                nombre = "Líquido de Frenos",
                descripcion = "DOT 4 Castrol React SRF Racing",
                descripcionLarga = "Líquido de frenos de competición Castrol React SRF Racing DOT 4. Punto de " +
                        "ebullición en seco excepcional de 310°C y en húmedo de 270°C (muy superior al DOT 4 " +
                        "estándar). Especialmente formulado para condiciones extremas de temperatura. Mantiene " +
                        "sus propiedades incluso después de múltiples sesiones intensas. Excelente resistencia " +
                        "a la absorción de humedad. Compatible con sistemas ABS y ESP. Reduce el riesgo de " +
                        "vapor lock (pérdida de frenada por vapor). Formato de 500ml. Recomendado cambio anual.",
                precio = 25.0,
                categoria = "Frenada",
                imagen = R.drawable.liquido_frenos
            ),

            // ALUMBRADO
            Producto(
                nombre = "Faro Delantero",
                descripcion = "Faro LED completo con lupa",
                descripcionLarga = "Faro delantero completo con tecnología LED y lente de proyección (lupa). " +
                        "Iluminación de alto rendimiento con temperatura de color 6000K (blanco diamante). " +
                        "Consumo reducido de energía (60% menos que halógeno). Mayor vida útil (más de 30,000 horas). " +
                        "Incluye luz de posición LED integrada. Carcasa en policarbonato de alta resistencia. " +
                        "Óptica de proyección para haz de luz preciso y sin deslumbramiento. Homologación ECE. " +
                        "Instalación plug & play. Compatible con sistemas de regulación de altura automática.",
                precio = 320.0,
                categoria = "Alumbrado",
                imagen = R.drawable.faro
            ),
            Producto(
                nombre = "Bombilla H7",
                descripcion = "Kit LED H7 6000K Philips",
                descripcionLarga = "Kit de conversión LED H7 Philips Ultinon Pro6000 con temperatura de color " +
                        "6000K. Proporciona hasta un 230% más de luz que las halógenas estándar. Patrón de luz " +
                        "preciso sin zonas oscuras. Tecnología SafeBeam que previene el deslumbramiento. " +
                        "Sistema de refrigeración AirFlux para mayor durabilidad. Vida útil de 5000 horas. " +
                        "Instalación sencilla sin necesidad de modificaciones. Compatible con sistemas CANBus. " +
                        "Certificación ECE R37 y R128. Incluye dos bombillas LED. Garantía de 5 años.",
                precio = 35.0,
                categoria = "Alumbrado",
                imagen = R.drawable.bombilla_h7
            ),
            Producto(
                nombre = "Bombilla D1S",
                descripcion = "Xenón D1S 4300K Osram",
                descripcionLarga = "Bombilla de xenón original Osram Xenarc D1S con temperatura de color 4300K " +
                        "(luz blanca natural). Proporciona hasta 3 veces más luz que las halógenas. Mayor alcance " +
                        "y amplitud del haz de luz. Consume menos energía que las bombillas convencionales. " +
                        "Vida útil de hasta 2500 horas. Produce menos calor. Luz más parecida a la del día " +
                        "reduciendo la fatiga visual. Compatible con todos los sistemas de xenón OEM. " +
                        "Homologación ECE. Incluye certificado de garantía de 4 años.",
                precio = 55.0,
                categoria = "Alumbrado",
                imagen = R.drawable.bombilla_d1s
            ),
            Producto(
                nombre = "Bombilla P21W",
                descripcion = "LED P21W intermitente ámbar",
                descripcionLarga = "Bombilla LED P21W especialmente diseñada para intermitentes con luz ámbar. " +
                        "Mayor visibilidad que las bombillas incandescentes tradicionales. Encendido instantáneo " +
                        "sin retardo. Consumo mínimo de energía (80% menos que halógena). No genera calor excesivo. " +
                        "Compatible con la mayoría de sistemas CANBus sin necesidad de resistencias adicionales. " +
                        "Vida útil superior a 50,000 horas. Construcción robusta con circuito integrado protegido. " +
                        "Casquillo BA15S estándar. Voltaje 12V. Pack de 2 unidades.",
                precio = 15.0,
                categoria = "Alumbrado",
                imagen = R.drawable.bombilla_p21w
            )
        )

        // ==================================================================
        // RECYCLERVIEW HORIZONTAL PARA EL CATÁLOGO
        // ==================================================================

        binding.recyclerViewCatalogo!!.apply {
            layoutManager = LinearLayoutManager(
                this@HomeActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = ProductoAdapter(
                productos = productosCatalogo,
                onProductoClick = { producto ->
                    // Navegar a la pantalla de detalle
                    val intent = Intent(this@HomeActivity, ProductDetailActivity::class.java)
                    intent.putExtra(ProductDetailActivity.EXTRA_PRODUCTO, producto)
                    startActivity(intent)
                },
                onFavoritoClick = { producto ->
                    agregarAlCarrito(producto)
                }
            )

        // ==================================================================
        // CONFIGURAR SPINNER/FILTRO
        // ==================================================================

        binding.contenedorFiltroClickable?.setOnClickListener { view ->
            mostrarMenuFiltro(view)
        }

        actualizarCarrito()
    }

    // ==================================================================
    // MOSTRAR MENÚ DE FILTRO
    // ==================================================================

    private fun mostrarMenuFiltro(view: android.view.View) {
        val popup = PopupMenu(this, view)

        popup.menu.add(0, 0, 0, "Mostrar todas")
        popup.menu.add(0, 1, 1, "Motor")
        popup.menu.add(0, 2, 2, "Frenada")
        popup.menu.add(0, 3, 3, "Alumbrado")

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                0 -> filtrarProductos("Mostrar todas")
                1 -> filtrarProductos("Motor")
                2 -> filtrarProductos("Frenada")
                3 -> filtrarProductos("Alumbrado")
            }
            true
        }

        popup.show()
    }

    // ==================================================================
    // FILTRAR PRODUCTOS
    // ==================================================================

    private fun filtrarProductos(categoria: String) {
        binding.tvFiltro?.text = categoria

        val productosFiltrados = if (categoria == "Mostrar todas") {
            productosCatalogo
        } else {
            productosCatalogo.filter { it.categoria == categoria }
        }

        binding.recyclerViewCatalogo?.adapter = ProductoAdapter(
            productos = productosFiltrados,
            onProductoClick = { producto ->
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra(ProductDetailActivity.EXTRA_PRODUCTO, producto)
                startActivity(intent)
            },
            onFavoritoClick = { producto ->
                agregarAlCarrito(producto)
            }
        )

    // ==================================================================
    // AGREGAR AL CARRITO
    // ==================================================================

    private fun agregarAlCarrito(producto: Producto) {
        val productoExistente = productosEnCarrito.find { it.nombre == producto.nombre }

        if (productoExistente != null) {
            productoExistente.cantidad++
            Toast.makeText(
                this,
                "${producto.nombre} - Cantidad: ${productoExistente.cantidad}",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val nuevoProducto = producto.copy(cantidad = 1)
            productosEnCarrito.add(nuevoProducto)
            Toast.makeText(
                this,
                "${producto.nombre} añadido al carrito",
                Toast.LENGTH_SHORT
            ).show()
        }

        actualizarCarrito()
    }

    // ==================================================================
    // ACTUALIZAR VISTA DEL CARRITO
    // ==================================================================

    private fun actualizarCarrito() {
        binding.listContainer?.removeAllViews()

        if (productosEnCarrito.isEmpty()) {
            val mensajeVacio = TextView(this).apply {
                text = "El carrito está vacío\n\n❤️ Añade productos desde el catálogo"
                textSize = 16f
                setPadding(16, 32, 16, 32)
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                setTextColor(resources.getColor(android.R.color.darker_gray, null))
            }
            binding.listContainer?.addView(mensajeVacio)
            binding.tvTotalFinal?.text = "0.00 €"
            return
        }

        val inflater = layoutInflater.cloneInContext(this)

        for (producto in productosEnCarrito) {
            val item = inflater.inflate(R.layout.item_producto, binding.listContainer, false)

            val txtNombre = item.findViewById<TextView>(R.id.txtNombre)
            val txtCantidad = item.findViewById<TextView>(R.id.txtCantidad)
            val txtTotal = item.findViewById<TextView>(R.id.txtTotalLinea)
            val btnRestar = item.findViewById<ImageButton>(R.id.btnRestar)
            val btnSumar = item.findViewById<ImageButton>(R.id.btnSumar)
            val btnEliminar = item.findViewById<ImageButton>(R.id.btnEliminar)

            txtNombre.text = producto.nombre
            txtCantidad.text = producto.cantidad.toString()
            txtTotal.text = String.format("%.2f €", producto.precioTotal)

            btnRestar.setOnClickListener {
                if (producto.cantidad > 1) {
                    producto.cantidad--
                    Toast.makeText(
                        this,
                        "Cantidad reducida: ${producto.cantidad}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    productosEnCarrito.remove(producto)
                    Toast.makeText(
                        this,
                        "${producto.nombre} eliminado del carrito",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                actualizarCarrito()
            }

            btnSumar.setOnClickListener {
                producto.cantidad++
                Toast.makeText(
                    this,
                    "Cantidad aumentada: ${producto.cantidad}",
                    Toast.LENGTH_SHORT
                ).show()
                actualizarCarrito()
            }

            btnEliminar.setOnClickListener {
                productosEnCarrito.remove(producto)
                Toast.makeText(
                    this,
                    "${producto.nombre} eliminado del carrito",
                    Toast.LENGTH_SHORT
                ).show()
                actualizarCarrito()
            }

            binding.listContainer?.addView(item)
        }

        val totalGeneral = productosEnCarrito.sumOf { it.precioTotal }
        binding.tvTotalFinal?.text = String.format("%.2f €", totalGeneral)
    }
}