package com.dmo.buscaprofe.view.commont

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dmo.buscaprofe.R
import com.dmo.buscaprofe.controller.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SplashActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        firebaseAuth= FirebaseAuth.getInstance()

        verBienvenida()
    }

    private fun verBienvenida() {
        Toast.makeText(this, "Bienvenido a BuscaProfe", Toast.LENGTH_SHORT).show()

        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                // Simplemente navega a la pantalla de selección
                startActivity(Intent(this@SplashActivity, SeleccionarTipoUsuario::class.java))
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {
                // No hacer nada
            }
        }.start()
    }
}
