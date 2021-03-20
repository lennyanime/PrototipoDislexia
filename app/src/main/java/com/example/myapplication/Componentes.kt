package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.competenciasLinguisticas.Competencias_Linguisticas
import com.example.myapplication.funcionesEjecutivas.Competencias_Funciones_Ejecutivas
import com.example.myapplication.lateralidad.Competencias_Lateralidad
import com.example.myapplication.memoriaDeTrabajo.Competencia_Memoria_Trabajo
import com.example.myapplication.procesosPerceptivos.Competencias_Procesos_Perceptivos
import com.example.myapplication.rendimientoDesempeño.Competencias_Rendimiento_Desempeno
import kotlinx.android.synthetic.main.activity_componentes.*

class Componentes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_componentes)

        btncl.setOnClickListener{CompetenciasLinguisticas()}

        btnmt.setOnClickListener{MemoriaDeTrabjo()}

        btnfe.setOnClickListener{FuncionesEjecutivas()}

        btnrd.setOnClickListener{RendimiendoDesempeño()}

        btnpp.setOnClickListener{ProcesosPerceptivos()}

        btnl.setOnClickListener{Lateralidad()}

    }

    private fun CompetenciasLinguisticas(){
        val intent = Intent(this, Competencias_Linguisticas()::class.java)
        startActivity(intent)
    }

    private fun MemoriaDeTrabjo(){
        val intent = Intent(this, Competencia_Memoria_Trabajo()::class.java)
        startActivity(intent)
    }

    private fun FuncionesEjecutivas(){
        val intent = Intent(this, Competencias_Funciones_Ejecutivas()::class.java)
        startActivity(intent)
    }

    private fun RendimiendoDesempeño(){
        val intent = Intent(this, Competencias_Rendimiento_Desempeno::class.java)
        startActivity(intent)
    }

    private fun ProcesosPerceptivos(){
        val intent = Intent(this, Competencias_Procesos_Perceptivos::class.java)
        startActivity(intent)
    }

    private fun Lateralidad(){
        val intent = Intent(this, Competencias_Lateralidad::class.java)
        startActivity(intent)
    }
}