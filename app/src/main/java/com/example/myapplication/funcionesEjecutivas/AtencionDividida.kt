package com.example.myapplication.funcionesEjecutivas

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_atencion_dividida.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var listaTags: MutableList<String>

class AtencionDividida : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion_dividida)

        btnSiguienteAtencionDividida.isEnabled = false

        instruccionesAtencionDividida()

        iniciarPrueba()

        siguiente()

        validarImagenesMatriz2()

        validarImagenesMatriz1()

        listaTags = mutableListOf()
    }

    private fun instruccionesAtencionDividida() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesAtencionDividida.setOnClickListener {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesAtencionDividida.isEnabled = false
                habilitarImagenes()
            }
        }
    }

    private fun iniciarPrueba() {

        btnIniciarPruebaAtencionDividida.setOnClickListener {

            desaparecerImagenes()

            GlobalScope.launch {
                desaparecerImagenes2()
            }

            GlobalScope.launch {
                desaparecerImagenes3()
            }
        }
    }

    private fun listaImagenes1(): ArrayList<ImageView> {

        return arrayListOf(
            imgCruz1AtencionDividida,
            imgCirculo1AtencionDividida,
            imgTriangulo1AtencionDividida,
            imgPentagono1AtencionDividida,
            imgEstrella1AtencionDividida,
            imgTrapecio1AtencionDividida,
            imgCuadrado1AtencionDividida,
            imgTrianguloInvertido1AtencionDividida,
            imgRectangulo1AtencionDividida,
        )
    }

    private fun listaImagenes2(): ArrayList<ImageView> {

        return arrayListOf(
            imgCruz2AtencionDividida,
            imgCirculo2AtencionDividida,
            imgTriangulo2AtencionDividida,
            imgPentagono2AtencionDividida,
            imgEstrella2AtencionDividida,
            imgTrapecio2AtencionDividida,
            imgCuadrado2AtencionDividida,
            imgTrianguloInvertido2AtencionDividida,
            imgRectangulo2AtencionDividida
        )
    }

    private fun desaparecerImagenes() {

        val imagenAzar1 = listaImagenes1().random()
        var imagenAzar2 = listaImagenes2().random()

        listaTags.add(imagenAzar1.tag.toString())

        while (listaTags.contains(imagenAzar2.tag.toString()))
            imagenAzar2 = listaImagenes2().random()

        imagenAzar1.isInvisible = true
        imagenAzar2.isInvisible = true

        listaTags.add(imagenAzar2.tag.toString())
    }

    suspend fun desaparecerImagenes2() {

            delay(2000)

            val imagenAzar3 = listaImagenes1().random()
            var imagenAzar4 = listaImagenes2().random()

            listaTags.add(imagenAzar3.tag.toString())

            while (listaTags.contains(imagenAzar4.tag.toString()))
                imagenAzar4 = listaImagenes2().random()

            imagenAzar3.isInvisible = true
            imagenAzar4.isInvisible = true

            listaTags.add(imagenAzar4.tag.toString())

    }

    suspend fun desaparecerImagenes3() {

            delay(4000)

            val imagenAzar5 = listaImagenes1().random()
            var imagenAzar6 = listaImagenes2().random()

            listaTags.add(imagenAzar5.tag.toString())

            while (listaTags.contains(imagenAzar6.tag.toString()))
                imagenAzar6 = listaImagenes2().random()

            imagenAzar5.isInvisible = true
            imagenAzar6.isInvisible = true

            listaTags.add(imagenAzar6.tag.toString())
    }

    private fun validarImagenesMatriz1() {

        listaImagenes1().forEach {

            it.setOnClickListener {

                clicks++

                val boton = it.tag

                it.isEnabled = false

                if (listaTags.contains(boton)) {

                    hits++

                    Toast.makeText(
                            applicationContext,
                    "correcto",
                    Toast.LENGTH_SHORT
                    ).show()
                }

                habilitarBotonSiguiente()
            }
        }
    }

    private fun validarImagenesMatriz2() {

        listaImagenes2().forEach {

            it.setOnClickListener {

                clicks++

                val boton = it.tag

                it.isEnabled = false

                if (listaTags.contains(boton)) {

                    hits++

                    Toast.makeText(
                        applicationContext,
                        "correcto",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                habilitarBotonSiguiente()
            }
        }
    }

    private fun habilitarBotonSiguiente() {

        if (clicks == 6) {

            btnSiguienteAtencionDividida.isEnabled = true
            inhabilitarImagenes()
        }
    }

    private fun habilitarImagenes() {

        listaImagenes1().forEach {

            it.isEnabled = true
        }

        listaImagenes2().forEach {

            it.isEnabled = true
        }
    }

    private fun inhabilitarImagenes() {

        listaImagenes1().forEach {

            it.isEnabled = false
        }

        listaImagenes2().forEach {

            it.isEnabled = false
        }
    }

    private fun siguiente() {

        btnSiguienteAtencionDividida.setOnClickListener {

            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("AtenciónDividida").set(
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