package com.dmo.buscaprofe.model

//la clase open es para que otras clases hereden de ella los atributos
open class Usuario(
    nombre1: String?,
    email1: String?,
    edad1: Int?,
    string: String,
    dni1: String?,
    telefono1: String?,
    fechaRegistro1: String?,
    ubicacion1: String?,
    imagenUrl1: String?
) {
    var nombre: String? = null
    var email: String? = null
    var edad: Int? = null
    var tipoUsuario: String? = null
    var dni: String? = null
    var telefono: String? = null
    var fechaRegistro: String? = null // formato ISO o timestamp
    var ubicacion: String? = null
    var imagenUrl: String? = null // URL de la imagen en Firebase Storage
}