package com.example.myapplication.lateralidad

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_alfabetica.*
import kotlinx.android.synthetic.main.activity_lateralidad_1.*
import kotlinx.android.synthetic.main.activity_lateralidad_1.btnSalirCA




class Lateralidad_1 : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    //private final val email: String? = Firebase.auth.currentUser?.email
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lateralidad_1)

        instruccionesPruebaLateralidad()

        seleccionarColorAuto_1()

        seleccionarColorAuto_2()

        seleccionarColorAuto_3()

        seleccionarColorAuto_4()

        seleccionarColorAuto_5()

        siguiente()

        salir()

        buttonsSetEnabledFalseLateralidad1()

        imgCar1Instrucciones.setColorFilter(Color.rgb(0, 0, 255))

        imgCar2Instrucciones.setColorFilter(Color.rgb(255, 87, 51))

    }

    private fun instruccionesPruebaLateralidad() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesLateralidad1.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesLateralidad1.setEnabled(false)
                Thread.sleep(2000)
                buttonsSetEnabledTrueLateralidad1()
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun seleccionarColorAuto_1() {

        btn1_car1.setOnClickListener {
            car1.setColorFilter(Color.rgb(0, 0, 255))
            btn1_car1.setEnabled(false)
            btn2_car1.setEnabled(true)
            clicks++
            hits++
        }

        btn2_car1.setOnClickListener {
            car1.setColorFilter(Color.rgb(255, 87, 51))
            btn2_car1.setEnabled(false)
            btn1_car1.setEnabled(true)
            clicks++
            misses++
        }
    }

    private fun seleccionarColorAuto_2() {

        btn3_car2.setOnClickListener {
            car2.setColorFilter(Color.rgb(0, 0, 255))
            btn3_car2.setEnabled(false)
            btn4_car2.setEnabled(true)
            clicks++
            misses++

        }

        btn4_car2.setOnClickListener {
            car2.setColorFilter(Color.rgb(255, 87, 51))
            btn4_car2.setEnabled(false)
            btn3_car2.setEnabled(true)
            clicks++
            hits++
        }
    }

    private fun seleccionarColorAuto_3() {

        btn5_car3.setOnClickListener {
            car3.setColorFilter(Color.rgb(0, 0, 255))
            btn5_car3.setEnabled(false)
            btn6_car3.setEnabled(true)
            clicks++
            hits++
        }

        btn6_car3.setOnClickListener {
            car3.setColorFilter(Color.rgb(255, 87, 51))
            btn6_car3.setEnabled(false)
            btn5_car3.setEnabled(true)
            clicks++
            misses++
        }
    }

    private fun seleccionarColorAuto_4() {

        btn7_car4.setOnClickListener {
            car4.setColorFilter(Color.rgb(0, 0, 255))
            btn7_car4.setEnabled(false)
            btn8_car4.setEnabled(true)
            clicks++
            misses++
        }

        btn8_car4.setOnClickListener {
            car4.setColorFilter(Color.rgb(255, 87, 51))
            btn8_car4.setEnabled(false)
            btn7_car4.setEnabled(true)
            clicks++
            hits++
        }
    }

    private fun seleccionarColorAuto_5() {

        btn9_car5.setOnClickListener {
            car5.setColorFilter(Color.rgb(0, 0, 255))
            btn9_car5.setEnabled(false)
            btn10_car5.setEnabled(true)
            clicks++
            misses++
        }

        btn10_car5.setOnClickListener {
            car5.setColorFilter(Color.rgb(255, 87, 51))
            btn10_car5.setEnabled(false)
            btn9_car5.setEnabled(true)
            clicks++
            hits++
        }
    }

    private fun buttonsSetEnabledFalseLateralidad1() {
        val botonesCarros = arrayListOf<Button>(btn1_car1, btn2_car1, btn3_car2, btn4_car2, btn5_car3, btn6_car3, btn7_car4, btn8_car4, btn9_car5, btn10_car5)

        for (i in botonesCarros) {
            i.setEnabled(false)
            i.setBackgroundColor(Color.GRAY)
        }

    }

    private fun buttonsSetEnabledTrueLateralidad1() {
        val botonesCarros = arrayListOf<Button>(btn1_car1, btn2_car1, btn3_car2, btn4_car2, btn5_car3, btn6_car3, btn7_car4, btn8_car4, btn9_car5, btn10_car5)

        for (i in botonesCarros) {
            i.setEnabled(true)
            i.setBackgroundColor(Color.TRANSPARENT)
        }

    }

    private fun siguiente() {

        btnSiguienteLateralidad1.setOnClickListener {
            Firebase.auth.currentUser?.email?.let { it1 ->
                db.collection("LateralidadPrueba1").document(it1).set(
                        hashMapOf("Clicks" to clicks,
                                "Hits" to hits,
                                "Misses" to misses)
                )
            }
        }
    }

    private fun salir() {

        btnSalirCA.setOnClickListener {
            onBackPressed()
        }
    }
}








