package com.example.myapplication.memoriaDeTrabajo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencia__memoria__trabajo.*

class Competencia_Memoria_Trabajo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia__memoria__trabajo)


        btn_mvisual.setOnClickListener { memoriaTrabajoVisual() }

        btn_mauditiva.setOnClickListener { memoriaTrabajoAuditiva() }

        btn_msauditiva.setOnClickListener { memoriaSecuencialAuditiva() }

        btn_msvisual.setOnClickListener { memoriaSecuencialVisual() }
    }

    private fun memoriaTrabajoVisual() {

        val intent = Intent(this, MemoriaVisual()::class.java)
        startActivity(intent)
    }

    private fun memoriaSecuencialAuditiva() {

        val intent = Intent(this, MemoriaSecuencialAuditiva()::class.java)
        startActivity(intent)
    }

    private fun memoriaSecuencialVisual() {

        val intent = Intent(this, MemoriaSecuencialVisual()::class.java)
        startActivity(intent)
    }

    private fun memoriaTrabajoAuditiva() {

        val intent = Intent(this, MemoriaTrabajoAuditiva()::class.java)
        startActivity(intent)
    }


}