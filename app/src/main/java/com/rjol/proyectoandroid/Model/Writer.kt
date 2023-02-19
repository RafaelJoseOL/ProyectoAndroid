package com.rjol.proyectoandroid.Model

import java.io.Serializable

data class Writer(var nombre: String = "",
                  var apellido: String = "",
                  var paginaWeb: String = "",
                  var editorial: String = "",
                  var imagen : String = "",
                  var idEscritor : String = "") : Serializable {
    constructor() : this("", "", "", "", "", "")
}
