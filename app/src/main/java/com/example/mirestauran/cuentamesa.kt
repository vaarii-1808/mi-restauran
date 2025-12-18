package com.example.mirestauran

/**
 * Clase que modela la Cuenta o Pedido.
 * Es responsable de gestionar los ítems y calcular los totales finales.
 */
class CuentaMesa {

    // Lista de ítems iniciales del menú.
    val menuItems = mutableListOf(
        ItemMenu("Pastel de Choclo", 12000.0),
        ItemMenu("Cazuela", 10000.0)
    )

    // Estado del Switch de Propina.
    var propinaActivada: Boolean = false
    private val PORCENTAJE_PROPINA = 0.10 // 10%

    /**
     * Función que calcula el subtotal de todos los ítems pedidos.
     */
    fun calcularTotalSinPropina(): Double {
        var total = 0.0
        for (item in menuItems) {
            total += item.calcularSubtotal()
        }
        return total
    }

    /**
     * Función que calcula el monto final a pagar, aplicando la lógica de la propina.
     */
    fun calcularTotalFinal(): Double {
        val totalSinPropina = calcularTotalSinPropina()

        if (propinaActivada) {
            val montoPropina = totalSinPropina * PORCENTAJE_PROPINA
            return totalSinPropina + montoPropina
        }

        return totalSinPropina
    }
}