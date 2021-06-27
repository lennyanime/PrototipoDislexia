package com.example.myapplication.procesosPerceptivos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencias__procesos__perceptivos.*

class Competencias_Procesos_Perceptivos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__procesos__perceptivos)

        btnPrueba1DCV.setOnClickListener { discrimizacionCategorizacionVisual() }

        btnPrueba2DCA.setOnClickListener { discrimizacionCategorizacionAuditiva() }

    }

    private fun discrimizacionCategorizacionAuditiva() {
        val intent = Intent(this, DiscriminacionCategorizacionAuditiva()::class.java)
        startActivity(intent)
    }

    private fun discrimizacionCategorizacionVisual() {
        val intent = Intent(this, DiscriminacionCategorizacionVisual1()::class.java)
        startActivity(intent)
    }
}