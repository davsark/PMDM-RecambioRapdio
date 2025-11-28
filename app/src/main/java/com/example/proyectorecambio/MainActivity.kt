package com.example.proyectorecambio

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        Instala la SplashScreen del sistema antes de super.onCreate:
        - Permite que el tema de Splash se muestre de forma inmediata y consistente.
        - No infla aún el layout; solo prepara el hook de Splash.
       */
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        // Activar dibujo a borde (status/navigation bar) si usas Material3.
        enableEdgeToEdge()
        /*
       Flag para mantener la Splash visible unos segundos (simulación de carga).
       Clave: ya NO retrasamos setContentView; inflamos primero para tener vistas disponibles.
      */
        var keepSplash = true
        splashScreen.setKeepOnScreenCondition { keepSplash }
        /*
       Inflamos el layout principal de inmediato:
       - Así findViewById funciona y podemos configurar listeners y paddings.
      */
        setContentView(R.layout.activity_main)
        /*
        Ajuste de paddings según insets del sistema sobre el contenedor raíz con id @+id/main:
        - Evita que contenido quede bajo la barra de estado o navegación.
       */
        val root = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /*
         Configura botones de la pantalla inicial:
         - Login → abre LoginActivity.
         - Registrarse → muestra un Toast con “Opción no válida”.
        */
        val btnLogin: Button = findViewById(R.id.buttonLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)


        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnRegister.setOnClickListener {
            Toast.makeText(this, "Opción no válida", Toast.LENGTH_SHORT).show()
        }

        /*
         Simula carga de 3 segundos y suelta la Splash después:
         - El contenido ya está inflado; al expirar, el sistema hace la transición.
        */
        Handler(Looper.getMainLooper()).postDelayed({
            keepSplash = false
        }, 3000)
    }
}

