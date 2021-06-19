package com.example.myapplication.funcionesEjecutivas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.lateralidad.Lateralidad_1
import kotlinx.android.synthetic.main.activity_competencias__funciones__ejecutivas.*

class Competencias_Funciones_Ejecutivas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__funciones__ejecutivas)



        btnAtencionDividida.setOnClickListener {
            atencionDividida()
        }

       btnAtencionSelectiva.setOnClickListener {
           atencionSelectiva()
       }

        btnAtencionSostenida.setOnClickListener {
            atencionSostenida()
        }

    }

    private fun atencionDividida(){

            val intent = Intent(this, AtencionDividida::class.java)
            startActivity(intent)
    }

    private fun atencionSelectiva(){

            val intent = Intent(this, AtencionSelectiva::class.java)
            startActivity(intent)

    }

    private fun atencionSostenida(){

            val intent = Intent(this, AtencionSostenida::class.java)
            startActivity(intent)
    }
}