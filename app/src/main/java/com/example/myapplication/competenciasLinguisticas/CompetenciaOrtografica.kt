package com.example.myapplication.competenciasLinguisticas

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Componentes
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

        btnContadorSignosPuntuacion.isEnabled = false

        instruccionesCompetenciaOrtografica()

        contarSignosPuntuacion()

        contarSignosPrueba()

        siguiente()

        menu()
    }

    private fun instruccionesCompetenciaOrtografica() {

        val mp = MediaPlayer.create(this, R.raw.competenciaortografica)

        btnInstruccionesCompetenciaOrtografica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(29000)
                txtTextoCOrtografica.visibility = View.VISIBLE
                btnContadorSignosPuntuacion.isEnabled = true

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

    private fun menu(){

        btnMenuPrincipalCompetenciaOrtografica.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun siguiente() {

        btnSiguienteCompetenciaOrtografica.setOnClickListener {

            if(clicks == contadorSignosPuntuacionTexto){
                hits = 1
            }else{
                misses = 1
            }

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaOrtográfica").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}