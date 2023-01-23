import java.time.LocalDate

class Aplicacion {

        var idAplicacion: Int = 0
        var nombreAplicacion:String = ""
        var esGratuita: Boolean = false
        var precioAplicacion: Double = 0.0
        var fechaCreacion = LocalDate.parse("1999-06-21")



        //Create APP
        fun createAplicacion() {
            println("ID de Aplicacion")
            val idApp = readln().toInt()
            this.idAplicacion = idApp

            println("Nombre de Aplicacion")
            val nombreApp = readln()
            this.nombreAplicacion = nombreApp

            println("Es gratuita?")
            val aux = readln()
            if(aux.equals("S") || aux.equals("s")){
                this.esGratuita = true
            }else {
                (aux.equals("N") || aux.equals("n"))
                this.esGratuita = false
            }

            println("Precio de Aplicacion")
            val precioApp = readln().toDouble()
            this.precioAplicacion = precioApp


            println("Fecha de Creacion de Aplicacion")
            val fechaCreaApp = LocalDate.parse(readln())
            this.fechaCreacion = fechaCreaApp



        }

        //Read SO
        fun readAplicacion(idAplicacion:Int, listaAplicacion: ArrayList<Aplicacion>):Aplicacion{
            var app = Aplicacion()
            listaAplicacion.forEach{
                    aplicacion:Aplicacion ->
                if(aplicacion.idAplicacion.equals(idAplicacion)){
                    app =  aplicacion
                }
            }
            return app
        }


        //Update Aplicacion
        fun updateAplicacion(idAplicacion:Int, listaAplicacion: ArrayList<Aplicacion>):ArrayList<Aplicacion>{

            //Buscar vivienda para actualizar
            var app = Aplicacion()
            var nuevaApp = app.readAplicacion(idAplicacion, listaAplicacion)
            var indSO = (app.readAplicacion(idAplicacion, listaAplicacion).idAplicacion)-1
            println(nuevaApp.toString())

            //Actualizar
            do {
                println("1. Nombre Aplicacion")
                println("2. Es gratuita?")
                println("3. Precio de Aplicacion")
                println("4. Fecha de Creacion de Aplicacion")
                println("5. Guardar")
                val opc = readln().toInt()


                when(opc) {
                    1 -> {
                        println("Nombre de Aplicacion")
                        val nombreApp = readln()
                        nuevaApp.nombreAplicacion = nombreApp
                        listaAplicacion[indSO] = nuevaApp
                    }

                    2 -> {
                        println("Es gratuita?")
                        val aux = readln()
                        if(aux.equals("S") || aux.equals("s")){
                            nuevaApp.esGratuita = true
                        }else {
                            (aux.equals("N") || aux.equals("n"))
                            nuevaApp.esGratuita = false
                        }
                        listaAplicacion[indSO] = nuevaApp
                    }

                    3 -> {
                        println("Precio de Aplicacion")
                        val precioApp = readln().toDouble()
                        nuevaApp.precioAplicacion = precioApp
                        listaAplicacion[indSO] = nuevaApp
                    }

                    4 -> {
                        println("Fecha de Creacion de Aplicacion")
                        val fechaCreaApp = LocalDate.parse(readln())
                        nuevaApp.fechaCreacion = fechaCreaApp
                        listaAplicacion[indSO] = nuevaApp
                    }


                }
            }while (opc!=5);
            return listaAplicacion
        }

        //Delete Aplicacion
        fun deleteAplicacion(idAplicacion:Int, listaAplicacion: ArrayList<Aplicacion>):ArrayList<Aplicacion>{
            var app = Aplicacion()
            var nuevaApp = app.readAplicacion(idAplicacion, listaAplicacion)
            var indSO = (app.readAplicacion(idAplicacion, listaAplicacion).idAplicacion)-1
            listaAplicacion.removeAt(indSO)
            return listaAplicacion
        }

    //ToString
    override fun toString(): String {
        return "Aplicacion(idAplicacion=$idAplicacion, nombreAplicacion='$nombreAplicacion', esGratuita=$esGratuita, " +
                "precioAplicacion=$precioAplicacion, fechaCreacion=$fechaCreacion)"
    }










}