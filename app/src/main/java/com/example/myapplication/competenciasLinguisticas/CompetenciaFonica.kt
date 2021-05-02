package com.example.myapplication.competenciasLinguisticas

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencia_fonica.*
import kotlinx.android.synthetic.main.activity_lateralidad_1.*
import java.util.*

private var words = arrayListOf("leña", "peña", "cabeza", "pereza", "hormiga", "barriga", "ratón", "botón", "araña", "montaña",
        "piña", "niña", "luna", "cuna", "ventana", "anillo", "martillo", "niño")
private var random = Random()

class CompetenciaFonica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_fonica)

        instruccionesPruebaLateralidad()

        randomWordRow1()
        randomWordRow2()
        randomWordRow3()
        randomWordRow4()

        btnSiguienteCFonica.setEnabled(false)
    }

    private fun instruccionesPruebaLateralidad() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesCFonica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
            }
        }
    }

    private fun randomWordRow1() {

        words.forEach {

            word1.setText(words[random.nextInt(words.size)])
            word2.setText(words[random.nextInt(words.size)])

            while (word1.getText().toString().equals(word2.getText().toString()))
                word2.setText(words[random.nextInt(words.size)])
        }

        /*btnrtap1.setOnClickListener {
            var char1Word1 = ""

            for (i in word1.getText().toString()) {

                //obtener útlimas dos letras de la palabra
                char1Word1 = word1.getText().toString().substring((word1.length() - 2))
                //char2Word2 = word1.getText().toString().substring(word1.length())

                Toast.makeText(applicationContext, ("${char1Word1} "), Toast.LENGTH_SHORT).show()
                *//*letra = silaba1
                Toast.makeText(applicationContext, ("${letra}"), Toast.LENGTH_SHORT).show()

                break*//*
                //Toast.makeText(applicationContext, ("$"), Toast.LENGTH_SHORT).show()

            }

            //Toast.makeText(applicationContext, ("$i"), Toast.LENGTH_SHORT).show()
        }*/
        //forma correcta de borrar un TextView
        words.remove("${word1.getText()}")
        words.remove("${word2.getText()}")


        /*for (i in words)
            Toast.makeText(applicationContext, ("$i"), Toast.LENGTH_SHORT).show()*/
    }

    private fun randomWordRow2() {

        words.forEach {

            word3.setText(words[random.nextInt(words.size)])
            word4.setText(words[random.nextInt(words.size)])

            while (word4.getText().toString().equals(word3.getText().toString()))
                word4.setText(words[random.nextInt(words.size)])
        }
        //forma correcta de borrar un TextView
        words.remove("${word3.getText()}")
        words.remove("${word4.getText()}")
    }

    private fun randomWordRow3() {

        words.forEach {

            word5.setText(words[random.nextInt(words.size)])
            word6.setText(words[random.nextInt(words.size)])

            while (word6.getText().toString().equals(word5.getText().toString()))
                word6.setText(words[random.nextInt(words.size)])
        }

        //forma correcta de borrar un TextView
        words.remove("${word5.getText()}")
        words.remove("${word6.getText()}")
    }

    private fun randomWordRow4() {

        words.forEach {

            word7.setText(words[random.nextInt(words.size)])
            word8.setText(words[random.nextInt(words.size)])

            while (word8.getText().toString().equals(word7.getText().toString()))
                word8.setText(words[random.nextInt(words.size)])
        }

        //forma correcta de borrar un TextView
        //words.remove("${word7.getText()}")
        //words.remove("${word8.getText()}")
    }

}