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

class MemoriaVisual : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 6

    private lateinit var imagenAzar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoria_visual)

        instruccionesMemoriaVisual()

        inhabilitarImagenes()

        siguiente()

        //prueba()

        //mostrarImagenOculta()

    }

    private fun instruccionesMemoriaVisual() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesMemoriaVisual.setOnClickListener {

            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesMemoriaVisual.isEnabled = false
                habilitarImagenes()
                prueba()
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

        btnIniciarPrueba.setOnClickListener {
            imagenAzar = listaImagenes().random()
            imagenAzar.tag = "correcto"
            imagenAzar.isInvisible = true

            GlobalScope.launch(Dispatchers.Main) {
                mostrarImagenOculta()
            }
        }
    }

    suspend fun mostrarImagenOculta() {

        delay(2000)

        imagenAzar.isVisible = true

    }

    private fun validarImagenCorrecta(imagen: ImageView) {

        listaImagenes().forEach {

            it.setOnClickListener {

                if (imagen.tag == "correcto") {

                    Toast.makeText(
                        applicationContext,
                        "correcto",
                        Toast.LENGTH_SHORT
                    ).show()

                    hits++

                }
            }
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