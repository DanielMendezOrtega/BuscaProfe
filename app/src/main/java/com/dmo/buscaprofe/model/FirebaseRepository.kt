package com.dmo.buscaprofe.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.io.Console


class FirebaseRepository {
    private val auth= FirebaseAuth.getInstance()
    private val db= FirebaseDatabase.getInstance()

    //esta funcion aceptarra callbacks para exito y error
    fun registrarAlumno(alumno: Alumno, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val uid = FirebaseAuth.getInstance().uid ?: return
        Log.d("prueba",alumno.nombre.toString())
        Log.d("FirebaseRepo", "Guardando alumno con UID: $uid")
        FirebaseDatabase.getInstance().getReference("Usuarios/Alumnos")
            .child(uid)
            .setValue(alumno)
            .addOnSuccessListener {
                Log.d("FirebaseRepo", "Alumno guardado correctamente")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("FirebaseRepo", "Error al guardar alumno: ${e.message}")
                onError(e)
            }
    }

    fun registrarProfesor(profesor: Profesor, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val uid = FirebaseAuth.getInstance().uid ?: return
        Log.d("prueba",profesor.nombre.toString())
        Log.d("FirebaseRepo", "Guardando profe con UID: $uid")
        FirebaseDatabase.getInstance().getReference("Usuarios/Profesor")
            .child(uid)
            .setValue(profesor)
            .addOnSuccessListener {
                Log.d("FirebaseRepo", "profesor guardado correctamente")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("FirebaseRepo", "Error al guardar profesor: ${e.message}")
                onError(e)
            }
    }

    fun loginAlumno(email: String,password:String,onSucess:()->Unit,onError:(Exception)-> Unit){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener { onSucess() }
            .addOnFailureListener { e->onError(e) }
    }

    fun loginProfesor(email: String,password:String,onSucess:()->Unit,onError:(Exception)-> Unit){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener { onSucess() }
            .addOnFailureListener { e->onError(e) }
    }
}