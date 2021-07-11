package com.example.myapplication.funcionesEjecutivas

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_atencion_selectiva.*
import java.util.*

class AtencionSelectiva : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion_selectiva)

        btnSiguienteAtencionSelectiva.setEnabled(false)

        instruccionesAtencionSelectiva()

        siguiente()

    }

    private fun instruccionesAtencionSelectiva() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        if (!mp.isPlaying) {
            btnInstruccionesatencionSelectiva.setOnClickListener {
                mp.start()
                btnInstruccionesatencionSelectiva.setEnabled(false)
                Thread.sleep(2000)
                habilitarBotonesPrueba1()
            }
        }
    }

    private fun habilitarBotonesPrueba1() {

        var contadorU = 0
        val PRUEBA1 = mutableListOf("n", "u")

        val BOTONES_ATENCION_SELECTIVA = arrayListOf<Button>(btnOpcion1AtencionSelectiva,
            btnOpcion2AtencionSelectiva,
            btnOpcion3AtencionSelectiva,
            btnOpcion4AtencionSelectiva,
            btnOpcion5AtencionSelectiva,
            btnOpcion6AtencionSelectiva,
            btnOpcion7AtencionSelectiva,
            btnOpcion8AtencionSelectiva,
            btnOpcion9AtencionSelectiva,
            btnOpcion10AtencionSelectiva,
            btnOpcion11AtencionSelectiva,
            btnOpcion12AtencionSelectiva)

        BOTONES_ATENCION_SELECTIVA.forEach {

            it.setVisibility(View.VISIBLE)
            it.text = PRUEBA1[random.nextInt(PRUEBA1.size)]
            //it.text = PRUEBA2[random.nextInt(PRUEBA2.size)]
            if (it.text.equals("u")) {
                contadorU++
                it.tag = "correctoPrueba1"
            }

            if (contadorU > 0) {
                for (i in 1..contadorU - 1) {
                    //PRUEBA1.filter { x -> x == "u" }
                    it.text = PRUEBA1.get(0)
                    it.tag = ""
                }
            }

            it.setOnClickListener {
                clicks++

                if (it.tag == "correctoPrueba1") {
                    hits++
                } else {
                    misses++
                }

                if (clicks == 1) {
                    inhabilitarBotones()
                    habilitarBotonesPrueba2()
                }
            }
        }
    }

    private fun habilitarBotonesPrueba2() {

        var contadorP = 0
        val PRUEBA2 = mutableListOf("q", "p")
        val BOTONES_ATENCION_SELECTIVA = arrayListOf<Button>(btnOpcion1AtencionSelectiva,
            btnOpcion2AtencionSelectiva,
            btnOpcion3AtencionSelectiva,
            btnOpcion4AtencionSelectiva,
            btnOpcion5AtencionSelectiva,
            btnOpcion6AtencionSelectiva,
            btnOpcion7AtencionSelectiva,
            btnOpcion8AtencionSelectiva,
            btnOpcion9AtencionSelectiva,
            btnOpcion10AtencionSelectiva,
            btnOpcion11AtencionSelectiva,
            btnOpcion12AtencionSelectiva)

        BOTONES_ATENCION_SELECTIVA.forEach {

            it.setEnabled(true)
            it.text = PRUEBA2[random.nextInt(PRUEBA2.size)]

            if (it.getText().equals("p")) {
                contadorP++
                it.tag = "correctoPrueba2"
            }

            if (contadorP > 0) {
                for (i in 1..contadorP - 1) {
                    //PRUEBA2.filter { x -> x == "p" }
                    it.text = PRUEBA2[0]
                    it.tag = ""
                }
            }

            it.setOnClickListener {
                clicks++

                if (it.tag == "correctoPrueba2") {
                    hits++
                } else {
                    misses++
                }

                if (clicks == 2) {
                    inhabilitarBotones()
                    habilitarBotonesPrueba3()
                }
            }
        }
    }

    private fun habilitarBotonesPrueba3() {

        var contadorD = 0
        val PRUEBA3 = mutableListOf("b", "d")
        val BOTONES_ATENCION_SELECTIVA = arrayListOf<Button>(btnOpcion1AtencionSelectiva,
            btnOpcion2AtencionSelectiva,
            btnOpcion3AtencionSelectiva,
            btnOpcion4AtencionSelectiva,
            btnOpcion5AtencionSelectiva,
            btnOpcion6AtencionSelectiva,
            btnOpcion7AtencionSelectiva,
            btnOpcion8AtencionSelectiva,
            btnOpcion9AtencionSelectiva,
            btnOpcion10AtencionSelectiva,
            btnOpcion11AtencionSelectiva,
            btnOpcion12AtencionSelectiva)

        BOTONES_ATENCION_SELECTIVA.forEach {

            it.setEnabled(true)
            it.text = PRUEBA3[random.nextInt(PRUEBA3.size)]

            if (it.getText().equals("d")) {
                contadorD++
                it.tag = "correctoPrueba3"
            }

            if (contadorD > 0) {
                for (i in 1..contadorD - 1) {
                    //PRUEBA3.filter { x -> x == "d" }
                    it.text = PRUEBA3.get(0)
                    it.tag = ""
                }
            }

            it.setOnClickListener {
                clicks++

                if (it.tag == "correctoPrueba3") {
                    hits++
                } else {
                    misses++
                }

                if (clicks == 3) {
                    inhabilitarBotones()
                    btnSiguienteAtencionSelectiva.setEnabled(true)
                }
            }
        }
    }

    private fun inhabilitarBotones() {

        val BOTONES_ATENCION_SELECTIVA = arrayListOf<Button>(btnOpcion1AtencionSelectiva,
            btnOpcion2AtencionSelectiva,
            btnOpcion3AtencionSelectiva,
            btnOpcion4AtencionSelectiva,
            btnOpcion5AtencionSelectiva,
            btnOpcion6AtencionSelectiva,
            btnOpcion7AtencionSelectiva,
            btnOpcion8AtencionSelectiva,
            btnOpcion9AtencionSelectiva,
            btnOpcion10AtencionSelectiva,
            btnOpcion11AtencionSelectiva,
            btnOpcion12AtencionSelectiva)

        BOTONES_ATENCION_SELECTIVA.forEach {
            it.setEnabled(false)
        }
    }

    private fun siguiente() {

        btnSiguienteAtencionSelectiva.setOnClickListener {

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("AtenciónSelectiva").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
        }
    }
}