fun main(args: Array<String>) {

    var bdd = GestorBDD()

    do{
        println("Examen")
        println("1. Sistemas Operativos")
        println("2. Aplicaciones")
        println("3. Salir")
        val menu = readln().toInt()
        when (menu){
            1 -> {
                do {
                    println("Sistemas Operativos")

                    //println("MODULO VIVIENDA")
                    println("1. Crear Sistema Operativo (create)")
                    println("2. Buscar Vivienda (read)")
                    println("3. Actualizar Vivienda (update)")
                    println("4. Eliminar Vivienda (delete)")
                    //println("5. Ingresar al modulo de Servicios")
                    println("6. Regresar")

                    val respuestaSistema = readln().toInt()
                    var listaSistema = bdd.read()

                    when (respuestaSistema) {
                        1 -> {//Crear vivienda
                            val so = SistemaOperativo()
                            so.createSistema()
                            listaSistema.add(so)
                            bdd.write(listaSistema)
                            println("¡Vivienda creada con EXITO!\n") //cambiar
                        }
                        2->{//Buscar vivienda
                            println("ID de la vivienda: ")
                            val idSistema = readln().toInt()
                            val so = SistemaOperativo().readSistema(idSistema, listaSistema)

                            println("¡Vivienda encontrada con EXITO!\n") //cambiar mensajes
                            println(so.toString())
                        }

                        3->{//Actualizar vivienda
                            println("ID de la vivienda: ")
                            val idSistema = readln().toInt()
                            val so = SistemaOperativo().readSistema(idSistema, listaSistema)

                            bdd.write(listaSistema)
                            println("¡Vivienda actualizada con EXITO!\n") //cambiar print
                        }

                        4->{//Eliminar vivienda
                            println("ID de la vivienda: ")
                            val idSistema = readln().toInt()
                            val so = SistemaOperativo().readSistema(idSistema, listaSistema)
                            bdd.write(listaSistema)
                            println("¡Vivienda eliminada con EXITO!\n")
                        }
                        5->{//Modulo SERVICIOS

                        }
                    }
                } while (respuestaSistema != 6)
            }

            2 -> {
                var listaSistema = bdd.read()
                println("ID de la vivienda: ")
                val idSistema = readln().toInt()
                val so = SistemaOperativo().readSistema(idSistema, listaSistema)

                val indSO = (so.idSistema)-1

                do{
                    println("\nMODULO Aplicacion")
                    println("1. Crear Servicio")
                    println("2. Buscar Servicio")
                    println("3. Actualizar Servicio")
                    println("4. Eliminar Servicio")
                    println("5. Salir")
                    val respuestaServicios = readln().toInt()

                    listaSistema = bdd.read()

                    when(respuestaServicios){
                        1->{ //Crear Servicio
                            val app = Aplicacion()
                            app.createAplicacion()
                            listaSistema[indSO].listaAplicaciones.add(app)
                            bdd.write(listaSistema)
                            println("¡Servicio creado con EXITO!\n")
                        }
                        2->{ //Buscar Servicio
                            println("ID del Servicio: ")
                            val idAplicacion = readln().toInt()
                            val app = Aplicacion().readAplicacion(idAplicacion,
                                listaSistema[indSO].listaAplicaciones)
                            println(app.toString())
                            println("¡Servicio encontrado con EXITO!\n")
                        }
                        3->{//Actualizar Servicio
                            println("ID del Servicio: ")
                            val idAplicacion = readln().toInt()
                            val app = Aplicacion().updateAplicacion(idAplicacion,
                                listaSistema[indSO].listaAplicaciones)
                            bdd.write(listaSistema)
                            println("¡Servicio actualizado con EXITO!\n")
                        }
                        4->{//Eliminar Servicio
                            println("ID del Servicio: ")
                            val idAplicacion = readln().toInt()
                            val app = Aplicacion().deleteAplicacion(idAplicacion,
                                listaSistema[indSO].listaAplicaciones)
                            bdd.write(listaSistema)
                            println("¡Servicio eliminado con EXITO!\n")
                        }
                    }
                }while (respuestaServicios!=5)
            }
        }
    }while (menu != 2)




}