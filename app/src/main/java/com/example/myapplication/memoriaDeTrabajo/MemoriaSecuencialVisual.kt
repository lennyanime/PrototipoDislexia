package com.example.myapplication.memoriaDeTrabajo

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_memoria_secuencial_visual.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var imagenes: MutableList<ImageView>

class MemoriaSecuencialVisual : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 6

    private var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoria_secuencial_visual)

        //btnSiguienteMemoriaSV.isEnabled = false

        btnSecuenciaMemoriaSecuencialVisual.isVisible = false

        instruccionesMemoriaSecuencialVisual()

        siguiente()

        cambiarImagenes()

        mostrarSecuenciaImagenes()

    }

    private fun instruccionesMemoriaSecuencialVisual() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesMemoriaSecuencialVisual.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesMemoriaSecuencialVisual.isEnabled = false
                Thread.sleep(2000)
                btnSecuenciaMemoriaSecuencialVisual.isVisible = true
                inhabilitarImagenes()

                //imgPosicion1.setImageResource(R.drawable.coche)
                //imgPosicion1.setImageResource(pruebaImagenes().get(0))
            }
        }
    }

    private fun mostrarSecuenciaImagenes() {

        btnSecuenciaMemoriaSecuencialVisual.setOnClickListener {

            cont++

            when (cont) {

                1 -> imgPosicion1.isVisible = true
                2 -> imgPosicion2.isVisible = true
                3 -> imgPosicion4.isVisible = true
                4 -> imgPosicion3.isVisible = true
                5 -> imgPosicion5.isVisible = true
                6 -> {
                    habilitarImagenes()

                    cambiarImagenesCorrectas()

                    imgPosicion6.isVisible = true

                    btnSecuenciaMemoriaSecuencialVisual.isEnabled = false
                }
            }
        }
    }

    private fun cambiarImagenesCorrectas() {

        GlobalScope.launch {

            delay(6000)
            imgPosicion1.setImageResource(imagenesPrueba()[0])
            imgPosicion2.setImageResource(imagenesPrueba()[1])
            imgPosicion4.setImageResource(imagenesPrueba()[2])
            imgPosicion3.setImageResource(imagenesPrueba()[4])
            imgPosicion5.setImageResource(imagenesPrueba()[5])
            imgPosicion6.setImageResource(imagenesPrueba()[6])
        }
    }

    private fun imagenesPrueba(): ArrayList<Int> {

        var img1 = R.drawable.trianglebn
        var img2 = R.drawable.trapecio
        var img3 = R.drawable.circulobn
        var img4 = R.drawable.estrella
        var img5 = R.drawable.cruz
        var img6 = R.drawable.pentagono
        var img7 = R.drawable.cuadrado
        var img8 = R.drawable.triangulo
        var img9 = R.drawable.rectangle

        return arrayListOf(img1,
            img2,
            img3,
            img4,
            img5,
            img6,
            img7,
            img8,
            img9
        )
    }

    private fun cambiarImagenes() {

        imgPosicion1.setOnClickListener {

            imgPosicion1.setImageResource(imagenesPrueba().random())

            GlobalScope.launch {

                delay(1000)

                /*if(imgPosicion1 == listaImagenes()[0])
                    hits++*/
            }
        }

        imgPosicion2.setOnClickListener {

            imgPosicion2.setImageResource(imagenesPrueba().random())

            /* if(imgPosicion2 == listaImagenes()[1])
                 hits++*/
        }

        imgPosicion3.setOnClickListener {

            imgPosicion3.setImageResource(imagenesPrueba().random())

            /*if(imgPosicion3 == listaImagenes()[2])
                hits++*/
        }

        imgPosicion4.setOnClickListener {

            imgPosicion4.setImageResource(imagenesPrueba().random())

            /*if(imgPosicion4 == listaImagenes()[3])
                hits++*/
        }

        imgPosicion5.setOnClickListener {

            imgPosicion5.setImageResource(imagenesPrueba().random())

            /* if(imgPosicion5 == listaImagenes()[4])
                 hits++*/
        }

        imgPosicion6.setOnClickListener {

            imgPosicion6.setImageResource(imagenesPrueba().random())

            if (imgPosicion6 == validarImagenesCorrectas().getValue(key = "imagen6")) {

                hits++

                Toast.makeText(applicationContext,
                    "correcto",
                    Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(applicationContext,
                    "incorrecto",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarImagenesCorrectas(): HashMap<String, ImageView> {

        return hashMapOf(
            "imagen1" to imgPosicion1,
            "imagen2" to imgPosicion2,
            "imagen3" to imgPosicion3,
            "imagen4" to imgPosicion4,
            "imagen5" to imgPosicion5,
            "imagen6" to imgPosicion6
        )
    }

    private fun listaImagenes(): ArrayList<ImageView> {

        return arrayListOf(imgPosicion1,
            imgPosicion2,
            imgPosicion3,
            imgPosicion4,
            imgPosicion5,
            imgPosicion6)
    }

    private fun inhabilitarImagenes() {

        listaImagenes().forEach {

            it.isEnabled = false
        }
    }

    private fun habilitarImagenes() {

        listaImagenes().forEach {

            it.isEnabled = true
        }
    }

    private fun siguiente() {

        btnSiguienteMemoriaSV.setOnClickListener {

            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("MemoriaSecuencialVisual").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}

