package com.rjol.proyectoandroid.Model

data class Book(var nombre: String = "",
                var fechaLanzamiento: String = "",
                var precio: String = "",
                var imagen : String = "") {
    constructor() : this("", "", "")
}