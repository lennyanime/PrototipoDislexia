package com.example.myapplication.rendimientoDesempeño

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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ortografia_a__velocidad_e.*
import java.util.*

private lateinit var frases: MutableList<String>

class OrtografiaArbitraria_VelocidadEscritura : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ortografia_a__velocidad_e)

        btnConfirmarFraseOrtografiaArbitraria.isEnabled = false

        inhabilitarBotones()

        noSugerencias()

        instruccionesOrtografiaArbitrariaVelocidadEscritura()

        escucharFrases()

        validarFrases()

        siguiente()

        btnSiguienteOrtografiaArbitraria.isEnabled = false

        btnConfirmarFraseOrtografiaArbitraria.isEnabled = false

        txtFrases.visibility = View.INVISIBLE

        frases = mutableListOf()
    }

    private fun instruccionesOrtografiaArbitrariaVelocidadEscritura() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesOrtografiaVelocidadEscritura.setOnClickListener {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesOrtografiaVelocidadEscritura.isEnabled = false
                btnOrtografiaArbitrariaFrase1.isEnabled = true
            }
        }
    }

    private fun validarFrases() {

        btnConfirmarFraseOrtografiaArbitraria.setOnClickListener {

            val regex = "^[a-zA-Z ]+$".toRegex()

            if (!txtFrases.text.toString().toLowerCase(Locale.ROOT)
                    .contains(regex) || txtFrases.text.isEmpty()
            ) {
                ocultarTeclado(txtFrases)

                Toast.makeText(
                    applicationContext,
                    "Tu respuesta no es una frase o no escribiste nada",
                    Toast.LENGTH_SHORT
                ).show()

                txtFrases.text = null
            } else {
                frases.add(txtFrases.text.toString().trim().toLowerCase(Locale.ROOT))
                txtFrases.text = null
                ocultarTeclado(txtFrases)

                when (frases.size) {

                    1 -> {
                        btnConfirmarFraseOrtografiaArbitraria.isEnabled = false
                        btnOrtografiaArbitrariaFrase2.isEnabled = true

                        txtFrases.visibility = View.INVISIBLE

                        if (frases.contains("tu esfuerzo es muy valioso")) {
                            hits++
                            borrarFrase(1)
                        } else {
                            misses++
                            borrarFrase(1)
                        }
                    }

                    2 -> {
                        btnConfirmarFraseOrtografiaArbitraria.isEnabled = false
                        btnOrtografiaArbitrariaFrase3.isEnabled = true

                        txtFrases.visibility = View.INVISIBLE

                        if (frases.contains("voy al patio a jugar")) {
                            hits++
                            borrarFrase(1)
                        } else {
                            misses++
                            borrarFrase(1)
                        }
                    }

                    3 -> {
                        btnConfirmarFraseOrtografiaArbitraria.isEnabled = false

                        txtFrases.visibility = View.INVISIBLE

                        btnSiguienteOrtografiaArbitraria.isEnabled = true

                        if (frases.contains("estamos castigados por llegar tarde")) {
                            hits++
                            borrarFrase(1)
                        } else {
                            misses++
                            borrarFrase(1)
                        }
                    }
                }
            }
        }
    }

    private fun escucharFrases() {

        btnOrtografiaArbitrariaFrase1.setOnClickListener {

            val mp = MediaPlayer.create(this, R.raw.lenny2)

            if (!mp.isPlaying) {

                mp.start()
                Thread.sleep(2000)
                btnOrtografiaArbitrariaFrase1.isEnabled = false
                btnConfirmarFraseOrtografiaArbitraria.isEnabled = true

                txtFrases.visibility = View.VISIBLE
            }
        }

        btnOrtografiaArbitrariaFrase2.setOnClickListener {

            val mp = MediaPlayer.create(this, R.raw.lenny2)

            if (!mp.isPlaying) {

                mp.start()
                Thread.sleep(2000)
                btnOrtografiaArbitrariaFrase2.isEnabled = false
                btnConfirmarFraseOrtografiaArbitraria.isEnabled = true

                txtFrases.visibility = View.VISIBLE
            }
        }

        btnOrtografiaArbitrariaFrase3.setOnClickListener {

            val mp = MediaPlayer.create(this, R.raw.lenny2)

            if (!mp.isPlaying) {

                mp.start()
                Thread.sleep(2000)
                btnOrtografiaArbitrariaFrase3.isEnabled = false
                btnConfirmarFraseOrtografiaArbitraria.isEnabled = true

                txtFrases.visibility = View.VISIBLE
            }
        }
    }

    private fun ocultarTeclado(view: View) {

        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun noSugerencias() {

        txtFrases.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
    }

    private fun borrarFrase(x: Int) {

        frases.drop(x)
    }

    private fun listaBotones(): ArrayList<Button> {

        return arrayListOf(
            btnOrtografiaArbitrariaFrase1,
            btnOrtografiaArbitrariaFrase2,
            btnOrtografiaArbitrariaFrase3
        )
    }

    private fun inhabilitarBotones(){

        listaBotones().forEach {

            it.isEnabled = false
        }
    }

    private fun siguiente() {

        btnSiguienteOrtografiaArbitraria.setOnClickListener {

            clicks = frases.size

            Toast.makeText(
                applicationContext,
                "$hits",
                Toast.LENGTH_SHORT
            ).show()

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("OrtografíaArbitraria_VelodidadEscritura").set(
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