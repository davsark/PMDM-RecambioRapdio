package com.example.proyectorecambio

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View // Importar View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // ... (Vinculación de vistas, etc.)
    private lateinit var etUser: EditText
    private lateinit var etPass: EditText
    private lateinit var tvError: TextView // Lo usaremos para el mensaje general
    private lateinit var btnEntrar: Button

    // Instancia el validador, usando los valores por defecto (8 y 16)
    // que incluyen todas las nuevas reglas: min/max, dígito, mayúscula, símbolo.
    private val validator = PasswordValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1) Vincula vistas
        etUser = findViewById(R.id.etUser)
        etPass = findViewById(R.id.etPass)
        tvError = findViewById(R.id.tvError)
        btnEntrar = findViewById(R.id.buttonLogin)

        // Asegúrate de que el TextView de error esté oculto al inicio
        tvError.visibility = View.GONE

        /* 2) Observa cambios de texto */
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val userOk = etUser.text.toString().isNotBlank()
                val passText = etPass.text.toString()

                // Llama a la validación
                val (ok, msg) = validator.validate(passText)

                // Mensaje de error visible solo si hay texto y falla
                if (passText.isNotEmpty() && !ok) {
                    // Muestra el error en el EditText (aparece un ícono de error)
                    etPass.error = msg
                    // Muestra el error en el TextView
                    tvError.text = "Error: $msg"
                    tvError.visibility = View.VISIBLE
                } else {
                    etPass.error = null // Borra el error del EditText
                    tvError.text = ""
                    tvError.visibility = View.GONE
                }

                // Botón habilitado solo si ambos son correctos (usuario no vacío y contraseña VÁLIDA)
                btnEntrar.isEnabled = userOk && ok
            }
        }
        etUser.addTextChangedListener(watcher)
        etPass.addTextChangedListener(watcher)

        /* 3) Acción de botones */
        btnEntrar.setOnClickListener {
            Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }

        var botonRegistrarse = findViewById<Button>(R.id.registrarse)
        botonRegistrarse.setOnClickListener {
            Toast.makeText(this, "Opción no válida", Toast.LENGTH_SHORT).show()
        }
    }
}