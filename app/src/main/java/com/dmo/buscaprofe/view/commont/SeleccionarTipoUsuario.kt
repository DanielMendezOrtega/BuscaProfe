package com.dmo.buscaprofe.view.commont

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dmo.buscaprofe.R
import com.dmo.buscaprofe.databinding.ActivitySeleccionarTipoUsuarioBinding

class SeleccionarTipoUsuario : AppCompatActivity() {
    private lateinit var binding: ActivitySeleccionarTipoUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySeleccionarTipoUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipoProfesor.setOnClickListener {
            startActivity(Intent(this@SeleccionarTipoUsuario, LoginProfesorActivity::class.java))
        }
        binding.tipoAlumno.setOnClickListener {
            startActivity(Intent(this@SeleccionarTipoUsuario, LoginAlumnoActivity::class.java))
        }


    }
}