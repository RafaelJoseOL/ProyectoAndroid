package com.rjol.proyectoandroid.Model

data class Store(var nombre: String = "",
                var descuento: String = "",
                var imagen : String = "") {
    constructor() : this("", "", "")
}