fun main(args: Array<String>) {

    var bdd = GestorBDD()

    do{
        println("********Examen********")
        println("1. Sistemas Operativos")
        println("2. Aplicaciones")
        println("3. Salir")

        val menu = readln().toInt()

        when (menu){
            1 -> {
                do {
                    println("\n ********Sistemas Operativos********")

                    println("1. Crear Sistema Operativo (create)")
                    println("2. Buscar Sistema Operativo (read)")
                    println("3. Actualizar Sistema Operativo (update)")
                    println("4. Eliminar Sistema Operativo (delete)")
                    println("5. Regresar")

                    val menuSO = readln().toInt()
                    var listaSistema = bdd.read()

                    when (menuSO) {
                        //Create SO
                        1 -> {
                            val so = SistemaOperativo()
                            so.createSistema()
                            listaSistema.add(so)

                            bdd.write(listaSistema)
                            println("SO creado\n")
                        }

                        //Read SO
                        2->{
                            println("ID del SO: ")
                            val idSistema = readln().toInt()
                            val so = SistemaOperativo().readSistema(idSistema, listaSistema)

                            println("El SO es: \t")
                            print(so.toString())
                        }

                        //Update SO
                        3->{
                            println("ID del SO: ")
                            val idSistema = readln().toInt()
                            val so = SistemaOperativo().readSistema(idSistema, listaSistema)
                            val listasSO = SistemaOperativo().updateSistema(idSistema, listaSistema)

                            bdd.write(listaSistema)
                            println("SO actualizado\n")
                        }

                        //Delete SO
                        4->{
                            println("ID del SO: ")
                            val idSistema = readln().toInt()
                            val so = SistemaOperativo().readSistema(idSistema, listaSistema)

                            bdd.write(listaSistema)
                            println("SO eliminado\n")
                        }

                        //Opcion Regresar
                        5 -> {
                            val menu = 4
                        }
                    }
                } while (menuSO != 5)
            }

            2 -> {
                var listaSistema = bdd.read()

                println("\n Ingrese el ID del SO: ")
                val idSistema = readln().toInt()
                val so = SistemaOperativo().readSistema(idSistema, listaSistema)
                val indSO = (so.idSistema)-1

                do{
                    println("\n ********Aplicaciones********")
                    println("1. Crear Aplicacion (create)")
                    println("2. Buscar Aplicacion (read)")
                    println("3. Actualizar Aplicacion (update)")
                    println("4. Eliminar Aplicacion (delete)")
                    println("5. Regresar")

                    val menuApp = readln().toInt()

                    when(menuApp){
                        //Create Aplicacion
                        1->{
                            val app = Aplicacion()
                            app.createAplicacion()
                            listaSistema[indSO].listaAplicaciones.add(app)
                            bdd.write(listaSistema)
                            println("Aplicacion creada\n")
                        }

                        //Read Aplicacion
                        2->{
                            println("ID de Aplicacion: ")
                            val idAplicacion = readln().toInt()
                            val app = Aplicacion().readAplicacion(idAplicacion,
                                listaSistema[indSO].listaAplicaciones)
                            println(app.toString())
                            println("Aplicacion encontrada\n")
                        }

                        //Update Aplicacion
                        3->{
                            println("ID de Aplicacion: ")
                            val idAplicacion = readln().toInt()
                            val app = Aplicacion().updateAplicacion(idAplicacion, listaSistema[indSO].listaAplicaciones)
                            bdd.write(listaSistema)
                            //val auxListViviendas = Aplicacion().updateAplicacion(idAplicacion, listaSistema)
                            println("Aplicacion actualizada\n")
                        }

                        //Delete Aplicacion
                        4->{
                            println("ID de Aplicacion: ")
                            val idAplicacion = readln().toInt()
                            val app = Aplicacion().deleteAplicacion(idAplicacion,
                                listaSistema[indSO].listaAplicaciones)
                            bdd.write(listaSistema)
                            println("Aplicacion eliminada\n")
                        }

                        //Opcion Regresar
                        5->{
                            val menu = 4
                        }
                    }
                }while (menuApp!=5)
            }
        }

    }while (menu != 2)

}