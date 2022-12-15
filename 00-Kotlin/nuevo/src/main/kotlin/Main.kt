import java.util.*

fun main() {
    println("Hola")

//Tipos de variables
//IMNUTABLES (NO Re Asignar) =
    val inmutable: String = "Leo";
//inmutable = "Byron"; // No se puede re asignar

//MUTABLE (Re Asignar) =
    var mutable: String = "Byron";
    mutable = "Leo";

//val > var

    //Sintaxis Duck typing
    //Comilla doble para string
    //Comilla simple para char

    val ejemploVariable = "Ejemplo"
    val edadEjemplo: Int = 12;
    ejemploVariable.trim()

    //Variables primitivas
    val nombreProfesor: String = "Leo Salvador"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true
    //Clases JAVA
    val fechaNacimiento: Date = Date()

    //Condicionales
    if (true) {
    } else {
        println("hola")
    }


    //Switch no existe
    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("acercarse")
        }

        "C" -> {
            println("alejarse")
        }

        "UN" -> println("hablar")
        else -> println("o reconocido")
    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"

    val sumaUno = Suma(1, 1);
    val sumaDos = Suma(null, 1);
    val sumaTres = Suma(1, null);
    val sumaCuatro = Suma(null, null);

    sumaUno.sumar()
    sumaDos.sumar()

    sumaTres.sumar()
    sumaCuatro.sumar()

    Suma.pi
    Suma.elevarAlCuadrado(2)
    Suma.historialsumas


// Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

// Avregto Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 8, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

// ***************Operadores -> Sirven para arreglos estáticos y dinámicos********


// FOR EACH -> Unit
// Iterar un arreglo
    println("-------------------Operador FOR EACH-------------------------")

    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual:  ${valorActual}")
        }
    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")

        }
    println(respuestaForEach)

    println("---------------------------------------------------------")



    println("-------------------Operador MAP-------------------------")

// MAP -> Muta el arregto (Cambio el arreglo)
// 1) Enviemos el nuevo valor de la iteracion
// 2) Nos devuelve es un NUEVO ARREGLO con Los valores modificodos

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 108.60
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }
        .map { valorActual: Int ->
            return@map valorActual + 15
        }

    println(respuestaMapDos)

    println("---------------------------------------------------------")


    println("-------------------Operador FILTER-------------------------")

// Filter -> FILTRAR EL ARREGLO
// 1) Devolver una expresion (TRUE 0 FALSE)
// 2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5// Expresion Condicion
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)
    println("---------------------------------------------------------")



    println("-------------------Operador OR -- AND-------------------------")

    //OR AND
    //OR -> ANY (ALGUNO CUMPLE?)
    //AND -> ALL (TODOS CUMPLEN)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaALL: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaALL) //false

    println("---------------------------------------------------------")



    println("-------------------Operador REDUCE-------------------------")

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en Lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    //valorIteracion1 = valorEmpieza + 1 = 0+1 -> = 1 -> Iteracion 1
    //valorIteracion2 = valorIteracion1 + 2 = 1+ 2 = 3 -> Iteracion 2
    //valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    //valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    //valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5


    val respuestaReduce: Int = arregloDinamico
        .reduce { //acumulado (contador) = 0 -> siempre empieza en 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica del negocio
        }
    println(respuestaReduce) //78

    println("---------------------------------------------------------")


    //************************************************************************************


    //Funciones
    //void imprimirNombre(String nombre){}
    //Unit == void

    fun imprimirNombre(nombre: String): Unit {
        println("nombre:  ${nombre}");
    }

    fun calcularSueldo(sueldo: Double, tasa: Double = 12.00, bonoEspecial: Double? = null): Double {
        //String -> String?
        //Int -> Int?
        //Date -> Date?
        if (bonoEspecial == null) {
            return sueldo * (100 / tasa)
        } else {
            return sueldo * (100 / tasa) + bonoEspecial
        }
    }

}

//Creacion de clases
abstract class NumerosJava {
    protected val numeroUno: Int;
    private val numeroDos: Int;


    constructor(uno: Int, dos: Int) {
        //Bloque codigo constructor
        this.numeroUno = uno;
        this.numeroDos = dos;
        println("Inicializado");
    }
}


abstract class Numeros( // Constructor Primario
//uno: Int, // Parametro
//public var uno: Int, // Propiedad de la clase publica
//var uno: Int, // Propiedad de la clase publica
    protected val numeroUno: Int, // Propiedad de La clase protected
    protected val numeroDos: Int // Propiedad de la clase protected
) {

    //  protected val numeroUno: Int
    //  var cedula: String = "";
    //  public var cedula: String = "";

    init { // Bloque codigo constructor PRIMARIO
        //this.numeroUno = uno
        this.numeroUno
        numeroUno
        this.numeroDos
        numeroDos
        println("Inicializado")
    }
}


//Nuleable en el primer parametro
class Suma( // Constructor Primario Suma
    uno: Int, // Parametro
    dos: Int // Parametro
) : Numeros(uno, dos) {
    init { // Bloque constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor(// Segundo constructor
        uno: Int?,
        dos: Int // parametros
    ) : this( // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    ) {

    }

    //Nuleable el segundo
    constructor(// tercer constructor
        uno: Int, // parametros
        dos: Int? // parametros
    ) : this( // Llamada constructor primario
        uno,
        if (dos == null) 0 else dos
    ) {
    }

    //Dos Nuleables
    constructor(// cuarto constructor
        uno: Int?, // parametros
        dos: Int? // parametros
    ) : this( // Llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos
    ) {
    }

    public fun sumar(): Int {   //public no es necesario poner
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object { // Atributos y Metodos "Compartidos”entre las instancias .
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialsumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int) {
            historialsumas.add(valorNuevaSuma)
        }
    }
}



