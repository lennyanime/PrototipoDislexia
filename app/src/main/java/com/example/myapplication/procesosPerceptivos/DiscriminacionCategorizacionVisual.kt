package com.example.myapplication.procesosPerceptivos

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.*
import kotlinx.android.synthetic.main.activity_discriminicion_visual.*
import kotlinx.android.synthetic.main.activity_discriminicion_visual.view.*
import kotlinx.android.synthetic.main.activity_lateralidad_1.*
import kotlinx.android.synthetic.main.activity_lateralidad_2.*
import kotlin.time.milliseconds

/*
val  IMAGENES_DISCRIMINIZCION_VISUAL = arrayListOf<ImageView>(img1PruebaDiscriminizacionVisual, img2PruebaDiscriminizacionVisual, img3PruebaDiscriminizacionVisual,
        img4PruebaDiscriminizacionVisual, img5PruebaDiscriminizacionVisual, img6PruebaDiscriminizacionVisual)
*/
class DiscriminacionCategorizacionVisual : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var t1: Int = 0
    private var t2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discriminicion_visual)

        instruccionesPruebaDiscriminizacionVisual()

        desactivarImagenes()

        imagenesCorrectas()

        pruebaCorrecta()

        siguiente()

        figurasIncorrectas()
    }


    private fun instruccionesPruebaDiscriminizacionVisual() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesDiscriminizacionVisual.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesDiscriminizacionVisual.setEnabled(false)
                Thread.sleep(4000)
                mostrarBotonesOcultosDiscriminacionVisual()
                activarImagenes()
                //btnInstruccionesDiscriminizacionVisual.setEnabled(true)
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun mostrarBotonesOcultosDiscriminacionVisual(){
        val BOTONES_PRUEBA_DISCRIMINACION_VISUAL = arrayListOf<ImageView>(
            img1PruebaDiscriminizacionVisual,
            img2PruebaDiscriminizacionVisual,
            img3PruebaDiscriminizacionVisual,
            img4PruebaDiscriminizacionVisual,
            img5PruebaDiscriminizacionVisual,
            img6PruebaDiscriminizacionVisual)


        BOTONES_PRUEBA_DISCRIMINACION_VISUAL.forEach {
            it.setVisibility(View.VISIBLE)
        }
    }

    private fun desactivarImagenes() {

        val IMAGENES_DISCRIMINIZCION_VISUAL = arrayListOf<ImageView>(
            img1PruebaDiscriminizacionVisual,
            img2PruebaDiscriminizacionVisual,
            img3PruebaDiscriminizacionVisual,
            img4PruebaDiscriminizacionVisual,
            img5PruebaDiscriminizacionVisual,
            img6PruebaDiscriminizacionVisual)

        IMAGENES_DISCRIMINIZCION_VISUAL.forEach { it.setEnabled(false) }
    }

    private fun activarImagenes() {

        val IMAGENES_DISCRIMINIZCION_VISUAL = arrayListOf<ImageView>(
            img1PruebaDiscriminizacionVisual,
            img2PruebaDiscriminizacionVisual,
            img3PruebaDiscriminizacionVisual,
            img4PruebaDiscriminizacionVisual,
            img5PruebaDiscriminizacionVisual,
            img6PruebaDiscriminizacionVisual)

        IMAGENES_DISCRIMINIZCION_VISUAL.forEach { it.setEnabled(true) }
    }

    private fun pruebaCorrecta() {

        img4PruebaDiscriminizacionVisual.setOnClickListener {
            t1 = 1
        }

        img5PruebaDiscriminizacionVisual.setOnClickListener {
            t2 = 1
        }
            }

    //TODO: validar el número de hits y misses
    private fun imagenesCorrectas() {

        if (t1 == 1 && t2 == 1) {
            hits++
            Toast.makeText(applicationContext,
                "$hits",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun opcionIncorrecta(){

        t1=0
        t2=0
    }

    private fun figurasIncorrectas(){

        img1PruebaDiscriminizacionVisual.setOnClickListener {

            opcionIncorrecta()
        }

        img2PruebaDiscriminizacionVisual.setOnClickListener {

            opcionIncorrecta()
        }

        img3PruebaDiscriminizacionVisual.setOnClickListener {

            opcionIncorrecta()
        }

        img6PruebaDiscriminizacionVisual.setOnClickListener {

            opcionIncorrecta()
        }
    }

    private fun siguiente(){

        btnSiguienteDiscriminizacionCV.setOnClickListener {

            imagenesCorrectas()

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("DiscriminaciónCategorizacionVisual").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}