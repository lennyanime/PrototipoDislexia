package com.example.myapplication.competenciasLinguisticas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencia_ortografica.*

var contadorSignosPuntuacionTexto = 0
var contadorSignosPuntuacionPrueba = 0

class CompetenciaOrtografica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_ortografica)

        contarSignosPuntuacion()

        btnContadorSignosPuntuacion.setOnClickListener {

            contadorSignosPuntuacionPrueba++
        }
    }

    private fun contarSignosPuntuacion() {

        val texto = txtTextoCOrtografica.getText()

        for (i in texto) {

            if (i.equals(',') || i.equals('.') || i.equals(':') || i.equals(';'))
                contadorSignosPuntuacionTexto++
        }

        /*Toast.makeText(
            applicationContext,
            "${contador}",
            Toast.LENGTH_SHORT
        ).show()*/
    }
}