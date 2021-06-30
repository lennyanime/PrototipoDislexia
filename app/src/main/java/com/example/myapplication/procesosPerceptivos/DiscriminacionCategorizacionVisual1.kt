package com.example.myapplication.procesosPerceptivos

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_discriminicion_visual.*

/*
val  IMAGENES_DISCRIMINIZCION_VISUAL = arrayListOf<ImageView>(img1PruebaDiscriminizacionVisual, img2PruebaDiscriminizacionVisual, img3PruebaDiscriminizacionVisual,
        img4PruebaDiscriminizacionVisual, img5PruebaDiscriminizacionVisual, img6PruebaDiscriminizacionVisual)
*/
class DiscriminacionCategorizacionVisual1 : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var t1: Int = 0
    private var t2: Int = 0

    private var data : MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discriminicion_visual)

        instruccionesPruebaDiscriminizacionVisual()

        //desactivarImagenes()

        pruebaCorrecta()

        //siguiente()

        figurasIncorrectas()

        btnSiguienteDiscriminizacionCV.isEnabled = false
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

    private fun mostrarBotonesOcultosDiscriminacionVisual() {

        val BOTONES_PRUEBA_DISCRIMINACION_VISUAL = arrayListOf<ImageView>(
            img1PruebaDiscriminizacionVisual,
            img2PruebaDiscriminizacionVisual,
            img3PruebaDiscriminizacionVisual,
            img4PruebaDiscriminizacionVisual,
            img5PruebaDiscriminizacionVisual,
            img6PruebaDiscriminizacionVisual)

        BOTONES_PRUEBA_DISCRIMINACION_VISUAL.forEach {
            it.visibility = View.VISIBLE
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

        IMAGENES_DISCRIMINIZCION_VISUAL.forEach { it.isEnabled = false }
    }

    private fun activarImagenes() {

        val IMAGENES_DISCRIMINIZCION_VISUAL = arrayListOf<ImageView>(
            img1PruebaDiscriminizacionVisual,
            img2PruebaDiscriminizacionVisual,
            img3PruebaDiscriminizacionVisual,
            img4PruebaDiscriminizacionVisual,
            img5PruebaDiscriminizacionVisual,
            img6PruebaDiscriminizacionVisual)

        IMAGENES_DISCRIMINIZCION_VISUAL.forEach { it.isEnabled = true }
    }

    private fun pruebaCorrecta() {

        img4PruebaDiscriminizacionVisual.setOnClickListener {
            clicks++
            t1 = 1
            imagenesCorrectas()
            segundaPrueba()
        }

        img5PruebaDiscriminizacionVisual.setOnClickListener {
            clicks++
            t2 = 1
            imagenesCorrectas()
            segundaPrueba()
        }
    }

    private fun imagenesCorrectas() {

        if (t1 == 1 && t2 == 1) {
            hits++
            segundaPrueba()
            Toast.makeText(applicationContext,
                "$hits",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun segundaPrueba() {

        if (clicks == 2) {
            data.add(clicks)
            data.add(hits)
            val intent = Intent(this, DiscriminacionCategorizacionVisual2()::class.java)
            intent.putExtra("hits", hits)
            intent.putExtra("clicks", clicks)
            startActivity(intent)
        }
    }

    private fun opcionIncorrecta() {

        t1 = 0
        t2 = 0
    }

    private fun figurasIncorrectas() {

        img1PruebaDiscriminizacionVisual.setOnClickListener {
            clicks++
            opcionIncorrecta()
            segundaPrueba()
        }

        img2PruebaDiscriminizacionVisual.setOnClickListener {
            clicks++
            opcionIncorrecta()
            segundaPrueba()
        }

        img3PruebaDiscriminizacionVisual.setOnClickListener {
            clicks++
            opcionIncorrecta()
            segundaPrueba()
        }

        img6PruebaDiscriminizacionVisual.setOnClickListener {
            clicks++
            opcionIncorrecta()
            segundaPrueba()
        }
    }

    /*private fun siguiente() {

        btnSiguienteDiscriminizacionCV.setOnClickListener {

            //segundaPrueba()

           *//* imagenesCorrectas()
            misses = PUNTAJE_MAXIMO - hits
            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("DiscriminaciónCategorizacionVisual").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }*//*
        }
    }*/
}