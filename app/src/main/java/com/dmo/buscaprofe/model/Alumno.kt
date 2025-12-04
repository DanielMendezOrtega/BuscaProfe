package com.dmo.buscaprofe.model

class Alumno (

    nombre: String? = null,
    email: String? = null,
    edad: Int? = null,
    dni: String? = null,
    telefono: String? = null,
    fechaRegistro: String? = null,
    ubicacion: String? = null,
    imagenUrl: String? = null,
    var asignaturasAyuda: List<String>? = null
//cuando una clase hereda de otra , puede llamar al constructor de la clase padre
): Usuario(
    nombre,
    email,
    edad,
    "Alumno",
    dni,
    telefono,
    fechaRegistro,
    ubicacion,
    imagenUrl){

    constructor(): this("","",0,"","","","","",emptyList())
}