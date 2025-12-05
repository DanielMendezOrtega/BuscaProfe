package com.dmo.buscaprofe.controller

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dmo.buscaprofe.R
import com.dmo.buscaprofe.databinding.ActivityRegistroAlumnoBinding
import com.dmo.buscaprofe.databinding.ActivityRegistroProfesorBinding
import com.dmo.buscaprofe.model.Alumno
import com.dmo.buscaprofe.model.FirebaseRepository
import com.dmo.buscaprofe.model.Profesor
import com.dmo.buscaprofe.view.commont.MainActivityAlumno
import com.dmo.buscaprofe.view.commont.MainActivityProfesor
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegistroProfesorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroProfesorBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private val firebaseRepo = FirebaseRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroProfesorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnregistrarA.setOnClickListener {
            validarInformacion()
        }
    }

    private var nombre = ""
    private var email = ""
    private var password = ""
    private var cpassword = ""

    private fun validarInformacion() {
        nombre = binding.etNombreC.text.toString().trim()
        email = binding.etEmailC.text.toString().trim()
        password = binding.etPasswordC.text.toString().trim()
        cpassword = binding.etCPasswordC.text.toString().trim()

        when {
            nombre.isEmpty() -> binding.etNombreC.error = "Ingrese nombres"
            email.isEmpty() -> binding.etEmailC.error = "Ingrese email"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> binding.etEmailC.error =
                "Email no válido"

            password.isEmpty() -> binding.etPasswordC.error = "Ingrese Password"
            password.length < 6 -> binding.etPasswordC.error = "Necesita más de 6 caracteres"
            password != cpassword -> binding.etCPasswordC.error =
                "La contraseña tiene que coincidir"

            else -> registrarProfesor()
        }
    }

    private fun registrarProfesor() {
        progressDialog.setMessage("Creando cuenta")
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                insertarInfoBD()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Fallo en el registro: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                progressDialog.hide()
            }
    }

    private fun insertarInfoBD() {
        progressDialog.setMessage("Guardando Informacion")
        val uid = firebaseAuth.uid ?: return
        val fechaRegistro = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val profesor = Profesor(
            nombre = nombre,
            email = email,
            edad = 0,
            dni = "",
            telefono = "",
            fechaRegistro = fechaRegistro,
            ubicacion = "",
            imagenUrl = "",
            asignaturasImpartir = emptyList()
        )
        firebaseRepo.registrarProfesor(profesor, {
            progressDialog.dismiss()
            startActivity(Intent(this, MainActivityProfesor::class.java))
            finishAffinity()
        }, { e ->
            progressDialog.dismiss()
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        })
    }
}