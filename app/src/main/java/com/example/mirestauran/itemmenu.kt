package com.example.mirestauran

/**
 * Data Class que modela un platillo individual en el menú.
 * Contiene propiedades básicas y la cantidad pedida.
 */
data class ItemMenu(
    val nombre: String,
    val precio: Double,
    var cantidad: Int = 0
) {
    /**
     * Función que calcula el subtotal de este ítem: Precio * Cantidad.
     */
    fun calcularSubtotal(): Double {
        return precio * cantidad
    }
}