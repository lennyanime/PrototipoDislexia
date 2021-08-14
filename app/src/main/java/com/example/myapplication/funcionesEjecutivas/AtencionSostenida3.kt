package com.example.myapplication.funcionesEjecutivas

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_atencion_sostenida3.*

class AtencionSostenida3 : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 6

    private lateinit var imagenAzar1: ImageView
    private lateinit var imagenAzar2: ImageView
    private lateinit var imagenAzar3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion_sostenida3)

        btnSiguienteAtencionSostenida.isEnabled = false

        desaparecerImagenesPrueba3()

        validarImagenesPrueba3()

        obtenerClicksHits()

        siguiente()
    }

    private fun imagenesPrueba3(): MutableList<ImageView> {

        return mutableListOf(
            imgCruz1AtencionSostenida3,
            imgCruz2AtencionSostenida3,
            imgCuadrado1AtencionSostenida3,
            imgCuadrado2AtencionSostenida3,
            imgTriangulo1AtencionSostenida3,
            imgTriangulo2AtencionSostenida3,
            imgEstrella1AtencionSostenida3,
            imgEstrella2AtencionSostenida3,
            imgCirculo1AtencionSostenida3,
            imgCirculo2AtencionSostenida3,
            imgTrapecio1AtencionSostenida3,
            imgTrapecio2AtencionSostenida3)
    }

    private fun validarImagenesPrueba3() {

        imagenesPrueba3().forEach {

            it.setOnClickListener {

                clicks++
                var boton = it.tag

                if (imagenAzar1.tag == boton || imagenAzar2.tag == boton || imagenAzar3.tag == boton) {

                    hits++
                    Toast.makeText(applicationContext,
                        "correcto",
                        Toast.LENGTH_SHORT).show()
                    habilitarBotonSiguiente()
                } else {
                    habilitarBotonSiguiente()
                }
            }
        }
    }

    private fun desaparecerImagenesPrueba3() {

        imagenAzar1 = imagenesPrueba3().random()
        imagenAzar2 = imagenesPrueba3().random()
        imagenAzar3 = imagenesPrueba3().random()

        while (imagenAzar1.tag == imagenAzar2.tag || imagenAzar1.tag == imagenAzar3.tag)
            imagenAzar1 = imagenesPrueba3().random()

        while (imagenAzar2.tag == imagenAzar1.tag || imagenAzar2.tag == imagenAzar3.tag)
            imagenAzar2 = imagenesPrueba3().random()

        while (imagenAzar3.tag == imagenAzar1.tag || imagenAzar3.tag == imagenAzar2.tag)
            imagenAzar3 = imagenesPrueba3().random()

        imagenAzar1.isInvisible = true
        imagenAzar2.isInvisible = true
        imagenAzar3.isInvisible = true
    }

    private fun inhabilitarImagenesInterfazPrueba3() {

        imagenesPrueba3().forEach {

            it.isEnabled = false
        }
    }

    private fun obtenerClicksHits() {

        val bundle = intent.extras
        clicks = bundle?.get("clicks") as Int
        hits = bundle?.get("hits") as Int

        Toast.makeText(applicationContext,
            "$hits , $clicks",
            Toast.LENGTH_SHORT).show()
    }

    private fun habilitarBotonSiguiente() {

        if (clicks == 6) {

            btnSiguienteAtencionSostenida.isEnabled = true
            inhabilitarImagenesInterfazPrueba3()
        }
    }

    private fun siguiente() {

        btnSiguienteAtencionSostenida.setOnClickListener {

            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("AtenciónSostenida").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}