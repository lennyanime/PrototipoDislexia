package com.example.myapplication.competenciasLinguisticas

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.*

class CompetenciaAlfabetica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_alfabetica)
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        instruccionesCompetenciaAlfabetica()
        letrasCorrectas()
        siguiente()
        salir()
        btnLetrasCorrectasCAlfabetica.setEnabled(false)
        btnSiguienteCompetenciaAlfabetica.setEnabled(false)
    }

    private fun instruccionesCompetenciaAlfabetica(){

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesCAlfabetica.setOnClickListener {
                mp.start()
                btnInstruccionesCAlfabetica.setEnabled(false)
                Thread.sleep(2000)
                btnLetrasCorrectasCAlfabetica.setEnabled(true)
            }
        }
    }

    private fun habilitarBotones(){
        val BOTONES_PRUEBA_ALFABETICA = arrayListOf<Button>(btnp, btnf, btne, btna, btnj, btnt, btnm, btnu, btni, btns, btnq, btno)

        BOTONES_PRUEBA_ALFABETICA.forEach {
            it.setEnabled(true)
            it.setVisibility(View.VISIBLE)
        }
    }

    private fun letrasCorrectas() {

        buttonsSetEnabledFalse()

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnLetrasCorrectasCAlfabetica.setOnClickListener {
                mp.start()
                btnLetrasCorrectasCAlfabetica.setEnabled(false)
                Thread.sleep(2000)
                habilitarBotones()
            }
        }

        btnp.setOnClickListener {
            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()

            btnp.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            hits++
        }

        btnf.setOnClickListener {

            btnf.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btne.setOnClickListener {

            btne.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btna.setOnClickListener {

            btna.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btnj.setOnClickListener {

            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            btnj.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            hits++
        }
        btnt.setOnClickListener {

            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            btnt.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            hits++
        }
        btnm.setOnClickListener {

            btnm.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btnu.setOnClickListener {

            btnu.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btni.setOnClickListener {
            btni.setEnabled(false)
            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            validarNumeroBotonesSeleccionados()
            hits++
        }
        btns.setOnClickListener {
            btns.setEnabled(false)

            validarNumeroBotonesSeleccionados()
            misses++
        }
        btnq.setOnClickListener {

            btnq.setEnabled(false)
            validarNumeroBotonesSeleccionados()
            misses++
        }
        btno.setOnClickListener {

            validarNumeroBotonesSeleccionados()
            btno.setEnabled(false)
            misses++
        }
    }

    private fun validarNumeroBotonesSeleccionados() {

        clicks++
        if (clicks == 4) {

            buttonsSetEnabledFalse()
            btnSiguienteCompetenciaAlfabetica.setEnabled(true)
        }
    }

    private fun buttonsSetEnabledFalse() {
        val botonesLetras = arrayListOf<Button>(btnp, btnf, btne, btna, btnj, btnt, btnm, btnu, btni, btns, btnq, btno)

        for (i in botonesLetras)
            i.setEnabled(false)
    }

    private fun salir() {


    }

    private fun siguiente() {

        btnSiguienteCompetenciaAlfabetica.setOnClickListener {

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaAlfabética").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}






