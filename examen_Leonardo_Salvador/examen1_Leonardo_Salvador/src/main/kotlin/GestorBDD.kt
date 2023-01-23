import java.io.File
import java.io.InputStream
import java.time.LocalDate

class GestorBDD {

    //Leer Archivos

    fun read():ArrayList<SistemaOperativo>{
        val inputStream: InputStream = File("C:\\Users\\salva\\Desktop\\AplicacionesMovilesRepos\\salvador-guzman-byron-leonardo-movsoft\\examen_Leonardo_Salvador\\examen1_Leonardo_Salvador\\src\\main\\resources\\BDD.txt").inputStream()
        val sentencias = mutableListOf<String>()
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                sentencias.add(it)
            }
        }

        var listaSistema = ArrayList<SistemaOperativo>()
        var indSistema = 0

        sentencias.forEach{ linea: String->
            val indicador = listOf(*linea.split("\\s*;\\s*".toRegex()).toTypedArray())

            if(indicador[0].equals("Sistema Operativo")){
                val so = SistemaOperativo()
                so.idSistema = indicador[2].toInt()
                so.nombreSistema = indicador[4]
                so.tieneLicencia = indicador[6].toBoolean()
                so.precioSistema = indicador[8].toDouble()
                so.fechaDeInstalacion = LocalDate.parse(indicador[10])

                listaSistema.add(so)
                indSistema = listaSistema.indexOf(so)
            }else{
                if(indicador[0].equals("Aplicacion") ){
                    val app = Aplicacion()
                    app.idAplicacion = indicador[2].toInt()
                    app.nombreAplicacion = indicador[4]
                    app.esGratuita = indicador[6].toBoolean()
                    app.precioAplicacion = indicador[8].toDouble()
                    app.fechaCreacion = LocalDate.parse(indicador[10])

                    listaSistema[indSistema].listaAplicaciones.add(app)
                }
            }
        }
        return listaSistema
    }

    //Escribir en Archivos
    fun write(texto:ArrayList<SistemaOperativo>){

        val archivo = File("C:\\Users\\salva\\Desktop\\AplicacionesMovilesRepos\\salvador-guzman-byron-leonardo-movsoft\\examen_Leonardo_Salvador\\examen1_Leonardo_Salvador\\src\\main\\resources\\BDD.txt")
        archivo.writeText("")
        texto.forEach { sistema: SistemaOperativo ->
            archivo.appendText("Sistema Operativo; ")
            archivo.appendText("Id Sistema Operativo;"+sistema.idSistema
                    + " ;Nombre del SO;" + sistema.nombreSistema
                    +" ;Tiene licencia?;"+sistema.tieneLicencia
                    +" ;Precio del SO;"+sistema.precioSistema
                    +" ;Fecha de instalacion del SO;"+sistema.fechaDeInstalacion
                    +"\n")

            sistema.listaAplicaciones.forEach { aplicacion: Aplicacion ->
                archivo.appendText("Aplicaciones; ")
                archivo.appendText("Id de Aplicacion;"+aplicacion.idAplicacion
                        +" ;Nombre de Aplicacion;"+aplicacion.nombreAplicacion
                        +" ;Es gratuita?;"+aplicacion.esGratuita
                        +" ;Precio de Aplicacion;"+aplicacion.precioAplicacion
                        +" ;Fecha de Creacion de Aplicacion;"+aplicacion.fechaCreacion
                        +"\n")
            }
        }
    }

}