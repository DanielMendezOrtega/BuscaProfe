package com.dmo.buscaprofe.model

class Profesor(
    nombre: String? = null,
    email: String? = null,
    edad: Int? = null,
    dni: String? = null,
    telefono: String? = null,
    fechaRegistro: String? = null,
    ubicacion: String? = null,
    imagenUrl: String? = null,
    var asignaturasImpartir: List<String>? = null
) : Usuario(
    nombre,
    email,
    edad,
    "Profesor",
    dni,
    telefono,
    fechaRegistro,
    ubicacion,
    imagenUrl)