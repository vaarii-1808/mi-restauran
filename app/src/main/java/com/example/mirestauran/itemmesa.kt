package com.example.mirestauran

/**
 * Clase que representa el Item/Estado de la única mesa del restaurante.
 * Contiene una instancia de la clase CuentaMesa.
 */
class ItemMesa(
    val numeroMesa: Int = 1
) {
    // La mesa tiene asociada una CuentaMesa (un pedido)
    val cuenta: CuentaMesa = CuentaMesa()

    /**
     * Función que verifica si la mesa tiene algún pedido pendiente.
     */
    fun estaAbierta(): Boolean {
        return cuenta.calcularTotalSinPropina() > 0
    }
}