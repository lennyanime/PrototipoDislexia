package com.example.myapplication.competenciasLinguisticas

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_lexica.*
import java.util.*

class CompetenciaLexica : AppCompatActivity() {
    //, "calor", "arte", "mar"
    private var wordsCLexica = arrayListOf("árbol", "calor", "arte", "mar")
    private var random = Random()

    var grabar: TextView? = null
    private val RECOGNIZE_SPEECH_ACTIVITY = 1

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_lexica)

        instruccionesPruebaLexica()

        reconocimientoDeVozBloqueado()

        palabraAleatoria()

        limpiarTexto()

        siguiente()

        botonesBloqueadosDefault()

        grabar = findViewById(R.id.txtMostrarPalabra)

        btnSiguienteCompetenciaLexica.isEnabled = false

    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RECOGNIZE_SPEECH_ACTIVITY ->
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    val strSpeech2Text = speech?.get(0)
                    grabar?.setText(strSpeech2Text)
                }
            else -> {
            }
        }
    }

    fun speak(v: View) {
        val intentActionRecognizeSpeech = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-co")
        try {
            startActivityForResult(intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY)
            reconocimientoDeVozBloqueado()
            btnLimpiarTexto.setEnabled(true)



        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext,
                "Tu dispositivo no soporta el reconocimiento por voz",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun instruccionesPruebaLexica() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesCompetenciaLexica.setOnClickListener {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesCompetenciaLexica.setEnabled(false)
                btnMostrarPalabra.setEnabled(true)
            }
        }
    }

    private fun palabraAleatoria() {

        btnMostrarPalabra.setOnClickListener {
            wordLexica.setText(wordsCLexica[random.nextInt(wordsCLexica.size)])
            wordsCLexica.remove("${wordLexica.getText()}")

            btnMostrarPalabra.setEnabled(false)
            reconocimientoDeVozHabilitado()
        }
    }

    private fun limpiarTexto() {

        btnLimpiarTexto.setOnClickListener {

           /* Toast.makeText(
                applicationContext,
                palabrasAudio.firstOrNull(),
                Toast.LENGTH_SHORT
            ).show()*/

            if(wordLexica.text.toString() == txtMostrarPalabra.text.toString()){
                    //palabrasAudio.add(txtMostrarPalabra.getText().toString())
                    hits++
            }else{
                misses++
            }

            if(wordsCLexica.isEmpty())
                btnSiguienteCompetenciaLexica.isEnabled = true

            //if (txtMostrarPalabra.toString().toLowerCase().equals(wordLexica.toString().toLowerCase())){
            if (txtMostrarPalabra.toString() != null && wordLexica.toString() != null) {
                wordLexica.setText("")
                txtMostrarPalabra.setText("")
                btnMostrarPalabra.setEnabled(true)
                reconocimientoDeVozBloqueado()
                btnLimpiarTexto.setEnabled(false)
            }

            if (wordsCLexica.isEmpty()) {

                wordLexica.setText("")
                txtMostrarPalabra.setText("")
                btnMostrarPalabra.setEnabled(false)
                reconocimientoDeVozBloqueado()
                btnLimpiarTexto.setEnabled(false)
                btnSiguienteCompetenciaLexica.setEnabled(true)
            }
        }
    }

    private fun reconocimientoDeVozBloqueado() {
        imgHablar.setEnabled(false)
    }

    private fun reconocimientoDeVozHabilitado() {
        imgHablar.setEnabled(true)
    }

    private fun botonesBloqueadosDefault() {
        //btnSiguienteCompetenciaLexica.setEnabled(false)
        btnLimpiarTexto.setEnabled(false)
        btnMostrarPalabra.setEnabled(false)
    }

    private fun siguiente() {

        clicks = wordsCLexica.size
        btnSiguienteCompetenciaLexica.setOnClickListener {

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaLéxica").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}