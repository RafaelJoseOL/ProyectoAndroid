package com.rjol.proyectoandroid.Model

import java.io.Serializable

data class Book(var nombre: String = "",
                var fechaLanzamiento: String = "",
                var precio: String = "",
                var imagen : String = "",
                var idBook : String = "",
                var idSaga : String = "") : Serializable {
    constructor() : this("", "", "", "", "", "")
}