package com.rjol.proyectoandroid.Model

data class Writer(var nombre: String = "",
                  var apellido: String = "",
                  var paginaWeb: String = "",
                  var editorial: String = "",
                  var imagen : String = "") {
    constructor() : this("", "", "", "")
}
