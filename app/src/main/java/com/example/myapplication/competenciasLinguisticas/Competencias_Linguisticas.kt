package com.example.myapplication.competenciasLinguisticas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencias__linguisticas.*

class Competencias_Linguisticas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__linguisticas)

    btnc_alfabetica.setOnClickListener{competenciaAlfabetica()}

    }

    private fun competenciaAlfabetica(){
        val intent = Intent(this, CompetenciaAlfabetica()::class.java)
        startActivity(intent)
    }
}