package com.example.myapplication.rendimientoDesempeño

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_comprension_de_lectura.*

class ComprensionLectura : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private val PUNTAJE_MAXIMO: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprension_de_lectura)

        instruccionesComprensionLectura()

        respuestaCorrecta1()

        respuestaCorrecta2()

        respuestaCorrecta3()

        siguiente()
    }

    private fun instruccionesComprensionLectura() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesComprensionLectura.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesComprensionLectura.isEnabled = false
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun respuestaCorrecta1() {

        chkRespuesta2.setOnClickListener {

            clicks++

            if (chkRespuesta2.tag == "correcto") {

                hits++
                hits = hitsAcierto(hits)
                Toast.makeText(
                    applicationContext,
                    "correcto, $hits",
                    Toast.LENGTH_SHORT
                ).show()
            }

            chkRespuesta1.isChecked = false
            chkRespuesta3.isChecked = false
        }

        chkRespuesta1.setOnClickListener {

            clicks++

            hits--
            hits = hitsError(hits)
            chkRespuesta2.isChecked = false
            chkRespuesta3.isChecked = false
        }

        chkRespuesta3.setOnClickListener {

            clicks++

            hits--
            hits = hitsError(hits)
            chkRespuesta2.isChecked = false
            chkRespuesta1.isChecked = false
        }
    }


    private fun respuestaCorrecta2() {

        chkRespuesta6.setOnClickListener {

            clicks++

            if (chkRespuesta6.tag == "correcto") {

                hits++
                hits = hitsAcierto(hits)
                Toast.makeText(
                    applicationContext,
                    "correcto, $hits",
                    Toast.LENGTH_SHORT
                ).show()
            }

            chkRespuesta4.isChecked = false
            chkRespuesta5.isChecked = false
        }

        chkRespuesta5.setOnClickListener {

            clicks++

            hits--
            hits = hitsError(hits)

            chkRespuesta4.isChecked = false
            chkRespuesta6.isChecked = false
        }

        chkRespuesta4.setOnClickListener {

            clicks++

            hits--
            hits = hitsError(hits)

            chkRespuesta5.isChecked = false
            chkRespuesta6.isChecked = false
        }
    }

    private fun respuestaCorrecta3() {

        chkRespuesta7.setOnClickListener {

            clicks++

            if (chkRespuesta6.tag == "correcto") {

                hits++
                hits = hitsAcierto(hits)
                Toast.makeText(
                    applicationContext,
                    "correcto, $hits",
                    Toast.LENGTH_SHORT
                ).show()
            }

            chkRespuesta8.isChecked = false
            chkRespuesta9.isChecked = false
        }

        chkRespuesta8.setOnClickListener {

            clicks++

            hits--
            hits = hitsError(hits)

            chkRespuesta7.isChecked = false
            chkRespuesta9.isChecked = false
        }

        chkRespuesta9.setOnClickListener {

            clicks++

            hits--
            hits = hitsError(hits)

            chkRespuesta7.isChecked = false
            chkRespuesta8.isChecked = false
        }
    }

    private fun hitsAcierto(acierto: Int): Int {
        var _acierto = acierto

        if (_acierto == 1 || _acierto > 1)
            _acierto = 1

        if (_acierto == 0)
            _acierto++

        return _acierto
    }

    private fun hitsError(restarHits: Int): Int {
        var _restarHits = restarHits

        if (_restarHits == 0 || _restarHits < 0)
            _restarHits = 0

        if (_restarHits == 1)
            _restarHits--

        return _restarHits
    }

    private fun siguiente() {

        btnSiguienteComprensionLectura.setOnClickListener {

            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("ComprensiónLectura").set(
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