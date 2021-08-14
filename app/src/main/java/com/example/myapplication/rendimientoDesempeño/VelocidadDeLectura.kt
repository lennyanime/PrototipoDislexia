package com.example.myapplication.rendimientoDesempeño

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_velocidad_de_lectura.*
import java.util.*
import java.util.concurrent.TimeUnit

class VelocidadDeLectura : AppCompatActivity() {

    private lateinit var horaInicial: Date

    private lateinit var horaFinal: Date

    private val DB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_velocidad_de_lectura)

        instruccionesVelocidadLectura()

        iniciarLectura()

        finalizarLectura()

        siguiente()

        btnIniciarLectura.isEnabled = false

    }

    private fun instruccionesVelocidadLectura() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesVelocidadLectura.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesVelocidadLectura.isEnabled = false
                btnIniciarLectura.isEnabled = true
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun iniciarLectura(): Date {

        horaInicial = Calendar.getInstance().time

        btnIniciarLectura.setOnClickListener {

            txtTextoVelocidadLectura.isVisible = true

            horaInicial = Calendar.getInstance().time
        }
        return horaInicial
    }

    private fun finalizarLectura(): Date {

        horaFinal = Calendar.getInstance().time

        btnFinalizarLectura.setOnClickListener {

            btnSiguienteVelocidadLectura.isEnabled = true

            horaFinal = Calendar.getInstance().time
        }

        return horaFinal
    }

    private fun siguiente() {

        btnSiguienteVelocidadLectura.setOnClickListener {

            val velocidadLectura = TimeUnit.MILLISECONDS.toSeconds(horaFinal.time - horaInicial.time)

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("VelocidadLectura").set(
                    hashMapOf(
                        "VelodidadLectura" to velocidadLectura
                    )
                )
            }

            val intent = Intent(this, ComprensionLectura()::class.java)
            startActivity(intent)
        }
    }
}