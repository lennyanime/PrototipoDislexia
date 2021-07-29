package com.example.myapplication.rendimientoDesempeño

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_velocidad_de_lectura.*

class VelocidadDeLectura : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_velocidad_de_lectura)

        instruccionesVelocidadLectura()

        siguiente()
    }

    private fun instruccionesVelocidadLectura() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesVelocidadLectura.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesVelocidadLectura.isEnabled = false
                txtTextoVelocidadLectura.visibility = View.VISIBLE
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun siguiente(){

        btnSiguienteVelocidadLectura.setOnClickListener{

            val intent = Intent(this, ComprensionLectura()::class.java)
            startActivity(intent)
        }
    }
}