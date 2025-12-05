package com.dmo.buscaprofe.model

data class Alumno(

    var nombre: String = "",
    var email: String = "",
    var edad: Int = 0,
    var tipo: String = "Alumno",
    var dni: String = "",
    var telefono: String = "",
    var fechaRegistro: String = "",
    var ubicacion: String = "",
    var imagenUrl: String = "",
    var asignaturasAyuda: List<String> = emptyList()
)

