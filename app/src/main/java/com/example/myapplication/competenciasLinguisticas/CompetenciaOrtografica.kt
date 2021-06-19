package com.example.myapplication.competenciasLinguisticas

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_ortografica.*

private var contadorSignosPuntuacionTexto = 0

class CompetenciaOrtografica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_ortografica)

        instruccionesCompetenciaOrtografica()

        contarSignosPuntuacion()

        contarSignosPrueba()

        siguiente()
    }

    private fun instruccionesCompetenciaOrtografica() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesCompetenciaOrtografica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                txtTextoCOrtografica.visibility = View.VISIBLE
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun contarSignosPuntuacion() {

        val texto = txtTextoCOrtografica.text

        for (i in texto) {

            if (i.equals(',') || i.equals('.') || i.equals(':') || i.equals(';'))
                contadorSignosPuntuacionTexto++
        }
    }

    private fun contarSignosPrueba(){

        btnContadorSignosPuntuacion.setOnClickListener {

            clicks++
        }
    }

    private fun siguiente() {

        btnSiguienteCompetenciaOrtografica.setOnClickListener {

            if(clicks == contadorSignosPuntuacionTexto)
                hits = 1
            misses = 1

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaOrtográfica").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}