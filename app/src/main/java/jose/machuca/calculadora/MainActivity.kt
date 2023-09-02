package jose.machuca.calculadora

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Declaración de variables para guardar información y controlar las operaciones
    private var oper: Int = 0 // Variable para saber el tipo de operación (suma, resta, mult, div)
    private var numero1: Double = 0.0 // Variable para almacenar el primer número en la operación

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Configura el diseño de la actividad
    }
    fun limpiar(view: View) {// Función para limpiar los txt
        val tv_num1: TextView = findViewById(R.id.tv_num1)
        val tv_num2: TextView = findViewById(R.id.tv_num2)
        tv_num1.text = "0"
        tv_num2.text = "0"
    }


    // Función para manejar los botones numéricos y el punto decimal
    fun presionar(view: View) {
        val tv_num2: TextView = findViewById(R.id.tv_num2) // Accede al TextView donde se muestra el número
        val num2: String = tv_num2.text.toString() // Obtiene el contenido actual del TextView
        val btnText = (view as TextView).text.toString() // Obtiene el texto del botón presionado

        // Verifica si el número actual contiene un operador (+, -, *, /)
        val contieneOperador = num2.contains("+") || num2.contains("-") || num2.contains("*") || num2.contains("/")

        // Decide si reemplazar el número actual o agregar el número del botón
        val nuevoNum2 = if (contieneOperador) btnText else num2 + btnText
        tv_num2.text = nuevoNum2 // Actualiza el contenido del TextView
    }

    // Función para manejar los botones de operaciones (+, -, *, /)
    fun Operaciones(view: View) {
        val tv_num2: TextView = findViewById(R.id.tv_num2) // Accede al TextView donde se muestra el número
        val num2_text: String = tv_num2.text.toString() // Obtiene el contenido actual del TextView
        val num2 = try {
            num2_text.toDouble() // Convierte el contenido en un número decimal
        } catch (e: NumberFormatException) {
            0.0 // Maneja errores si el contenido no es un número
        }

        // Guarda el valor de num2 antes de borrar el txt
        numero1 = num2

        tv_num2.text = "0" // Borra el TextView para ingresar un nuevo número
        var operador = ""
        when (view.id) {
            R.id.btnMAS -> operador = "+" // Determina el tipo de operación según el botón presionado
            R.id.btnMENOS -> operador = "-"
            R.id.btnMULT -> operador = "*"
            R.id.btnSLASH -> operador = "/"
        }

        val tv_num1: TextView = findViewById(R.id.tv_num1) // Accede al txt superior para mostrar la operación
        tv_num1.text = "$num2_text $operador" // Actualiza el txt con la operación realizada
        oper = when (view.id) {
            R.id.btnMAS -> 1
            R.id.btnMENOS -> 2
            R.id.btnMULT -> 3
            R.id.btnSLASH -> 4
            else -> 0
        }
    }

    // Función para  igual =
    fun igual(view: View) {
        val tv_num2: TextView = findViewById(R.id.tv_num2) // Accede al txt donde se muestra el número
        val num2_text: String = tv_num2.text.toString() // Obtiene el contenido actual del txt
        val num2 = try {
            num2_text.toDouble() // Convierte el contenido en un número decimal
        } catch (e: NumberFormatException) {
            0.0 // Maneja errores si el contenido no es un numero
        }

        val tv_num1: TextView = findViewById(R.id.tv_num1) // Accede al txt superior para mostrar la operación
        val operador = tv_num1.text.toString().takeLast(1).trim() // Obtiene el último carácter, que es el operador

        // Realizar  operación segun el tipo de operación almacenado en 'oper'
        val resultado: Double = when (oper) {
            1 -> numero1 + num2 // Suma
            2 -> numero1 - num2 // Resta
            3 -> numero1 * num2 // Multiplicacion
            4 -> if (num2 != 0.0) numero1 / num2 else 0.0 // Division con manejo de division por cero
            else -> 0.0
        }

        tv_num2.text = resultado.toString() // Muestra el resultado en el txt inferior
        tv_num1.text = "" // Borra la operación mostrada
        numero1 = resultado // Actualiza el valor de numero1 con el resultado
        oper = 0 // Reinicia el tipo de operación
    }
}
