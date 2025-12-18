package com.example.mirestauran

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 1. Definición de Constantes de Precios
    private val PRECIO_PASTEL_CHOCLO = 12000.0
    private val PRECIO_CAZUELA = 10000.0

    // 2. Declaración de Variables para las Vistas (Views)
    private lateinit var etCantidadPastel: EditText
    private lateinit var etCantidadCazuela: EditText
    private lateinit var tvSubtotalPastel: TextView
    private lateinit var tvSubtotalCazuela: TextView
    private lateinit var swPropina: Switch
    private lateinit var tvTotalSinPropina: TextView
    private lateinit var tvTotalAPagar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 3. Enlace de Vistas (Link XML IDs to Kotlin Variables)
        enlazarVistas()

        // 4. Configuración de Listeners para el Cálculo Dinámico
        configurarListeners()
    }

    private fun enlazarVistas() {
        // Enlace de los campos de cantidad
        etCantidadPastel = findViewById(R.id.et_cantidad_pastel)
        etCantidadCazuela = findViewById(R.id.et_cantidad_cazuela)

        // Enlace de los TextViews de subtotales
        tvSubtotalPastel = findViewById(R.id.tv_subtotal_pastel)
        tvSubtotalCazuela = findViewById(R.id.tv_subtotal_cazuela)

        // Enlace del Switch de propina
        swPropina = findViewById(R.id.sw_propina)

        // Enlace de los TextViews de totales
        tvTotalSinPropina = findViewById(R.id.tv_total_sin_propina)
        tvTotalAPagar = findViewById(R.id.tv_total_a_pagar)
    }

    /**
     * Configura los listeners para que la aplicación calcule el total
     * cada vez que una cantidad o el switch de propina cambie.
     */
    private fun configurarListeners() {
        // Listener para los cambios de texto en Pastel de Choclo
        etCantidadPastel.addTextChangedListener(crearTextWatcher())

        // Listener para los cambios de texto en Cazuela
        etCantidadCazuela.addTextChangedListener(crearTextWatcher())

        // Listener para los cambios del Switch de propina
        swPropina.setOnCheckedChangeListener { _, _ ->
            calcularTotal()
        }
    }

    /**
     * Función que crea un TextWatcher simple para llamar a calcularTotal()
     * después de que el texto en un EditText cambie.
     */
    private fun crearTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calcularTotal() // *Punto clave para el cálculo dinámico*
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    /**
     * Función principal que realiza todos los cálculos del pedido.
     */
    private fun calcularTotal() {
        // 1. Obtener y parsear las cantidades ingresadas. Usa 'toIntOrNull' para manejar campos vacíos (devuelve 0).
        val cantidadPastel = etCantidadPastel.text.toString().toIntOrNull() ?: 0
        val cantidadCazuela = etCantidadCazuela.text.toString().toIntOrNull() ?: 0

        // 2. Calcular los subtotales
        val subtotalPastel = cantidadPastel * PRECIO_PASTEL_CHOCLO
        val subtotalCazuela = cantidadCazuela * PRECIO_CAZUELA

        // 3. Actualizar los TextViews de Subtotales
        tvSubtotalPastel.text = "$ ${formatoMoneda(subtotalPastel)}"
        tvSubtotalCazuela.text = "$ ${formatoMoneda(subtotalCazuela)}"

        // 4. Calcular Total Sin Propina
        val totalSinPropina = subtotalPastel + subtotalCazuela
        tvTotalSinPropina.text = "$ ${formatoMoneda(totalSinPropina)}"

        // 5. Aplicar Propina si el Switch está activado
        var totalFinal = totalSinPropina
        if (swPropina.isChecked) {
            val montoPropina = totalSinPropina * 0.10
            totalFinal += montoPropina
        }

        // 6. Mostrar el Resultado Final
        tvTotalAPagar.text = "$ ${formatoMoneda(totalFinal)}"
    }

    /**
     * Función de utilidad para dar formato a la moneda (sin decimales para simplificar).
     * Puedes usar NumberFormat para una solución más robusta si lo exige el profesor.
     */
    private fun formatoMoneda(monto: Double): String {
        return monto.toInt().toString() // Convierte a entero y luego a String (ej: 12000)
    }
}