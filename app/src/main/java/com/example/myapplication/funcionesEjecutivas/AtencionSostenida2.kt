package com.example.myapplication.funcionesEjecutivas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_atencion_sostenida1.*
import kotlinx.android.synthetic.main.activity_atencion_sostenida1.view.*
import kotlinx.android.synthetic.main.activity_atencion_sostenida2.*
import kotlinx.android.synthetic.main.activity_atencion_sostenida2.view.*

class AtencionSostenida2 : AppCompatActivity() {

    private var clicks: Int = 0
    private var hits: Int = 0

    private lateinit var imagenAzar1: ImageView
    private lateinit var imagenAzar2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion_sostenida2)

        //ocultarImagenes()

        desaparecerImagenesPrueba2()

        validarImagenesPrueba2()

        /*btnIniciarPrueba2AtencionSostenida.setOnClickListener {

            btnIniciarPrueba2AtencionSostenida.isEnabled = false
            Thread.sleep(4000)
            desaparecerImagenesPrueba2()
            validarImagenesPrueba2()
        }*/

        obtenerClicksHits()
    }

    private fun imagenesPrueba2(): MutableList<ImageView> {

        var IMAGENES_PRUEBA_ATENCION_SOSTENIDA = mutableListOf<ImageView>(
            imgCruz1AtencionSostenida2,
            imgCruz2AtencionSostenida2,
            imgCuadrado1AtencionSostenida2,
            imgCuadrado2AtencionSostenida2,
            imgTriangulo1AtencionSostenida2,
            imgTriangulo2AtencionSostenida2,
            imgEstrella1AtencionSostenida2,
            imgEstrella2AtencionSostenida2,
            imgCirculo1AtencionSostenida2,
            imgCirculo2AtencionSostenida2,
            imgTrapecio1AtencionSostenida2,
            imgTrapecio2AtencionSostenida2
        )

        return IMAGENES_PRUEBA_ATENCION_SOSTENIDA
    }

    private fun desaparecerImagenesPrueba2() {

        imagenAzar1 = imagenesPrueba2().random()
        imagenAzar2 = imagenesPrueba2().random()

        while (imagenAzar2.tag == imagenAzar1.tag)
            imagenAzar2 = imagenesPrueba2().random()

        imagenAzar1.isInvisible = true
        imagenAzar2.isInvisible = true
    }

    private fun validarImagenesPrueba2(){

        imagenesPrueba2().forEach {

            it.setOnClickListener {

                clicks++
                var boton = it.tag

                if (imagenAzar1.tag == boton || imagenAzar2.tag == boton) {

                    hits++
                    Toast.makeText(applicationContext,
                        "correcto",
                        Toast.LENGTH_SHORT).show()
                    terceraPrueba()

                } else {
                    terceraPrueba()
                }
            }
        }
    }

    private fun inhabilitarImagenesInterfazPrueba2() {

        imagenesPrueba2().forEach {

            it.isEnabled = false
        }
    }

    private fun obtenerClicksHits() {

        val bundle = intent.extras
        clicks = bundle?.get("clicks") as Int
        hits = bundle?.get("hits") as Int

        Toast.makeText(applicationContext,
            "$hits , $clicks",
            Toast.LENGTH_SHORT).show()
    }

    private fun terceraPrueba() {

        if (clicks == 3) {

            inhabilitarImagenesInterfazPrueba2()
            val intent = Intent(this, AtencionSostenida3()::class.java)
            intent.putExtra("hits", hits)
            intent.putExtra("clicks", clicks)
            startActivity(intent)
        }
    }
}