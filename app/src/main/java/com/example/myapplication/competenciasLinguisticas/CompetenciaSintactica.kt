package com.example.myapplication.competenciasLinguisticas

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencia_sintactica.*
import kotlinx.android.synthetic.main.activity_competencia_sintactica.btnInstruccionesCompetenciaSintactica


class CompetenciaSintactica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_sintactica)

        instruccionesCompetenciaSintactica()
        imagenesSetEnabledFalse()

        btnSiguienteCSintactica.setEnabled(false)
    }

    fun primerImagen(v: View) {

        imagenesSetEnabledFalse()
        siguienteSetEnabledTrue()
    }

    fun segundaImagen(v: View) {

        imagenesSetEnabledFalse()
        siguienteSetEnabledTrue()
    }

    fun terceraImagen(v: View) {

        imagenesSetEnabledFalse()
        siguienteSetEnabledTrue()
    }

    fun cuartaImagen(v: View) {

        imagenesSetEnabledFalse()
        siguienteSetEnabledTrue()
    }

    private fun instruccionesCompetenciaSintactica() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesCompetenciaSintactica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(2000)
                btnInstruccionesCompetenciaSintactica.setEnabled(false)
                imagenesSetEnabledTrue()
            }
        }
    }

    private fun imagenesSetEnabledFalse() {
        val imagenes = arrayListOf<ImageView>(imgCSintactica1, imgCSintactica2, imgCSintactica3, imgCSintactica4)

        for (i in imagenes)
            i.setEnabled(false)
    }

    private fun imagenesSetEnabledTrue() {
        val imagenes = arrayListOf<ImageView>(imgCSintactica1, imgCSintactica2, imgCSintactica3, imgCSintactica4)

        for (i in imagenes)
            i.setEnabled(true)
    }

    private fun siguienteSetEnabledTrue() {

        btnSiguienteCSintactica.setEnabled(true)
    }
}