package com.example.myapplication.competenciasLinguisticas

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.*
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.btnSalirCA
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.btnFonemasCA

class CompetenciaAlfabetica : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_alfabetica)

        letrasCorrectas()
        salir()

    }

    private fun letrasCorrectas() {

        var botonesSeleccionados = 0
        buttonsSetEnabledFalse()

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            this.btnFonemasCA.setOnClickListener {
                mp.start()
                this.btnFonemasCA.setEnabled(false)
                Thread.sleep(6000)
                buttonsSetEnabledTrue()
            }
        }

        btnp.setOnClickListener {
            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            botonesSeleccionados++

            btnp.setEnabled(false)
            if (botonesSeleccionados == 4){

                buttonsSetEnabledFalse()
            }

        }

        btnf.setOnClickListener {
            botonesSeleccionados++

            btnf.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btne.setOnClickListener {

            botonesSeleccionados++
            btne.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btna.setOnClickListener {
            botonesSeleccionados++

            btna.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btnj.setOnClickListener {
            botonesSeleccionados++

            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            btnj.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btnt.setOnClickListener {
            botonesSeleccionados++
            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            btnt.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btnm.setOnClickListener {
            botonesSeleccionados++

            btnm.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btnu.setOnClickListener {
            botonesSeleccionados++

            btnu.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btni.setOnClickListener {
            botonesSeleccionados++
            btni.setEnabled(false)
            Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btns.setOnClickListener {
            botonesSeleccionados++
            btns.setEnabled(false)

            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btnq.setOnClickListener {
            botonesSeleccionados++

            btnq.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
        btno.setOnClickListener {
            botonesSeleccionados++

            btno.setEnabled(false)
            if (botonesSeleccionados == 4) {

                buttonsSetEnabledFalse()
            }
        }
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

    private fun salir( ) {

        btnSalirCA.setOnClickListener{
            onBackPressed()
            super.onDestroy()
        }
    }
    private fun siguiente(){

    }
}





