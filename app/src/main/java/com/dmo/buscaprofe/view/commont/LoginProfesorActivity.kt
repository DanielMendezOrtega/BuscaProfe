package com.dmo.buscaprofe.view.commont

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
import com.dmo.buscaprofe.controller.RegistroAlumnoActivity
import com.dmo.buscaprofe.controller.RegistroProfesorActivity
import com.dmo.buscaprofe.databinding.ActivityLoginAlumnoBinding
import com.dmo.buscaprofe.databinding.ActivityLoginProfesorBinding
import com.dmo.buscaprofe.model.FirebaseRepository

class LoginProfesorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginProfesorBinding
    private lateinit var progressDialog: ProgressDialog
    private val firebaseRepo = FirebaseRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginProfesorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnLoginC.setOnClickListener {
            validarInfo()
        }

        binding.tvRegistrarC.setOnClickListener {
            startActivity(Intent(this, RegistroProfesorActivity::class.java))
        }
    }

    private var email = ""
    private var password = ""

    private fun validarInfo() {
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.etEmail.error = "Ingrese Email"
            binding.etEmail.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Email no válido"
            binding.etEmail.requestFocus()
        } else if (password.isEmpty()) {
            binding.etPassword.error = "Ingrese Password"
            binding.etPassword.requestFocus()
        } else {
            loginProfesor()
        }
    }

    private fun loginProfesor() {
        progressDialog.setMessage("Ingresando")
        progressDialog.show()

        firebaseRepo.loginProfesor(email, password, {
            progressDialog.dismiss()
            startActivity(Intent(this, MainActivityAlumno::class.java))
            finishAffinity()
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
        }, { e ->
            progressDialog.dismiss()
            Toast.makeText(this, "No se puede iniciar sesión. CAUSA: ${e.message}", Toast.LENGTH_SHORT).show()
        })
    }
}