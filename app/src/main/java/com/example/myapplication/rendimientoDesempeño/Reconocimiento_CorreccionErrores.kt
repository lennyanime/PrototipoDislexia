package com.example.myapplication.rendimientoDesempeño

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_reconocimiento_correccion_errores.*

class Reconocimiento_CorreccionErrores : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 3

    private var hitsPrueba1: Int = 0

    private var hitsPrueba2: Int = 0

    private var hitsPrueba3: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reconocimiento_correccion_errores)

        instruccionesCorreccionErrores()

        validacionPrueba1()

        validacionPrueba2()

        validacionPrueba3()

        siguiente()
    }

    private fun instruccionesCorreccionErrores() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesCorreccionErrores.setOnClickListener {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesCorreccionErrores.isEnabled = false
                habilitarPrueba()
            }
        }
    }

    private fun habilitarPrueba() {

        prueba1.isVisible = true
        prueba2.isVisible = true
        prueba3.isVisible = true

        botonesPrueba1.isVisible = true
        botonesPrueba2.isVisible = true
        botonesPrueba3.isVisible = true
    }

    private fun validacionPrueba1() {

        btnOpcion1Palabra1.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba1--
            hitsPrueba1 = hitsError(hitsPrueba1)

            btnOpcion1Palabra1.isEnabled = false
            btnOpcion2Palabra1.isEnabled = true
            btnOpcion3Palabra1.isEnabled = true
        }

        btnOpcion2Palabra1.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba1++
            hitsPrueba1 = hitsAcierto(hitsPrueba1)

            btnOpcion2Palabra1.isEnabled = false
            btnOpcion1Palabra1.isEnabled = true
            btnOpcion3Palabra1.isEnabled = true
        }

        btnOpcion3Palabra1.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba1--
            hitsPrueba1 = hitsError(hitsPrueba1)

            btnOpcion3Palabra1.isEnabled = false
            btnOpcion2Palabra1.isEnabled = true
            btnOpcion1Palabra1.isEnabled = true
        }
    }

    private fun validacionPrueba2() {

        btnOpcion1Palabra2.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba2--
            hitsPrueba2 = hitsError(hitsPrueba2)

            btnOpcion1Palabra2.isEnabled = false
            btnOpcion2Palabra2.isEnabled = true
            btnOpcion3Palabra2.isEnabled = true
        }

        btnOpcion2Palabra2.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba2--
            hitsPrueba2 = hitsError(hitsPrueba2)

            btnOpcion2Palabra2.isEnabled = false
            btnOpcion1Palabra2.isEnabled = true
            btnOpcion3Palabra2.isEnabled = true
        }

        btnOpcion3Palabra2.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba2++
            hitsPrueba2 = hitsAcierto(hitsPrueba2)

            btnOpcion3Palabra2.isEnabled = false
            btnOpcion2Palabra2.isEnabled = true
            btnOpcion1Palabra2.isEnabled = true
        }
    }

    private fun validacionPrueba3() {

        btnOpcion1Palabra3.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba3++
            hitsPrueba3 = hitsAcierto(hitsPrueba3)

            btnOpcion1Palabra3.isEnabled = false
            btnOpcion2Palabra3.isEnabled = true
            btnOpcion3Palabra3.isEnabled = true
        }

        btnOpcion2Palabra3.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba3--
            hitsPrueba3 = hitsError(hitsPrueba3)

            btnOpcion2Palabra3.isEnabled = false
            btnOpcion1Palabra3.isEnabled = true
            btnOpcion3Palabra3.isEnabled = true
        }

        btnOpcion3Palabra3.setOnClickListener {

            clicks++

            habilitarBotonSiguiente()
            hitsPrueba3--
            hitsPrueba3 = hitsError(hitsPrueba3)

            btnOpcion3Palabra3.isEnabled = false
            btnOpcion2Palabra3.isEnabled = true
            btnOpcion1Palabra3.isEnabled = true
        }
    }

    private fun hitsError(restarHits: Int): Int {
        var _restarHits = restarHits

        if (_restarHits == 0 || _restarHits < 0)
            _restarHits = 0

        if (_restarHits == 1)
            _restarHits--

        return _restarHits
    }

    private fun hitsAcierto(acierto: Int): Int {
        var _acierto = acierto

        if (_acierto == 1 || _acierto > 1)
            _acierto = 1

        if (_acierto == 0)
            _acierto++

        return _acierto
    }

    private fun habilitarBotonSiguiente() {

        if (clicks == 3)
            btnSiguienteCorreccionErrores.isEnabled = true
    }

    private fun siguiente() {

        btnSiguienteCorreccionErrores.setOnClickListener {

            hits = hitsPrueba1 + hitsPrueba2 + hitsPrueba3

            Toast.makeText(
                applicationContext,
                "$hits",
                Toast.LENGTH_SHORT
            ).show()

            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("ReconocimientoCorrecciónErrores").set(
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