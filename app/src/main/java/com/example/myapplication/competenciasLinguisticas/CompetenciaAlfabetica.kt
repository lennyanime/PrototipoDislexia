package com.example.myapplication.competenciasLinguisticas

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.*
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.btnSalirCA


class CompetenciaAlfabetica : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_alfabetica)

        instruccionesCompetenciaAlfabetica()
        letrasCorrectas()
        salir()
        btnLetrasCorrectasCAlfabetica.setEnabled(false)
        btnSiguienteCAlfabetica.setEnabled(false)
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

    private fun letrasCorrectas() {
        var botonesSeleccionados = 0

        buttonsSetEnabledFalse()

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnLetrasCorrectasCAlfabetica.setOnClickListener {
                mp.start()
                btnLetrasCorrectasCAlfabetica.setEnabled(false)
                Thread.sleep(6000)
                buttonsSetEnabledTrue()
            }
        }

        btnp.setOnClickListener {
            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            botonesSeleccionados++

            btnp.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }

        btnf.setOnClickListener {
            botonesSeleccionados++

            btnf.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btne.setOnClickListener {

            botonesSeleccionados++
            btne.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btna.setOnClickListener {
            botonesSeleccionados++

            btna.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btnj.setOnClickListener {
            botonesSeleccionados++

            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            btnj.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btnt.setOnClickListener {
            botonesSeleccionados++
            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            btnt.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btnm.setOnClickListener {
            botonesSeleccionados++

            btnm.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btnu.setOnClickListener {
            botonesSeleccionados++

            btnu.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btni.setOnClickListener {
            botonesSeleccionados++
            btni.setEnabled(false)
            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btns.setOnClickListener {
            botonesSeleccionados++
            btns.setEnabled(false)

            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btnq.setOnClickListener {
            botonesSeleccionados++

            btnq.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
                btnSiguienteSetEnabledTrue()
            }
        }
        btno.setOnClickListener {
            botonesSeleccionados++

            btno.setEnabled(false)
            btnSiguienteSetEnabledTrue()

        }
    }

    private fun validarNumeroBotonesSeleccionados() {

    }

    private fun buttonsSetEnabledFalse() {
        val botonesLetras = arrayListOf<Button>(btnp, btnf, btne, btna, btnj, btnt, btnm, btnu, btni, btns, btnq, btno)

        for (i in botonesLetras)
            i.setEnabled(false)
    }

    private fun buttonsSetEnabledTrue() {
        val botonesLetras = arrayListOf<Button>(btnp, btnf, btne, btna, btnj, btnt, btnm, btnu, btni, btns, btnq, btno)

        for (i in botonesLetras)
            i.setEnabled(true)
    }

    private fun salir() {

        btnSalirCA.setOnClickListener {
            onBackPressed()
            super.onDestroy()
        }
    }

    private fun btnSiguienteSetEnabledTrue(){

        btnSiguienteCAlfabetica.setEnabled(true)
    }

    private fun save() {

    }
}





