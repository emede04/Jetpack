package com.example.lazy_peliculas.ui.theme

sealed class pantallazo(   val ruta:String
) {
    object inicio : pantallazo("inicio")
    object info : pantallazo("info/{nombre}") {

        fun decaminoa(nombre: String) = "info/$nombre"
    }
}
