package com.example.myapplication.procesosPerceptivos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_discriminacion_categorizacion_visual2.*

class DiscriminacionCategorizacionVisual2 : AppCompatActivity() {

    private var clicks: Int = 0
    private var hits: Int = 0
    private var t1: Int = 0
    private var t2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discriminacion_categorizacion_visual2)

        img8PruebaDiscriminacionVisual.setOnClickListener {
            clicks++
            t1 = 1
            imagenesCorrectas()
            terceraPrueba()
        }

        img11PruebaDiscriminacionVisual.setOnClickListener {
            clicks++
            t2 = 1
            imagenesCorrectas()
            terceraPrueba()
        }

        obtenerClicksHits()

        figurasIncorrectas()

        imagenesCorrectas()

    }

    private fun obtenerClicksHits(){

        val bundle = intent.extras
        clicks = bundle?.get("clicks") as Int
        hits = bundle?.get("hits") as Int
    }

    private fun terceraPrueba(){

        if (clicks == 4) {

            val intent = Intent(this, DiscriminacionCategorizacionVisual3()::class.java)
            intent.putExtra("hits", hits)
            intent.putExtra("clicks", clicks)
            startActivity(intent)
        }
    }

    private fun imagenesCorrectas() {

        if (t1 == 1 && t2 == 1)
            hits++
    }

    private fun opcionIncorrecta() {

        t1 = 0
        t2 = 0
    }

    private fun figurasIncorrectas() {

        img7PruebaDiscriminacionVisual.setOnClickListener {
            clicks++
            opcionIncorrecta()
            terceraPrueba()
        }

        img9PruebaDiscriminacionVisual.setOnClickListener {
            clicks++
            opcionIncorrecta()
            terceraPrueba()
        }

        img10PruebaDiscriminacionVisual.setOnClickListener {
            clicks++
            opcionIncorrecta()
            terceraPrueba()
        }

        img12PruebaDiscriminacionVisual.setOnClickListener {
            clicks++
            opcionIncorrecta()
            terceraPrueba()
        }
    }
}
