package com.dmo.buscaprofe.model

data class Profesor(
    var nombre: String = "",
    var email: String = "",
    var edad: Int = 0,
    var tipo: String = "Profesor",
    var dni: String = "",
    var telefono: String = "",
    var fechaRegistro: String = "",
    var ubicacion: String = "",
    var imagenUrl: String = "",
    var asignaturasImpartir: List<String>? = null
)