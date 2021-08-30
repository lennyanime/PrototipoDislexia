package com.example.myapplication.competenciasLinguisticas

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_fonica.*
import java.util.*

private val palabrasCorrectas =
    arrayListOf(
        "miedo", "riego", "reír", "freír",
        "flor", "ladrillo", "abrigo", "cristal", "saltar",
        "mano", "sano", "playa", "raya", "paja", "caja"
    )

class CompetenciaFonica : AppCompatActivity() {

    private val map: HashMap<String, String> = hashMapOf(
        "leña" to "peña", "bota" to "gota", "montaña" to "araña"
    )

    private val DB = FirebaseFirestore.getInstance()
    private val user = Firebase.auth.currentUser

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

        btnSiguienteCompetenciaFonica.isEnabled = false

        siguiente()

        menu()
    }

    private fun instruccionesPruebaFonica() {

        val mp = MediaPlayer.create(this, R.raw.competenciafonica)

        btnInstruccionesCFonica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(17000)
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

        if (map.keys.isNotEmpty() && map.values.isNotEmpty()) {

            palabra1PruebaFonica.text = map.keys.random()
            palabra2PruebaFonica.text = map.getValue(palabra1PruebaFonica.text.toString())
        }

        btnRtaPositiva1CFonica.setOnClickListener {

            clicks++

            btnRtaPositiva1CFonica.isEnabled = false
            btnRtaPositiva1CFonica.setBackgroundColor(Color.GRAY)

            btnRtaNegativaCFonica1.isEnabled = true
            btnRtaNegativaCFonica1.setBackgroundColor(Color.TRANSPARENT)

            btnRtaPositiva2CFonica.isEnabled = true
            btnRtaNegativaCFonica2.isEnabled = true

            hits1++
            hits1 = hitsAcierto(hits1)
        }

        btnRtaNegativaCFonica1.setOnClickListener {

            clicks++

            btnRtaPositiva1CFonica.isEnabled = true
            btnRtaPositiva1CFonica.setBackgroundColor(Color.TRANSPARENT)

            btnRtaNegativaCFonica1.isEnabled = false
            btnRtaNegativaCFonica1.setBackgroundColor(Color.GRAY)

            btnRtaPositiva2CFonica.isEnabled = true
            btnRtaNegativaCFonica2.isEnabled = true

            hits1--
            hits1 = hitsError(hits1)
        }
    }

    private fun randomWordRow2() {

        palabrasCorrectas.forEach {

            palabra3PruebaFonica.text = palabrasCorrectas.random()
            palabra4PruebaFonica.text = palabrasCorrectas.random()

            while (palabra4PruebaFonica.text.toString() == palabra3PruebaFonica.text.toString()
            )
                palabra4PruebaFonica.text = palabrasCorrectas.random()
        }

        btnRtaPositiva2CFonica.setOnClickListener {

            clicks++

            btnRtaPositiva2CFonica.isEnabled = false
            btnRtaPositiva2CFonica.setBackgroundColor(Color.GRAY)

            btnRtaNegativaCFonica2.isEnabled = true
            btnRtaNegativaCFonica2.setBackgroundColor(Color.TRANSPARENT)

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
            btnRtaPositiva2CFonica.setBackgroundColor(Color.TRANSPARENT)

            btnRtaNegativaCFonica2.isEnabled = false
            btnRtaNegativaCFonica2.setBackgroundColor(Color.GRAY)

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
        palabrasCorrectas.remove("${palabra3PruebaFonica.text}")
        palabrasCorrectas.remove("${palabra4PruebaFonica.text}")
    }

    private fun randomWordRow3() {

        if (map.keys.isNotEmpty() && map.values.isNotEmpty()) {

            palabra5PruebaFonica.text = map.keys.random()
            palabra6PruebaFonica.text = map.getValue(palabra5PruebaFonica.text.toString())

            while (palabra5PruebaFonica.text == palabra1PruebaFonica.text) {
                palabra5PruebaFonica.text = map.keys.random()
                palabra6PruebaFonica.text = map.getValue(palabra5PruebaFonica.text.toString())
            }
        }

        btnRtaPositiva3CFonica.setOnClickListener {

            clicks++

            btnRtaPositiva3CFonica.isEnabled = false
            btnRtaPositiva3CFonica.setBackgroundColor(Color.GRAY)

            btnRtaNegativaCFonica3.isEnabled = true
            btnRtaNegativaCFonica3.setBackgroundColor(Color.TRANSPARENT)

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
            btnRtaPositiva3CFonica.setBackgroundColor(Color.TRANSPARENT)

            btnRtaNegativaCFonica3.isEnabled = false
            btnRtaNegativaCFonica3.setBackgroundColor(Color.GRAY)

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
    }

    private fun randomWordRow4() {

        palabrasCorrectas.forEach {

            palabrasCorrectas.random()
            palabra7PruebaFonica.text = palabrasCorrectas.random()
            palabra8PruebaFonica.text = palabrasCorrectas.random()

            while (palabra8PruebaFonica.text.toString() == palabra7PruebaFonica.text.toString()
            )
                palabra8PruebaFonica.text = palabrasCorrectas.random()
        }

        btnRtaPositiva4CFonica.setOnClickListener {

            btnSiguienteCompetenciaFonica.isEnabled = true

            btnRtaPositiva4CFonica.isEnabled = false
            btnRtaPositiva4CFonica.setBackgroundColor(Color.GRAY)

            btnRtaNegativaCFonica4.isEnabled = true
            btnRtaNegativaCFonica4.setBackgroundColor(Color.TRANSPARENT)

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
            btnRtaPositiva4CFonica.setBackgroundColor(Color.TRANSPARENT)

            btnRtaNegativaCFonica4.setBackgroundColor(Color.GRAY)
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
        palabrasCorrectas.remove("${palabra7PruebaFonica.text}")
        palabrasCorrectas.remove("${palabra8PruebaFonica.text}")
    }

    private fun botonesPruebaFonica(): ArrayList<Button> {

        return arrayListOf(
            btnRtaPositiva1CFonica,
            btnRtaNegativaCFonica1,
            btnRtaPositiva2CFonica,
            btnRtaNegativaCFonica2,
            btnRtaPositiva3CFonica,
            btnRtaNegativaCFonica3,
            btnRtaPositiva4CFonica,
            btnRtaNegativaCFonica4
        )
    }

    private fun inhabilitarBotones() {

        botonesPruebaFonica().forEach {

            it.isEnabled = false
        }
    }

    private fun ocultarBotones() {

        botonesPruebaFonica().forEach {

            it.isVisible = false
        }
    }

    private fun mostrarBotones() {

        botonesPruebaFonica().forEach {

            it.isVisible = true
        }
    }

    private fun textoPruebaFonica(): ArrayList<TextView> {

        return arrayListOf(
            palabra1PruebaFonica,
            palabra2PruebaFonica,
            palabra3PruebaFonica,
            palabra4PruebaFonica,
            palabra5PruebaFonica,
            palabra6PruebaFonica,
            palabra7PruebaFonica,
            palabra8PruebaFonica
        )
    }

    private fun ocultarTexto() {

        textoPruebaFonica().forEach {

            it.isVisible = false
        }
    }

    private fun habilitarTexto() {

        textoPruebaFonica().forEach {

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

    private fun menu() {

        btnMenuCF.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun siguiente() {

        btnSiguienteCompetenciaFonica.setOnClickListener {

            hits = hits1 + hits2 + hits3 + hits4
            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaFónica").set(
                    hashMapOf(
                        "Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses
                    )
                )
            }

            obtenerDocumento(
                user?.email.toString(),
                "CompetenciaSilábica",
                CompetenciaSilabica()
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