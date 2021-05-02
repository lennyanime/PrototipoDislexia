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

        btnc_alfabetica.setOnClickListener { competenciaAlfabetica() }

        btnc_fonica.setOnClickListener { competenciaFonologica() }

        btnc_silabica.setOnClickListener { competenciaSilabica() }

        btnc_lexica.setOnClickListener { competenciaLexica() }

        btnc_sintactica.setOnClickListener{ competenciaSintactica() }

        btnc_semantica.setOnClickListener{ competenciaSemantica() }

        btnc_ortografica.setOnClickListener{ competenciaOrtografica() }

    }

    private fun competenciaAlfabetica() {
        val intent = Intent(this, CompetenciaAlfabetica()::class.java)
        startActivity(intent)
    }

    private fun competenciaFonologica() {
        val intent = Intent(this, CompetenciaFonica()::class.java)
        startActivity(intent)
    }

    private fun competenciaSilabica() {
        val intent = Intent(this, CompetenciaSilabica()::class.java)
        startActivity(intent)
    }

    private fun competenciaLexica() {
        val intent = Intent(this, CompetenciaLexica()::class.java)
        startActivity(intent)
    }

    private fun competenciaSintactica(){
        val intent = Intent(this, CompetenciaSintactica()::class.java)
        startActivity(intent)
    }

    private fun competenciaSemantica(){
        val intent = Intent(this, CompetenciaSemantica()::class.java)
        startActivity(intent)
    }

    private fun competenciaOrtografica(){
        val intent = Intent(this, CompetenciaOrtografica()::class.java)
        startActivity(intent)
    }
}