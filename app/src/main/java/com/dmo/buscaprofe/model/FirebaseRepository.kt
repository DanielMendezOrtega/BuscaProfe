package com.dmo.buscaprofe.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class FirebaseRepository {
    private val auth= FirebaseAuth.getInstance()
    private val db= FirebaseDatabase.getInstance()

    //esta funcion aceptarra callbacks para exito y error
    fun registrarAlumno(alumno: Alumno, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val uid = FirebaseAuth.getInstance().uid ?: return
        Log.d("FirebaseRepo", "Guardando alumno con UID: $uid")
        FirebaseDatabase.getInstance().getReference("usuarios/alumnos")
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

    fun registrarProfesor(profesor: Profesor){
        val uid = auth.uid?: return
        db.getReference("usuarios/profesor")
            .child(uid)
            .setValue(profesor)

            .addOnSuccessListener {
                println("Profesor registrado correctamente")
            }
            .addOnFailureListener { e->
                println("Error al registrar profesor: ${e.message}")
            }
    }

    fun loginAlumno(email: String,password:String,onSucess:()->Unit,onError:(Exception)-> Unit){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener { onSucess() }
            .addOnFailureListener { e->onError(e) }
    }
}