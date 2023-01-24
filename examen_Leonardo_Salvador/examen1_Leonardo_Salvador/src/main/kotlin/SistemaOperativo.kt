import java.time.LocalDate

class SistemaOperativo {

    var idSistema: Int = 0
    var nombreSistema:String = ""
    var tieneLicencia: Boolean = false
    var precioSistema: Double = 0.0
    var fechaDeInstalacion = LocalDate.parse("1999-06-21")

    var listaAplicaciones = arrayListOf<Aplicacion>()

    //Create SO
    fun createSistema() {
        println("ID del SO")
        val idSO = readln().toInt()
        this.idSistema = idSO

        println("Nombre del SO")
        val nombreSO = readln()
        this.nombreSistema = nombreSO

        println("Tiene licencia?")
        val aux = readln()
        if(aux.equals("S") || aux.equals("s")){
            this.tieneLicencia = true
        }else {
            (aux.equals("N") || aux.equals("n"))
            this.tieneLicencia = false
        }

        println("Precio del SO")
        val precioSO = readln().toDouble()
        this.precioSistema = precioSO


        println("Fecha de Instalación del SO")
        val fechaInsSO = LocalDate.parse(readln())
        this.fechaDeInstalacion = fechaInsSO



    }

    //Read SO
    fun readSistema(idSistema:Int, listaSistema: ArrayList<SistemaOperativo>):SistemaOperativo{
        var so = SistemaOperativo()
        listaSistema.forEach{
                sistema:SistemaOperativo ->
            if(sistema.idSistema.equals(idSistema)){
                so =  sistema
            }
        }
        return so
    }


    //Update SO
    fun updateSistema(idSistema:Int, listaSistema: ArrayList<SistemaOperativo>):ArrayList<SistemaOperativo>{

        //Buscar SO
        var so = SistemaOperativo()
        var nuevoSO = so.readSistema(idSistema, listaSistema)
        var indSO = (so.readSistema(idSistema, listaSistema).idSistema)-1
        println(nuevoSO.toString())
        //Actualizar
        do {
            println("1. Nombre SO")
            println("2. Tiene licencia?")
            println("3. Precio del SO")
            println("4. Fecha de Instalación del SO")
            println("5. Guardar")
            val opc = readln().toInt()


            when(opc) {
                1 -> {
                    println("Nombre del SO")
                    val nombreSO = readln()
                    nuevoSO.nombreSistema = nombreSO
                    listaSistema[indSO] = nuevoSO
                }

                2 -> {
                    println("Tiene licencia?")
                    val aux = readln()
                    if(aux.equals("S") || aux.equals("s")){
                        nuevoSO.tieneLicencia = true
                    }else {
                        (aux.equals("N") || aux.equals("n"))
                        nuevoSO.tieneLicencia = false
                    }
                    listaSistema[indSO] = nuevoSO
                }

                3 -> {
                    println("Precio del SO")
                    val precioSO = readln().toDouble()
                    nuevoSO.precioSistema = precioSO
                    listaSistema[indSO] = nuevoSO
                }

                4 -> {
                    println("Fecha de Instalación del SO")
                    val fechaInsSO = LocalDate.parse(readln())
                    nuevoSO.fechaDeInstalacion = fechaInsSO
                    listaSistema[indSO] = nuevoSO
                }


            }
        }while (opc!=5);
        return listaSistema
    }

    //Delete SO
    fun deleteSistema(idSistema:Int, listaSistema: ArrayList<SistemaOperativo>):ArrayList<SistemaOperativo>{
        var so = SistemaOperativo()
        var nuevoSO = so.readSistema(idSistema, listaSistema)
        var indSO = (so.readSistema(idSistema, listaSistema).idSistema)-1
        listaSistema.removeAt(indSO)
        return listaSistema
    }



    //ToString

    override fun toString(): String {
        return "SistemaOperativo(idSistema=$idSistema, nombreSistema='$nombreSistema', tieneLicencia=$tieneLicencia, " +
                "precioSistema=$precioSistema, fechaDeInstalacion=$fechaDeInstalacion, listaAplicaciones=$listaAplicaciones)"
    }



}