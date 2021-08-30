package com.example.myapplication.competenciasLinguisticas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_silabica.*
import java.util.*

private lateinit var silabas: MutableList<String>

class CompetenciaSilabica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()

    private val user = Firebase.auth.currentUser

    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_silabica)

        instruccionesCompetenciaSilabica()

        audioPalabra1()

        noSugerencias()

        validarTexto_OcultarTeclado()

        inhabilitarBotones()

        siguiente()

        btnSiguienteCompetenciaSilabica.isEnabled = false

        btnConfirmarTextoPruebaSilabica.isEnabled = false

        txtSilabas.visibility = View.INVISIBLE

        silabas = mutableListOf()

        menu()
    }

    private fun menu() {

        btnMenuCSilabica.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun instruccionesCompetenciaSilabica() {

        val mp = MediaPlayer.create(this, R.raw.competenciasilabica)

        if (!mp.isPlaying) {
            btnInstruccionesSilabica.setOnClickListener {
                mp.start()
                Thread.sleep(26000)
                btnInstruccionesSilabica.isEnabled = false
                btnLetra1PruebaSilabica.isEnabled = true
            }
        }
    }

    private fun audioPalabra1() {

        val mp = MediaPlayer.create(this, R.raw.palabra1competenciasilabica)

        btnLetra1PruebaSilabica.setOnClickListener {

            if (!mp.isPlaying) {

                mp.start()
                Thread.sleep(1000)
                btnLetra1PruebaSilabica.isEnabled = false
                btnConfirmarTextoPruebaSilabica.isEnabled = true
                txtSilabas.visibility = View.VISIBLE
            }
        }
    }

    private fun noSugerencias() {

        txtSilabas.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
    }

    private fun validarTexto_OcultarTeclado() {

        btnConfirmarTextoPruebaSilabica.setOnClickListener {

            val regex = "^[a-zA-Z]+$".toRegex()

            if (!txtSilabas.text.toString().trim().toLowerCase(Locale.ROOT)
                    .contains(regex) || txtSilabas.text.isEmpty()
            ) {
                ocultarTeclado(txtSilabas)
                Toast.makeText(applicationContext,
                    "Tu respuesta no es una palabra o no escribiste nada",
                    Toast.LENGTH_SHORT).show()
                txtSilabas.text = null
            } else {

                silabas.add(txtSilabas.text.toString())
                txtSilabas.text = null
                ocultarTeclado(txtSilabas)

                when (silabas.size) {

                    1 -> {

                        btnConfirmarTextoPruebaSilabica.isEnabled = false
                        btnLetra2PruebaSilabica.isEnabled = true
                        txtSilabas.visibility = View.INVISIBLE

                        if (silabas.contains("plen")) {
                            hits++
                            borrarSilaba(1)
                        } else {
                            misses++
                            borrarSilaba(1)
                        }
                    }
                    2 -> {
                        btnConfirmarTextoPruebaSilabica.isEnabled = false
                        btnLetra3PruebaSilabica.isEnabled = true
                        txtSilabas.visibility = View.INVISIBLE

                        if (silabas.contains("crep")) {
                            hits++
                            borrarSilaba(2)
                        } else {
                            misses++
                            borrarSilaba(2)
                        }
                    }
                    3 -> {

                        btnConfirmarTextoPruebaSilabica.isEnabled = false
                        btnLetra4PruebaSilabica.isEnabled = true
                        txtSilabas.visibility = View.INVISIBLE

                        if (silabas.contains("dal")) {
                            hits++
                            borrarSilaba(3)
                        } else {
                            misses++
                            borrarSilaba(3)
                        }
                    }
                    4 -> {

                        btnConfirmarTextoPruebaSilabica.isEnabled = false
                        btnSiguienteCompetenciaSilabica.isEnabled = true
                        txtSilabas.visibility = View.INVISIBLE

                        if (silabas.contains("pri")) {
                            hits++
                        } else {
                            misses++
                        }
                    }
                    else -> null
                }

                btnLetra2PruebaSilabica.setOnClickListener {

                    val mp = MediaPlayer.create(this, R.raw.palabra2competenciasilabica)
                    if (!mp.isPlaying) {
                        mp.start()
                        Thread.sleep(1000)
                        btnLetra2PruebaSilabica.isEnabled = false
                        btnConfirmarTextoPruebaSilabica.isEnabled = true
                        txtSilabas.visibility = View.VISIBLE
                    }
                }

                btnLetra3PruebaSilabica.setOnClickListener {

                    val mp = MediaPlayer.create(this, R.raw.palabra3competenciasilabica)
                    if (!mp.isPlaying) {
                        mp.start()
                        Thread.sleep(1000)
                        btnLetra3PruebaSilabica.isEnabled = false
                        btnConfirmarTextoPruebaSilabica.isEnabled = true
                        txtSilabas.visibility = View.VISIBLE
                    }
                }

                btnLetra4PruebaSilabica.setOnClickListener {

                    val mp = MediaPlayer.create(this, R.raw.palabra4competenciasilabica)
                    if (!mp.isPlaying) {
                        mp.start()
                        Thread.sleep(1000)
                        btnLetra4PruebaSilabica.isEnabled = false
                        btnConfirmarTextoPruebaSilabica.isEnabled = true
                        txtSilabas.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun borrarSilaba(x: Int){

        silabas.drop(x)
    }

    private fun ocultarTeclado(view: View) {

        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun inhabilitarBotones() {

        val BOTONES_PRUEBA_SILABICA = arrayListOf<Button>(btnLetra1PruebaSilabica,
            btnLetra2PruebaSilabica,
            btnLetra3PruebaSilabica,
            btnLetra4PruebaSilabica)

        BOTONES_PRUEBA_SILABICA.forEach {

            it.isEnabled = false
        }
    }

    private fun siguiente() {

        btnSiguienteCompetenciaSilabica.setOnClickListener {
            clicks = silabas.size
            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaSilábica").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }

            obtenerDocumento(
                user?.email.toString(),
                "CompetenciaLéxica",
                CompetenciaLexica()
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


