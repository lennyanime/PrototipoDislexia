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

class AtencionDividida : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 2

    private lateinit var imagenAzar1: ImageView
    private lateinit var imagenAzar2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion_dividida)

        btnSiguienteAtencionDividida.isEnabled = false

        instruccionesAtencionDividida()

        siguiente()

        validarImagenMatriz1()

        validarImagenMatriz2()
    }

    private fun instruccionesAtencionDividida() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesAtencionDividida.setOnClickListener {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesAtencionDividida.isEnabled = false
                habilitarImagenes()
                desaparecerImagenes()
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

        return arrayListOf(imgCruz2AtencionDividida,
            imgCirculo2AtencionDividida,
            imgTriangulo2AtencionDividida,
            imgPentagono2AtencionDividida,
            imgEstrella2AtencionDividida,
            imgTrapecio2AtencionDividida,
            imgCuadrado2AtencionDividida,
            imgTrianguloInvertido2AtencionDividida,
            imgRectangulo2AtencionDividida)
    }

    private fun desaparecerImagenes() {

        imagenAzar1 = listaImagenes1().random()
        imagenAzar2 = listaImagenes2().random()

        while (imagenAzar1.tag == imagenAzar2.tag)
            imagenAzar1 = listaImagenes1().random()

        imagenAzar1.isInvisible = true
        imagenAzar2.isInvisible = true
    }

    private fun validarImagenMatriz1() {

        listaImagenes1().forEach {

            it.setOnClickListener {

                clicks++

                var boton = it.tag

                if (imagenAzar2.tag == boton){

                    hits++

                    Toast.makeText(applicationContext,
                        "correcto",
                        Toast.LENGTH_SHORT).show()
                }

                habilitarBotonSiguiente()
            }
        }
    }

    private fun validarImagenMatriz2() {

        listaImagenes2().forEach {

            it.setOnClickListener {

                clicks++

                var boton = it.tag

                if (imagenAzar1.tag == boton){

                    hits++

                    Toast.makeText(applicationContext,
                        "correcto",
                        Toast.LENGTH_SHORT).show()
                }

                habilitarBotonSiguiente()
            }
        }
    }

    private fun habilitarBotonSiguiente() {

        if (clicks == 2) {

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
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}