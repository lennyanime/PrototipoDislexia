package com.example.myapplication.lateralidad

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencias__lateralidad.*

class Competencias_Lateralidad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__lateralidad)


        btnpruebaL1.setOnClickListener{
            pruebaUnoLateralidad()
        }

        btnpruebaL2.setOnClickListener{
            pruebaDosLateralidad()
        }
    }


    private fun pruebaUnoLateralidad(){

        val intent = Intent(this, Lateralidad_1::class.java)
        startActivity(intent)
    }

    private fun pruebaDosLateralidad(){

        val intent = Intent(this, Lateralidad_2::class.java)
        startActivity(intent)
    }

}