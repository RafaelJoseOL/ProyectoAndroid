package com.rjol.proyectoandroid.Model

data class Saga(var nombre: String = "",
                  var genero: String = "",
                  var numeroLibros: String = "") {
    constructor() : this("", "", "")
}