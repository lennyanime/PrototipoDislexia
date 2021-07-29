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
import kotlinx.android.synthetic.main.activity_memoria_secuencial_visual.*
import kotlinx.android.synthetic.main.activity_memoria_visual.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var secuenciaVisualCorrectas: MutableList<ImageView>
//mutableListOf("correcto1", "correcto2", "correcto3")

private lateinit var secuenciaVisualRespuestas: MutableList<ImageView>

class MemoriaSecuencialVisual : AppCompatActivity() {

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
        setContentView(R.layout.activity_memoria_secuencial_visual)

        btnSiguienteMemoriaSV.isEnabled = false

        btnMemoriaSecuencialVisual.isEnabled = false

        instruccionesMemoriaSecuencialVisual()

        inhabilitarImagenes()

        siguiente()

        validarImagenCorrecta()

        iniciarPrueba()

        secuenciaVisualCorrectas = mutableListOf()

        secuenciaVisualRespuestas = mutableListOf()
    }

    private fun instruccionesMemoriaSecuencialVisual() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesMemoriaSecuencialVisual.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesMemoriaSecuencialVisual.isEnabled = false
                Thread.sleep(2000)
                btnMemoriaSecuencialVisual.isEnabled = true
            }
        }
    }

    private fun iniciarPrueba() {

        btnMemoriaSecuencialVisual.setOnClickListener {

            listaImagenes().forEach {

                it.isVisible = true
            }

            imagenAzar1 = listaImagenes().random()

            imagenAzar2 = listaImagenes().random()

            imagenAzar3 = listaImagenes().random()

            while (imagenAzar1 == imagenAzar2 || imagenAzar1 == imagenAzar3) {
                imagenAzar1 = listaImagenes().random()

            }
            imagenAzar1.tag = "correcto"
            secuenciaVisualCorrectas.add(imagenAzar1)

            while (imagenAzar2 == imagenAzar1 || imagenAzar2 == imagenAzar3) {
                imagenAzar2 = listaImagenes().random()

            }
            imagenAzar2.tag = "correcto"
            secuenciaVisualCorrectas.add(imagenAzar2)

            while (imagenAzar3 == imagenAzar2 || imagenAzar3 == imagenAzar1) {
                imagenAzar3 = listaImagenes().random()

            }
            imagenAzar3.tag = "correcto"
            secuenciaVisualCorrectas.add(imagenAzar3)

            GlobalScope.launch {
                delay(2000)
                ocultarImagen1()
            }

            GlobalScope.launch {
                delay(3000)
                mostrarImagen1()
            }

            GlobalScope.launch {
                delay(4000)
                ocultarImagen2()
            }

            GlobalScope.launch {
                delay(5000)
                mostrarImagen2()
            }

            GlobalScope.launch {
                delay(6000)
                ocultarImagen3()
            }

            GlobalScope.launch {
                delay(7000)
                mostrarImagen3()
                habilitarImagenes()
            }
        }
    }

    private fun ocultarImagen1() = Thread(
        kotlinx.coroutines.Runnable {

            this@MemoriaSecuencialVisual.runOnUiThread {

                imagenAzar1.isInvisible = true
            }
        },
    ).start()

    private fun mostrarImagen1() = Thread(
        kotlinx.coroutines.Runnable {

            this@MemoriaSecuencialVisual.runOnUiThread {

                imagenAzar1.isVisible = true
            }
        },
    ).start()

    private fun ocultarImagen2() = Thread(
        kotlinx.coroutines.Runnable {

            this@MemoriaSecuencialVisual.runOnUiThread {

                imagenAzar2.isInvisible = true
            }
        },
    ).start()

    private fun mostrarImagen2() = Thread(
        kotlinx.coroutines.Runnable {

            this@MemoriaSecuencialVisual.runOnUiThread {

                imagenAzar2.isVisible = true
            }
        },
    ).start()

    private fun ocultarImagen3() = Thread(
        kotlinx.coroutines.Runnable {

            this@MemoriaSecuencialVisual.runOnUiThread {

                imagenAzar3.isInvisible = true
            }
        },
    ).start()

    private fun mostrarImagen3() = Thread(
        kotlinx.coroutines.Runnable {

            this@MemoriaSecuencialVisual.runOnUiThread {

                imagenAzar3.isVisible = true
            }
        },
    ).start()

    private fun habilitarImagenes() = Thread(
        kotlinx.coroutines.Runnable {

            this@MemoriaSecuencialVisual.runOnUiThread {

                listaImagenes().forEach {

                    it.isEnabled = true
                }
            }
        },
    ).start()

    private fun validarImagenCorrecta() {

        imgPosicion1.setOnClickListener {
            clicks++

            if (it.tag == "correcto")
                secuenciaVisualRespuestas.add(imgPosicion1)
            secuenciaVisualRespuestas.add(imgPosicion1)

            habilitarBotonSiguiente()
        }

        imgPosicion2.setOnClickListener {
            clicks++

            if (it.tag == "correcto")
                secuenciaVisualRespuestas.add(imgPosicion2)
            secuenciaVisualRespuestas.add(imgPosicion2)

            habilitarBotonSiguiente()
        }

        imgPosicion3.setOnClickListener {
            clicks++

            if (it.tag == "correcto")
                secuenciaVisualRespuestas.add(imgPosicion3)
            secuenciaVisualRespuestas.add(imgPosicion3)

            habilitarBotonSiguiente()
        }

        imgPosicion4.setOnClickListener {
            clicks++

            if (it.tag == "correcto")
                secuenciaVisualRespuestas.add(imgPosicion4)
            secuenciaVisualRespuestas.add(imgPosicion4)

            habilitarBotonSiguiente()
        }

        imgPosicion5.setOnClickListener {
            clicks++

            if (it.tag == "correcto")
                secuenciaVisualRespuestas.add(imgPosicion5)
            secuenciaVisualRespuestas.add(imgPosicion5)

            habilitarBotonSiguiente()
        }

        imgPosicion6.setOnClickListener {
            clicks++

            if (it.tag == "correcto")
                secuenciaVisualRespuestas.add(imgPosicion6)
            secuenciaVisualRespuestas.add(imgPosicion6)

            habilitarBotonSiguiente()
        }
    }

    private fun habilitarBotonSiguiente() {

        if (clicks == 3) {

            inhabilitarImagenes()

            btnSiguienteMemoriaSV.isEnabled = true
        }
    }

    private fun listaImagenes(): ArrayList<ImageView> {

        return arrayListOf(
            imgPosicion1,
            imgPosicion2,
            imgPosicion3,
            imgPosicion4,
            imgPosicion5,
            imgPosicion6
        )
    }

    private fun inhabilitarImagenes() {

        listaImagenes().forEach {

            it.isEnabled = false
        }
    }

     private fun validarRespuestas() {

         var indice = 0

         while (indice < secuenciaVisualCorrectas.size) {
             if (secuenciaVisualCorrectas[indice] == secuenciaVisualRespuestas[indice])
                 hits++

             indice++
         }

         Toast.makeText(
             applicationContext,
             "$hits",
             Toast.LENGTH_SHORT
         ).show()
     }

    private fun siguiente() {

        btnSiguienteMemoriaSV.setOnClickListener {

            validarRespuestas()

            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("MemoriaSecuencialVisual").set(
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

