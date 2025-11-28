package com.example.proyectorecambio

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * ProductDetailActivity - Pantalla de detalle de producto en Jetpack Compose
 *
 * Esta Activity recibe un objeto Producto mediante Intent (Serializable)
 * y devuelve un resultado a HomeActivity cuando se añade al carrito.
 */
class ProductDetailActivity : ComponentActivity() {

    companion object {
        const val EXTRA_PRODUCTO = "extra_producto"
        const val RESULT_PRODUCTO_NOMBRE = "ADDED_PRODUCT_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener el producto del Intent (compatible con Android 13+)
        val producto = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_PRODUCTO, Producto::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_PRODUCTO) as? Producto
        }

        if (producto == null) {
            finish()
            return
        }

        setContent {
            MaterialTheme {
                ProductDetailScreen(
                    producto = producto,
                    onBackPressed = { finish() },
                    onAddToCart = { addedProduct ->
                        // Devolver resultado a HomeActivity
                        val resultIntent = Intent().apply {
                            putExtra(RESULT_PRODUCTO_NOMBRE, addedProduct.nombre)
                        }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    producto: Producto,
    onBackPressed: () -> Unit,
    onAddToCart: (Producto) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Producto") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF99C5F7),
                    titleContentColor = Color(0xFF0D0D2A),
                    navigationIconContentColor = Color(0xFF0D0D2A)
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                modifier = Modifier.height(80.dp)
            ) {
                Button(
                    onClick = { onAddToCart(producto) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8AADFF)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Añadir al Carrito",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Imagen del producto
            Card(
                modifier = Modifier.fillMaxWidth().height(250.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = producto.imagen),
                    contentDescription = "Imagen de ${producto.nombre}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Badge de categoría (Motor/Frenada/Alumbrado)
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        when (producto.categoria.lowercase()) {
                            "motor" -> Color(0xFF8AADFF)
                            "frenada" -> Color(0xFFFF0000)
                            "alumbrado" -> Color(0xFFFFDC8A)
                            else -> Color(0xFF8AADFF)
                        }
                    ),
                color = when (producto.categoria.lowercase()) {
                    "motor" -> Color(0xFF8AADFF)
                    "frenada" -> Color(0xFFFF0000)
                    "alumbrado" -> Color(0xFFFFDC8A)
                    else -> Color(0xFF8AADFF)
                }
            ) {
                Text(
                    text = producto.categoria.uppercase(),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre, descripción, precio
            Text(
                text = producto.nombre,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D0D2A)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = producto.descripcion,
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = producto.precioFormateado,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8AADFF)
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFFD6E1FF)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Descripción Detallada",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D0D2A)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = producto.descripcionLarga,
                fontSize = 14.sp,
                color = Color.Black,
                lineHeight = 20.sp
            )
        }
    }
}
