package com.example.blsg_app

class BBaseDatosMemoria {

    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Leo", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Byron", "f@f.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Daniel", "n@n.com")
                )
        }


    }

}