package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_componentes.*

class Componentes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_componentes)

        btncl.setOnClickListener(){
            competenciasLinguisticas()
        }


    }

    private fun competenciasLinguisticas(){
        val intent = Intent(this, CompetenciaAlfabetica::class.java)
        startActivity(intent)
    }
}