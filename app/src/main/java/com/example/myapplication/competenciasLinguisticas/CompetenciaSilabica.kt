package com.example.myapplication.competenciasLinguisticas

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_silabica.*
import java.lang.reflect.Type

private lateinit var silabas: MutableList<String>

class CompetenciaSilabica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
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

        btnSiguienteCompetenciaSilabica.setEnabled(false)

        btnConfirmarTextoPruebaSilabica.setEnabled(false)

        txtSilabas.setVisibility(View.INVISIBLE)

        silabas = mutableListOf()

    }

    private fun instruccionesCompetenciaSilabica() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesSilabica.setOnClickListener {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesSilabica.setEnabled(false)
                btnLetra1PruebaSilabica.setEnabled(true)
            }
        }
    }

    private fun audioPalabra1() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        //TODO: aquí va el audio de la palabra de esta prueba
        btnLetra1PruebaSilabica.setOnClickListener {

            if (!mp.isPlaying) {

                mp.start()
                Thread.sleep(2000)
                btnLetra1PruebaSilabica.setEnabled(false)
                btnConfirmarTextoPruebaSilabica.setEnabled(true)
                txtSilabas.setVisibility(View.VISIBLE)
            }
        }
    }

    private fun noSugerencias() {

        txtSilabas.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
    }

    private fun validarTexto_OcultarTeclado() {

        btnConfirmarTextoPruebaSilabica.setOnClickListener {

            val regex = "^[a-zA-Z]+$".toRegex()

            if (!txtSilabas.text.toString().toLowerCase()
                    .contains(regex) || txtSilabas.text.isEmpty()
            ) {
                ocultarTeclado(txtSilabas)
                Toast.makeText(applicationContext,
                    "Tu respuesta no es una palabra o no escribiste nada",
                    Toast.LENGTH_SHORT).show()
                txtSilabas.setText(null)
            } else {

                silabas.add(txtSilabas.text.toString())
                txtSilabas.setText(null)
                ocultarTeclado(txtSilabas)

                when (silabas.size) {

                    1 -> {

                        btnConfirmarTextoPruebaSilabica.setEnabled(false)
                        btnLetra2PruebaSilabica.setEnabled(true)
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
                        btnConfirmarTextoPruebaSilabica.setEnabled(false)
                        btnLetra3PruebaSilabica.setEnabled(true)
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

                        btnConfirmarTextoPruebaSilabica.setEnabled(false)
                        btnLetra4PruebaSilabica.setEnabled(true)
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

                        btnConfirmarTextoPruebaSilabica.setEnabled(false)
                        btnSiguienteCompetenciaSilabica.setEnabled(true)
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

                    val mp = MediaPlayer.create(this, R.raw.lenny2)
                    if (!mp.isPlaying) {
                        mp.start()
                        Thread.sleep(2000)
                        btnLetra2PruebaSilabica.setEnabled(false)
                        btnConfirmarTextoPruebaSilabica.setEnabled(true)
                        txtSilabas.visibility = View.VISIBLE
                    }
                }

                btnLetra3PruebaSilabica.setOnClickListener {

                    val mp = MediaPlayer.create(this, R.raw.lenny2)
                    if (!mp.isPlaying) {
                        mp.start()
                        Thread.sleep(2000)
                        btnLetra3PruebaSilabica.setEnabled(false)
                        btnConfirmarTextoPruebaSilabica.setEnabled(true)
                        txtSilabas.visibility = View.VISIBLE
                    }
                }

                btnLetra4PruebaSilabica.setOnClickListener {

                    val mp = MediaPlayer.create(this, R.raw.lenny2)
                    if (!mp.isPlaying) {
                        mp.start()
                        Thread.sleep(2000)
                        btnLetra4PruebaSilabica.setEnabled(false)
                        btnConfirmarTextoPruebaSilabica.setEnabled(true)
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

            it.setEnabled(false)
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
        }
    }
}


