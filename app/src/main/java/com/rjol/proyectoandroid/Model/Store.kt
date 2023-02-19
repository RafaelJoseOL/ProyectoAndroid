package com.rjol.proyectoandroid.Model

data class Store(var nombre: String = "",
                var url: String = "",
                var imagen : String = "") {
    constructor() : this("", "", "")
}