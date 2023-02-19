package com.rjol.proyectoandroid.Model

data class Saga(var nombre: String = "",
                  var genero: String = "",
                  var numeroLibros: String = "",
                  var idEscritor : String = "",
                  var idSaga : String = "") : java.io.Serializable {
    constructor() : this("", "", "", "", "")
}