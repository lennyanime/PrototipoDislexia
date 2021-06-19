package com.example.myapplication.competenciasLinguisticas

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_fonica.*
import kotlinx.coroutines.*
import java.util.*

private val words =
    arrayListOf("leña", "peña", "miedo", "riego", "playa", "raya", "reír", "freír",
        "flor", "ladrillo", "abrigo", "montaña", "araña", "cristal", "saltar",
        "mano", "sano", "bota", "gota", "paja", "caja")

class CompetenciaFonica : AppCompatActivity() {

    private var random = Random()

    private val map: Map<String, String> = mapOf("leña" to "peña")

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    private val PUNTAJE_MAXIMO: Int = 4
    private var hits1: Int = 0
    private var hits2: Int = 0
    private var hits3: Int = 0
    private var hits4: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_fonica)

        instruccionesPruebaFonica()

        inhabilitarBotones()

        ocultarBotones()

        ocultarTexto()

        randomWordRow1()
        randomWordRow2()
        randomWordRow3()
        randomWordRow4()

        //TODO: habilitar el bóton cuando se haya habilitado los botones de la última fila
        btnSiguienteCompetenciaFonica.isEnabled = false

        siguiente()
    }

    private fun instruccionesPruebaFonica() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesCFonica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesCFonica.isEnabled = false
                mostrarBotones()
                //habilitarBotones()
                btnRtaPositiva1CFonica.isEnabled = true
                btnRtaNegativaCFonica1.isEnabled = true
                habilitarTexto()
            }
        }
    }

    private fun randomWordRow1() {

        words.forEach { _ ->

            palabra1PruebaFonica.text = words[random.nextInt(words.size)]
            palabra2PruebaFonica.text = words[random.nextInt(words.size)]

            while (palabra1PruebaFonica.text.toString() == palabra2PruebaFonica.text.toString() || palabra1PruebaFonica.text
                    .toString().substring(palabra1PruebaFonica.length() - 3)
                !=
                palabra2PruebaFonica.text.toString()
                    .substring(palabra2PruebaFonica.length() - 3)
            )
                palabra2PruebaFonica.text = words[random.nextInt(words.size)]
        }

        btnRtaPositiva1CFonica.setOnClickListener {

            clicks++

            btnRtaPositiva1CFonica.isEnabled = false
            btnRtaNegativaCFonica1.isEnabled = true

            btnRtaPositiva2CFonica.isEnabled = true
            btnRtaNegativaCFonica2.isEnabled = true

            when {
                palabra1PruebaFonica.text.toString()
                    .substring(palabra1PruebaFonica.length() - 3)
                        ==
                        palabra2PruebaFonica.text.toString()
                            .substring(palabra1PruebaFonica.length() - 3) -> {
                    hits1++
                    hits1 = hitsAcierto(hits1)
                }
                else -> {
                    hits1--
                    hits1 = hitsError(hits1)
                }
            }
        }

        btnRtaNegativaCFonica1.setOnClickListener {

            clicks++

            btnRtaPositiva1CFonica.isEnabled = true
            btnRtaNegativaCFonica1.isEnabled = false

            btnRtaPositiva2CFonica.isEnabled = true
            btnRtaNegativaCFonica2.isEnabled = true

            when {
                palabra1PruebaFonica.text.toString()
                    .substring(palabra1PruebaFonica.length() - 3)
                        ==
                        palabra2PruebaFonica.text.toString()
                            .substring(palabra2PruebaFonica.length() - 3) -> {
                    hits1--
                    hits1 = hitsError(hits1)
                }
                else -> {
                    hits1++
                    hits1 = hitsAcierto(hits1)
                }
            }
        }

        //forma correcta de borrar un TextView
        words.remove("${palabra1PruebaFonica.text}")
        words.remove("${palabra2PruebaFonica.text}")
    }

    private fun randomWordRow2() {

        words.forEach {

            palabra3PruebaFonica.text = words[random.nextInt(words.size)]
            palabra4PruebaFonica.text = words[random.nextInt(words.size)]

            while (palabra4PruebaFonica.text.toString() == palabra3PruebaFonica.getText().toString()
            )
                palabra4PruebaFonica.text = words[random.nextInt(words.size)]
        }

        btnRtaPositiva2CFonica.setOnClickListener {

            clicks++

            btnRtaPositiva2CFonica.isEnabled = false
            btnRtaNegativaCFonica2.isEnabled = true

            btnRtaPositiva3CFonica.isEnabled = true
            btnRtaNegativaCFonica3.isEnabled = true

            if (palabra3PruebaFonica.text.toString()
                    .substring(palabra3PruebaFonica.length() - 3)
                ==
                palabra4PruebaFonica.text.toString()
                    .substring(palabra4PruebaFonica.length() - 3)
            ) {
                hits2++
                hits2 = hitsAcierto(hits2)
            } else {
                hits2--
                hits2 = hitsError(hits2)
            }
        }

        btnRtaNegativaCFonica2.setOnClickListener {

            clicks++

            btnRtaPositiva2CFonica.isEnabled = true
            btnRtaNegativaCFonica2.isEnabled = false

            btnRtaPositiva3CFonica.isEnabled = true
            btnRtaNegativaCFonica3.isEnabled = true

            if (palabra3PruebaFonica.text.toString()
                    .substring(palabra3PruebaFonica.length() - 3)
                ==
                palabra4PruebaFonica.text.toString()
                    .substring(palabra4PruebaFonica.length() - 3)
            ) {
                hits2--
                hits2 = hitsError(hits2)
            } else {
                hits2++
                hits2 = hitsAcierto(hits2)
            }
        }
        //forma correcta de borrar un TextView
        words.remove("${palabra3PruebaFonica.text}")
        words.remove("${palabra4PruebaFonica.text}")
    }

    private fun randomWordRow3() {

        words.forEach {

            palabra5PruebaFonica.text = words[random.nextInt(words.size)]
            palabra6PruebaFonica.text = words[random.nextInt(words.size)]

            while (palabra5PruebaFonica.text.toString() == palabra6PruebaFonica.text
                    .toString() || palabra5PruebaFonica.text.toString()
                    .substring(palabra5PruebaFonica.length() - 3)
                !=
                palabra6PruebaFonica.text.toString()
                    .substring(palabra6PruebaFonica.length() - 3)
            )
                palabra6PruebaFonica.text = words[random.nextInt(words.size)]
        }

        btnRtaPositiva3CFonica.setOnClickListener {

            clicks++

            btnRtaPositiva3CFonica.isEnabled = false
            btnRtaNegativaCFonica3.isEnabled = true

            btnRtaPositiva4CFonica.isEnabled = true
            btnRtaNegativaCFonica4.isEnabled = true

            if (palabra5PruebaFonica.text.toString()
                    .substring(palabra5PruebaFonica.length() - 3)
                ==
                palabra6PruebaFonica.text.toString()
                    .substring(palabra6PruebaFonica.length() - 3)
            ) {
                hits3++
                hits3 = hitsAcierto(hits3)
            } else {
                hits3--
                hits3 = hitsError(hits3)
            }
        }

        btnRtaNegativaCFonica3.setOnClickListener {

            clicks++

            btnRtaPositiva3CFonica.isEnabled = true
            btnRtaNegativaCFonica3.isEnabled = false

            btnRtaPositiva4CFonica.isEnabled = true
            btnRtaNegativaCFonica4.isEnabled = true

            if (palabra5PruebaFonica.text.toString()
                    .substring(palabra5PruebaFonica.length() - 3)
                ==
                palabra6PruebaFonica.text.toString()
                    .substring(palabra6PruebaFonica.length() - 3)
            ) {
                hits3--
                hits3 = hitsError(hits3)
            } else {
                hits3++
                hits3 = hitsAcierto(hits3)
            }
        }

        //forma correcta de borrar un TextView
        words.remove("${palabra5PruebaFonica.text}")
        words.remove("${palabra6PruebaFonica.text}")
    }

    private fun randomWordRow4() {


        words.forEach {

            palabra7PruebaFonica.text = words[random.nextInt(words.size)]
            palabra8PruebaFonica.text = words[random.nextInt(words.size)]

            while (palabra8PruebaFonica.text.toString() == palabra7PruebaFonica.text.toString()
            )
                palabra8PruebaFonica.text = words[random.nextInt(words.size)]
        }

        btnRtaPositiva4CFonica.setOnClickListener {

            btnSiguienteCompetenciaFonica.isEnabled = true
            btnRtaPositiva4CFonica.isEnabled = false
            btnRtaNegativaCFonica4.isEnabled = true

            clicks++
            when {
                palabra7PruebaFonica.text.toString()
                    .substring(palabra7PruebaFonica.length() - 3)
                        ==
                        palabra8PruebaFonica.text.toString()
                            .substring(palabra8PruebaFonica.length() - 3) -> {
                    hits4++
                    hits4 = hitsAcierto(hits4)
                }
                else -> {
                    hits4--
                    hits4 = hitsError(hits4)
                }
            }
        }

        btnRtaNegativaCFonica4.setOnClickListener {

            clicks++

            btnSiguienteCompetenciaFonica.isEnabled = true
            btnRtaPositiva4CFonica.isEnabled = true
            btnRtaNegativaCFonica4.isEnabled = false

            when {
                palabra7PruebaFonica.text.toString()
                    .substring(palabra7PruebaFonica.length() - 3)
                        ==
                        palabra8PruebaFonica.text.toString()
                            .substring(palabra8PruebaFonica.length() - 3) -> {
                    hits4--
                    hits4 = hitsError(hits4)
                }
                else -> {
                    hits4++
                    hits4 = hitsAcierto(hits4)
                }
            }
        }

        //forma correcta de borrar un TextView
        words.remove("${palabra7PruebaFonica.text}")
        words.remove("${palabra8PruebaFonica.text}")
    }

    private fun inhabilitarBotones() {

        val BOTONES_PRUEBA_FONICA = arrayListOf<Button>(btnRtaPositiva1CFonica,
            btnRtaNegativaCFonica1,
            btnRtaPositiva2CFonica,
            btnRtaNegativaCFonica2,
            btnRtaPositiva3CFonica,
            btnRtaNegativaCFonica3,
            btnRtaPositiva4CFonica,
            btnRtaNegativaCFonica4)

        BOTONES_PRUEBA_FONICA.forEach {

            it.isEnabled = false
        }
    }

    private fun ocultarBotones() {

        val BOTONES_PRUEBA_FONICA = arrayListOf<Button>(btnRtaPositiva1CFonica,
            btnRtaNegativaCFonica1,
            btnRtaPositiva2CFonica,
            btnRtaNegativaCFonica2,
            btnRtaPositiva3CFonica,
            btnRtaNegativaCFonica3,
            btnRtaPositiva4CFonica,
            btnRtaNegativaCFonica4)

        BOTONES_PRUEBA_FONICA.forEach {

            it.isVisible = false
        }
    }

    private fun mostrarBotones() {

        val BOTONES_PRUEBA_FONICA = arrayListOf<Button>(btnRtaPositiva1CFonica,
            btnRtaNegativaCFonica1,
            btnRtaPositiva2CFonica,
            btnRtaNegativaCFonica2,
            btnRtaPositiva3CFonica,
            btnRtaNegativaCFonica3,
            btnRtaPositiva4CFonica,
            btnRtaNegativaCFonica4)

        BOTONES_PRUEBA_FONICA.forEach {

            it.isVisible = true
        }
    }

    private fun ocultarTexto() {

        val EDIT_TEXT = arrayListOf<TextView>(palabra1PruebaFonica,
            palabra2PruebaFonica,
            palabra3PruebaFonica,
            palabra4PruebaFonica,
            palabra5PruebaFonica,
            palabra6PruebaFonica,
            palabra7PruebaFonica,
            palabra8PruebaFonica)

        EDIT_TEXT.forEach {

            it.isVisible = false
        }
    }

    private fun habilitarTexto() {

        val EDIT_TEXT = arrayListOf<TextView>(palabra1PruebaFonica,
            palabra2PruebaFonica,
            palabra3PruebaFonica,
            palabra4PruebaFonica,
            palabra5PruebaFonica,
            palabra6PruebaFonica,
            palabra7PruebaFonica,
            palabra8PruebaFonica)

        EDIT_TEXT.forEach {

            it.isVisible = true
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

    private fun siguiente() {

        btnSiguienteCompetenciaFonica.setOnClickListener {

            hits = hits1 + hits2 + hits3 + hits4
            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaFónica").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}