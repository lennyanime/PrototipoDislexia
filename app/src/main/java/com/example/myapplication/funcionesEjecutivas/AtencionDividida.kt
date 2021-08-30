package com.example.myapplication.funcionesEjecutivas

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.myapplication.Componentes
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

    private val user = Firebase.auth.currentUser

    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion_dividida)

        btnSiguienteAtencionDividida.isEnabled = false

        btnIniciarPruebaAtencionDividida.isEnabled = false

        instruccionesAtencionDividida()

        iniciarPrueba()

        siguiente()

        menu()

        validarImagenesMatriz2()

        validarImagenesMatriz1()

        listaTags = mutableListOf()
    }

    private fun menu() {

        btnMenuPrincipalAtencionDividida.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun instruccionesAtencionDividida() {

        val mp = MediaPlayer.create(this, R.raw.atenciondividida)

        if (!mp.isPlaying) {
            btnInstruccionesAtencionDividida.setOnClickListener {
                mp.start()
                Thread.sleep(14000)
                btnInstruccionesAtencionDividida.isEnabled = false
                habilitarImagenes()
                btnIniciarPruebaAtencionDividida.isEnabled = true
            }
        }
    }

    private fun iniciarPrueba() {

        btnIniciarPruebaAtencionDividida.setOnClickListener {

            btnIniciarPruebaAtencionDividida.isEnabled = false

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

        var imagenAzar1 = listaImagenes1().random()
        var imagenAzar2 = listaImagenes2().random()

        while (listaTags.contains(imagenAzar1.tag.toString()) || listaTags.contains(imagenAzar2.tag.toString())) {

            imagenAzar1 = listaImagenes1().random()
            imagenAzar2 = listaImagenes2().random()
        }

        listaTags.add(imagenAzar1.tag.toString())
        listaTags.add(imagenAzar2.tag.toString())

        imagenAzar1.isInvisible = true
        imagenAzar2.isInvisible = true

        listaImagenes1().remove(imagenAzar1)
        listaImagenes2().remove(imagenAzar2)
    }

    suspend fun desaparecerImagenes2() {

        delay(2000)

        var imagenAzar3 = listaImagenes1().random()
        var imagenAzar4 = listaImagenes2().random()

        while (listaTags.contains(imagenAzar3.tag.toString()) || listaTags.contains(imagenAzar4.tag.toString())) {
            imagenAzar3 = listaImagenes1().random()
            imagenAzar4 = listaImagenes2().random()
        }

        imagenAzar3.isInvisible = true
        imagenAzar4.isInvisible = true

        listaTags.add(imagenAzar3.tag.toString())
        listaTags.add(imagenAzar4.tag.toString())

        listaImagenes1().remove(imagenAzar3)
        listaImagenes2().remove(imagenAzar4)
    }

    suspend fun desaparecerImagenes3() {

        delay(4000)

        var imagenAzar5 = listaImagenes1().random()
        var imagenAzar6 = listaImagenes2().random()

        while (listaTags.contains(imagenAzar5.tag.toString()) || listaTags.contains(imagenAzar6.tag.toString())) {
            imagenAzar5 = listaImagenes1().random()
            imagenAzar6 = listaImagenes2().random()
        }

        imagenAzar5.isInvisible = true
        imagenAzar6.isInvisible = true

        listaTags.add(imagenAzar5.tag.toString())
        listaTags.add(imagenAzar6.tag.toString())

        listaImagenes1().remove(imagenAzar5)
        listaImagenes2().remove(imagenAzar6)
    }

    private fun validarImagenesMatriz1() {

        listaImagenes1().forEach {

            it.setOnClickListener {

                clicks++

                val boton = it.tag

                it.isEnabled = false

                if (listaTags.contains(boton)) {

                    hits++
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

            obtenerDocumento(
                user?.email.toString(),
                "AtenciónSelectiva",
                AtencionSelectiva()
            )
        }
    }

    private fun obtenerDocumento(
        correo: String,
        documento: String,
        activity: Activity
    ) {

        val document = DB.collection(correo).document(documento)
        document.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val intent = Intent(this, Componentes()::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, activity::class.java)
                    startActivity(intent)
                }
            }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}