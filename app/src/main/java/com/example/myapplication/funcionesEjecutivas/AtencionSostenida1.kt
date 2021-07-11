package com.example.myapplication.funcionesEjecutivas

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_atencion_sostenida1.*

class AtencionSostenida1 : AppCompatActivity() {

    private var clicks: Int = 0
    private var hits: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion_sostenida1)

        instruccionesAtencionSostenida()
    }

    private fun instruccionesAtencionSostenida() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesAtencionSostenida.setOnClickListener {
                mp.start()
                btnInstruccionesAtencionSostenida.isEnabled = false
                Thread.sleep(2000)
                desaparecerImagenesPrueba1()
            }
        }
    }

    private fun imagenesPrueba1(): MutableList<ImageView> {

        var IMAGENES_PRUEBA_ATENCION_SOSTENIDA = mutableListOf<ImageView>(imgCruz1AtencionSostenida,
            imgCruz2AtencionSostenida,
            imgCuadrado1AtencionSostenida,
            imgCuadrado2AtencionSostenida,
            imgTriangulo1AtencionSostenida,
            imgTriangulo2AtencionSostenida,
            imgEstrella1AtencionSostenida,
            imgEstrella2AtencionSostenida,
            imgCirculo1AtencionSostenida,
            imgCirculo2AtencionSostenida,
            imgTrapecio1AtencionSostenida,
            imgTrapecio2AtencionSostenida
            )

        return IMAGENES_PRUEBA_ATENCION_SOSTENIDA
    }

    private fun desaparecerImagenesPrueba1() {

        var imagenAzar1 = imagenesPrueba1().random()

        imagenAzar1.isInvisible = true

        imagenesPrueba1().forEach {

            it.setOnClickListener {

                clicks++

                var boton = it.tag

                if (imagenAzar1.tag == boton) {

                    hits++
                    Toast.makeText(applicationContext,
                        "correcto",
                        Toast.LENGTH_SHORT).show()
                    inhabilitarImagenesInterfazPrueba1()
                    segundaPrueba()

                } else {
                    inhabilitarImagenesInterfazPrueba1()
                    segundaPrueba()
                }
            }
        }
    }

    private fun inhabilitarImagenesInterfazPrueba1(){

        imagenesPrueba1().forEach{

            it.isEnabled = false
        }
    }

    private fun segundaPrueba(){

        if (clicks == 1) {

            val intent = Intent(this, AtencionSostenida2()::class.java)
            intent.putExtra("hits", hits)
            intent.putExtra("clicks", clicks)
            startActivity(intent)
        }

    }
}

