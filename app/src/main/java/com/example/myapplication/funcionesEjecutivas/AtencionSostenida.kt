package com.example.myapplication.funcionesEjecutivas

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.util.rangeTo
import com.example.myapplication.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_atencion_selectiva.*
import kotlinx.android.synthetic.main.activity_atencion_sostenida.*
import kotlinx.android.synthetic.main.activity_atencion_sostenida.view.*
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.*
import kotlinx.android.synthetic.main.activity_lateralidad_1.*
import java.util.*

class AtencionSostenida : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var random = Random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion_sostenida)

        instruccionesAtencionSostenida()
    }

    private fun instruccionesAtencionSostenida() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesAtencionSostenida.setOnClickListener {
                mp.start()
                btnInstruccionesAtencionSostenida.setEnabled(false)
                Thread.sleep(2000)
                desaparecerImagenes()
            }
        }
    }

    private fun desaparecerImagenes() {

        var contadorPrueba1 = 0

        var IMAGENES_PRUEBA_ATENCION_SOSTENIDA = mutableListOf<ImageView>(imgCruz1AtencionSostenida,
            imgCruz2AtencionSostenida,
            imgCuadrado1AtencionSostenida,
            imgOctagonoAtencionSostenida,
            imgTrianguloAtencionSostenida,
            imgEstrellaAtencionSostenida,
            imgCirculo1AtencionSostenida,
            imgCirculo2AtencionSostenida,
            imgCuadrado2AtencionSostenida)

        val range = 1..5
        IMAGENES_PRUEBA_ATENCION_SOSTENIDA.forEach {

            it.setVisibility(View.INVISIBLE)

            Toast.makeText(applicationContext,
                "${random.nextInt()}",
                Toast.LENGTH_SHORT).show()
        }

    }
}


