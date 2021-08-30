package com.example.myapplication.competenciasLinguisticas

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.*

class CompetenciaAlfabetica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()

    private val user = Firebase.auth.currentUser

    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_alfabetica)

        instruccionesCompetenciaAlfabetica()

        letrasCorrectas()

        siguiente()

        menu()

        btnLetrasCorrectasCAlfabetica.isEnabled = false

        btnSiguienteCompetenciaAlfabetica.isEnabled = false
    }

    private fun instruccionesCompetenciaAlfabetica() {

        val mp = MediaPlayer.create(this, R.raw.competenciaalfabetica)

        if (!mp.isPlaying) {
            btnInstruccionesCAlfabetica.setOnClickListener {
                mp.start()
                btnInstruccionesCAlfabetica.isEnabled = false
                Thread.sleep(24000)
                btnLetrasCorrectasCAlfabetica.isEnabled = true
            }
        }
    }

    private fun habilitarBotones() {
        val BOTONES_PRUEBA_ALFABETICA = arrayListOf<Button>(
            btnp,
            btnf,
            btne,
            btna,
            btnj,
            btnt,
            btnm,
            btnu,
            btni,
            btns,
            btnq,
            btno
        )

        BOTONES_PRUEBA_ALFABETICA.forEach {
            it.isEnabled = true
            it.visibility = View.VISIBLE
        }
    }

    private fun letrasCorrectas() {

        buttonsSetEnabledFalse()

        val mp = MediaPlayer.create(this, R.raw.palabrascompetenciaalfabetica)

        if (!mp.isPlaying) {
            btnLetrasCorrectasCAlfabetica.setOnClickListener {
                mp.start()
                btnLetrasCorrectasCAlfabetica.isEnabled = false
                Thread.sleep(5000)
                habilitarBotones()
            }
        }

        btnp.setOnClickListener {

            btnp.isEnabled = false
            validarNumeroBotonesSeleccionados()
            hits++
        }

        btnf.setOnClickListener {

            btnf.isEnabled = false
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btne.setOnClickListener {

            btne.isEnabled = false
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btna.setOnClickListener {

            btna.isEnabled = false
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btnj.setOnClickListener {

            btnj.isEnabled = false
            validarNumeroBotonesSeleccionados()
            hits++
        }
        btnt.setOnClickListener {

            btnt.isEnabled = false
            validarNumeroBotonesSeleccionados()
            hits++
        }
        btnm.setOnClickListener {

            btnm.isEnabled = false
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btnu.setOnClickListener {

            btnu.isEnabled = false
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btni.setOnClickListener {
            btni.isEnabled = false
            validarNumeroBotonesSeleccionados()
            hits++
        }
        btns.setOnClickListener {
            btns.isEnabled = false

            validarNumeroBotonesSeleccionados()
            misses++
        }
        btnq.setOnClickListener {

            btnq.isEnabled = false
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btno.setOnClickListener {

            validarNumeroBotonesSeleccionados()
            btno.isEnabled = false
            misses++
        }
    }

    private fun validarNumeroBotonesSeleccionados() {

        clicks++
        if (clicks == 4) {

            buttonsSetEnabledFalse()
            btnSiguienteCompetenciaAlfabetica.isEnabled = true
        }
    }

    private fun buttonsSetEnabledFalse() {
        val botonesLetras = arrayListOf<Button>(
            btnp,
            btnf,
            btne,
            btna,
            btnj,
            btnt,
            btnm,
            btnu,
            btni,
            btns,
            btnq,
            btno
        )

        for (i in botonesLetras)
            i.isEnabled = false
    }

    private fun menu() {

        btnMenuCA.setOnClickListener {

            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun siguiente() {

        btnSiguienteCompetenciaAlfabetica.setOnClickListener {

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaAlfabética").set(
                    hashMapOf(
                        "Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses
                    )
                )
            }

            obtenerDocumento(
                user?.email.toString(),
                "CompetenciaFónica",
                CompetenciaFonica()
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







