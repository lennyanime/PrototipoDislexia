package com.example.myapplication.procesosPerceptivos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_discriminacion_categorizacion_visual3.*

class DiscriminacionCategorizacionVisual3 : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 3
    private var t1: Int = 0
    private var t2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discriminacion_categorizacion_visual3)

        img15DCV.setOnClickListener {
            clicks++
            t1 = 1
            imagenesCorrectas()
            habilitarBotonSiguiente()
        }

        img18DCV.setOnClickListener {
            clicks++
            t2 = 1
            imagenesCorrectas()
            habilitarBotonSiguiente()
        }

        obtenerClicksHits()

        figurasIncorrectas()

        siguiente()

        btnSiguienteDiscriminacionV.isEnabled = false
    }

    private fun habilitarBotonSiguiente(){

        if(clicks == 6)
            btnSiguienteDiscriminacionV.isEnabled = true
    }

    private fun obtenerClicksHits() {

        val bundle = intent.extras
        clicks = bundle?.get("clicks") as Int
        hits = bundle?.get("hits") as Int

        Toast.makeText(applicationContext,
            "$hits , $clicks",
            Toast.LENGTH_SHORT).show()
    }

    private fun imagenesCorrectas() {

        if (t1 == 1 && t2 == 1) {
            hits++
            Toast.makeText(applicationContext,
                "$hits",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun opcionIncorrecta() {

        t1 = 0
        t2 = 0
    }

    private fun figurasIncorrectas() {

        img14DCV.setOnClickListener {
            clicks++
            opcionIncorrecta()
            habilitarBotonSiguiente()
        }

        img13DCV.setOnClickListener {
            clicks++
            opcionIncorrecta()
            habilitarBotonSiguiente()
        }

        img16DCV.setOnClickListener {
            clicks++
            opcionIncorrecta()
            habilitarBotonSiguiente()
        }

        img17DCV.setOnClickListener {
            clicks++
            opcionIncorrecta()
            habilitarBotonSiguiente()
        }
    }

    private fun siguiente() {

        btnSiguienteDiscriminacionV.setOnClickListener {

            misses = PUNTAJE_MAXIMO - hits

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