package com.example.myapplication.competenciasLinguisticas

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_sintactica.*


class CompetenciaSintactica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_sintactica)

        instruccionesCompetenciaSintactica()

        imagenesDeshabilitadas()

        btnSiguienteCompetenciaSintactica.setEnabled(false)

        prueba()

        siguiente()
    }

    private fun instruccionesCompetenciaSintactica() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesCompetenciaSintactica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesCompetenciaSintactica.setEnabled(false)
                imagenesHabilitadas()
                txtInstruccionCompetenciaSemantica.setVisibility(View.VISIBLE)
            }
        }
    }

    private fun prueba() {

        img1CompetenciaSintactica.setOnClickListener{
            clicks++
            hits++
            imagenesDeshabilitadas()
            habilitarBotonSiguiente()
        }

        img2CompetenciaSintactica.setOnClickListener{
            clicks++
            misses++
            imagenesDeshabilitadas()
            habilitarBotonSiguiente()
        }

        img3CompetenciaSintactica.setOnClickListener{
            clicks++
            misses++
            imagenesDeshabilitadas()
            habilitarBotonSiguiente()
        }

        img4CompetenciaSintactica.setOnClickListener{
            clicks++
            misses++
            imagenesDeshabilitadas()
            habilitarBotonSiguiente()
        }
    }

    private fun imagenesDeshabilitadas() {
        val IMAGENES_COMPETENCIA_SINTACTICA = arrayListOf<ImageView>(img1CompetenciaSintactica, img2CompetenciaSintactica, img3CompetenciaSintactica, img4CompetenciaSintactica)

        IMAGENES_COMPETENCIA_SINTACTICA.forEach {
            it.setEnabled(false)
        }
    }

    private fun imagenesHabilitadas() {
        val IMAGENES_COMPETENCIA_SINTACTICA = arrayListOf<ImageView>(img1CompetenciaSintactica, img2CompetenciaSintactica, img3CompetenciaSintactica, img4CompetenciaSintactica)

        IMAGENES_COMPETENCIA_SINTACTICA.forEach {
            it.setEnabled(true)
            it.setVisibility(View.VISIBLE)
        }
    }

    private fun habilitarBotonSiguiente() {

        btnSiguienteCompetenciaSintactica.setEnabled(true)
    }

    private fun siguiente(){

        btnSiguienteCompetenciaSintactica.setOnClickListener {

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaSintáctica").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}