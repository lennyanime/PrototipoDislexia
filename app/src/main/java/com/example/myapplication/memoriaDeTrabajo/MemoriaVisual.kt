package com.example.myapplication.memoriaDeTrabajo

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_memoria_visual.*
import kotlinx.coroutines.*

private lateinit var imagenesSeleccionadas: MutableList<String>

class MemoriaVisual : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 3

    private lateinit var imagenAzar1: ImageView
    private lateinit var imagenAzar2: ImageView
    private lateinit var imagenAzar3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoria_visual)

        instruccionesMemoriaVisual()

        btnIniciarPruebaMemoriaVisual.isEnabled = false

        btnSiguienteMemoriaVisual.isEnabled = false

        inhabilitarImagenes()

        siguiente()

        prueba()

        validarImagenCorrecta()

        imagenesSeleccionadas = mutableListOf()
    }

    private fun instruccionesMemoriaVisual() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesMemoriaVisual.setOnClickListener {

            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesMemoriaVisual.isEnabled = false
                habilitarImagenes()
                btnIniciarPruebaMemoriaVisual.isEnabled = true
            }
        }
    }

    private fun listaImagenes(): ArrayList<ImageView> {

        return arrayListOf(
            imgMemoriaVisual1,
            imgMemoriaVisual2,
            imgMemoriaVisual3,
            imgMemoriaVisual4,
            imgMemoriaVisual5,
            imgMemoriaVisual6,
            imgMemoriaVisual7,
            imgMemoriaVisual8,
            imgMemoriaVisual9
        )
    }

    private fun habilitarImagenes() {

        listaImagenes().forEach {

            it.isEnabled = true
        }
    }

    private fun inhabilitarImagenes() {

        listaImagenes().forEach {

            it.isEnabled = false
        }
    }

    private fun prueba() {

        btnIniciarPruebaMemoriaVisual.setOnClickListener {

            imagenAzar1 = listaImagenes().random()
            imagenAzar1.tag = "correcto"

            imagenAzar2 = listaImagenes().random()
            imagenAzar2.tag = "correcto"

            imagenAzar3 = listaImagenes().random()
            imagenAzar3.tag = "correcto"

            while(imagenAzar1 == imagenAzar2 || imagenAzar1 == imagenAzar3){
                imagenAzar1 = listaImagenes().random()
                imagenAzar1.tag = "correcto"
            }

            while(imagenAzar2 == imagenAzar1 || imagenAzar2 == imagenAzar3){
                imagenAzar2 = listaImagenes().random()
                imagenAzar2.tag = "correcto"
            }

            while(imagenAzar3 == imagenAzar2 || imagenAzar3 == imagenAzar1){
                imagenAzar3 = listaImagenes().random()
                imagenAzar3.tag = "correcto"
            }

            ocultarImagen()

            GlobalScope.launch {
                delay(100)
                imagenTransparente()
            }
        }
    }

    private fun ocultarImagen() = Thread(
        Runnable {
            this@MemoriaVisual.runOnUiThread {

                imagenAzar1.isInvisible = true
                imagenAzar2.isInvisible = true
                imagenAzar3.isInvisible = true
            }
        },
    ).start()

    private fun imagenTransparente() = Thread(
        Runnable {
            this@MemoriaVisual.runOnUiThread {

                imagenAzar1.isVisible = true
                imagenAzar2.isVisible = true
                imagenAzar3.isVisible = true
            }
        },
    ).start()

    private fun validarImagenCorrecta() {

        listaImagenes().forEach {

            it.setOnClickListener {

                clicks++

                if (it.tag == "correcto") {

                    Toast.makeText(
                        applicationContext,
                        "correcto",
                        Toast.LENGTH_SHORT
                    ).show()

                    hits++
                }

                habilitarBotonSiguiente()
            }
        }
    }

    private fun habilitarBotonSiguiente() {

        if (clicks == 3) {

            inhabilitarImagenes()

            btnSiguienteMemoriaVisual.isEnabled = true
        }
    }

    private fun siguiente() {

        btnSiguienteMemoriaVisual.setOnClickListener {

            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("MemoriaVisual").set(
                    hashMapOf(
                        "Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses
                    )
                )
            }
        }
    }
}