package com.example.myapplication.rendimientoDesempeño

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencias__rendimiento__desempeno.*

class Competencias_Rendimiento_Desempeno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__rendimiento__desempeno)

        btnVelocidadLectura.setOnClickListener{

            velocidadLectura()
        }

        btnComprensionLectura.setOnClickListener{

            comprensionLectura()
        }

        btnOrtografiaArbitraria.setOnClickListener {

            ortografiaArbitraria()
        }

        btnReconocimientoErrores.setOnClickListener {

            reconocimientoErrores()
        }

    }

    private fun velocidadLectura(){

        val intent = Intent(this, VelocidadDeLectura()::class.java)
        startActivity(intent)
    }

    private fun comprensionLectura(){

        val intent = Intent(this, ComprensionLectura()::class.java)
        startActivity(intent)
    }

    private fun ortografiaArbitraria(){

        val intent = Intent(this, OrtografiaArbitraria_VelocidadEscritura()::class.java)
        startActivity(intent)
    }

    private fun reconocimientoErrores(){

        val intent = Intent(this, Reconocimiento_CorreccionErrores()::class.java)
        startActivity(intent)
    }
}