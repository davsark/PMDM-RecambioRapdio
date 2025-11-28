package com.example.proyectorecambio

class PasswordValidator(
    private val minLength: Int = 8,
    private val maxLength: Int = 16
) {
    // Expresión Regular para símbolos
    private val symbolRegex = Regex("[@#%\\!\\$*]")

    fun isValid(password: String): Boolean {
        return password.length >= minLength &&
                password.length <= maxLength &&
                password.any { it.isDigit() } &&
                password.any { it.isUpperCase() } && // Nueva condición: al menos una mayúscula
                symbolRegex.containsMatchIn(password)
    }

    fun validate(password: String): Pair<Boolean, String> {
        if (password.length > maxLength) return false to "Máximo $maxLength caracteres"
        if (password.length < minLength) return false to "Mínimo $minLength caracteres"

        if (!password.any { it.isDigit() }) return false to "Debe incluir al menos un número"

        // Nueva validación para mayúsculas
        if (!password.any { it.isUpperCase() }) return false to "Debe incluir al menos una letra mayúscula"

        if (!symbolRegex.containsMatchIn(password)) return false to "Debe incluir al menos uno de los siguientes: @, #, %, !, \$, o *"

        return true to "OK"
    }
}