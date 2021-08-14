package com.example.myapplication.lateralidad

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_lateralidad_1.*


class Lateralidad_1 : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private val PUNTAJE_MAXIMO: Int = 5
    private var hits1: Int = 0
    private var hits2: Int = 0
    private var hits3: Int = 0
    private var hits4: Int = 0
    private var hits5: Int = 0
    private var stop: Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lateralidad_1)

        instruccionesPruebaLateralidad1()

        seleccionarColorAuto_1()

        seleccionarColorAuto_2()

        seleccionarColorAuto_3()

        seleccionarColorAuto_4()

        seleccionarColorAuto_5()

        siguiente()

        menuPrincipal()

        buttonsSetEnabledFalseLateralidad1()

        imgCar1Instrucciones.setColorFilter(Color.rgb(0, 0, 255))

        imgCar2Instrucciones.setColorFilter(Color.rgb(255, 87, 51))

        btnSiguienteLateralidad1.setEnabled(false)

    }

    private fun habilitarBotonSiguiente() {
        btnSiguienteLateralidad1.setEnabled(true)
    }

    private fun instruccionesPruebaLateralidad1() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesLateralidad1.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesLateralidad1.setEnabled(false)
                Thread.sleep(2000)
                //buttonsSetEnabledTrueLateralidad1()
                habilitarPruebaAuto1()
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun habilitarPruebaAuto1() {

        val BOTONES_PRUEBA_1 = arrayListOf<Button>(btn1_car1, btn2_car1)

        BOTONES_PRUEBA_1.forEach {

            it.setEnabled(true)
            it.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun habilitarPruebaAuto2() {

        val BOTONES_PRUEBA_2 = arrayListOf<Button>(btn3_car2, btn4_car2)

        BOTONES_PRUEBA_2.forEach {

            it.setEnabled(true)
            it.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun habilitarPruebaAuto3() {

        val BOTONES_PRUEBA_3 = arrayListOf<Button>(btn5_car3, btn6_car3)

        BOTONES_PRUEBA_3.forEach {

            it.setEnabled(true)
            it.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun habilitarPruebaAuto4() {

        val BOTONES_PRUEBA_4 = arrayListOf<Button>(btn7_car4, btn8_car4)

        BOTONES_PRUEBA_4.forEach {

            it.setEnabled(true)
            it.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun habilitarPruebaAuto5() {

        val BOTONES_PRUEBA_5 = arrayListOf<Button>(btn9_car5, btn10_car5)

        BOTONES_PRUEBA_5.forEach {

            it.setEnabled(true)
            it.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun seleccionarColorAuto_1() {

        btn1_car1.setOnClickListener {
            car1.setColorFilter(Color.rgb(0, 0, 255))
            btn1_car1.setEnabled(false)
            btn2_car1.setEnabled(true)
            clicks++
            hits1++
            hits1 = hitsAcierto(hits1)
            habilitarPruebaAuto2()
        }

        btn2_car1.setOnClickListener {
            car1.setColorFilter(Color.rgb(255, 87, 51))
            btn2_car1.setEnabled(false)
            btn1_car1.setEnabled(true)
            clicks++
            hits1--
            hits1 = hitsError(hits1)
            habilitarPruebaAuto2()
        }
    }

    private fun seleccionarColorAuto_2() {

        btn3_car2.setOnClickListener {
            car2.setColorFilter(Color.rgb(0, 0, 255))
            btn3_car2.setEnabled(false)
            btn4_car2.setEnabled(true)
            clicks++
            hits2--
            hits2 = hitsError(hits2)
            habilitarPruebaAuto3()
        }

        btn4_car2.setOnClickListener {
            car2.setColorFilter(Color.rgb(255, 87, 51))
            btn4_car2.setEnabled(false)
            btn3_car2.setEnabled(true)
            clicks++
            hits2++
            hits2 = hitsAcierto(hits2)
            habilitarPruebaAuto3()
        }
    }

    private fun seleccionarColorAuto_3() {

        btn5_car3.setOnClickListener {
            car3.setColorFilter(Color.rgb(0, 0, 255))
            btn5_car3.setEnabled(false)
            btn6_car3.setEnabled(true)
            clicks++
            hits3++
            hits3 = hitsAcierto(hits3)
            habilitarPruebaAuto4()
        }

        btn6_car3.setOnClickListener {
            car3.setColorFilter(Color.rgb(255, 87, 51))
            btn6_car3.setEnabled(false)
            btn5_car3.setEnabled(true)
            clicks++
            hits3--
            hits3 = hitsError(hits3)
            habilitarPruebaAuto4()
        }
    }

    private fun seleccionarColorAuto_4() {

        btn7_car4.setOnClickListener {
            car4.setColorFilter(Color.rgb(0, 0, 255))
            btn7_car4.setEnabled(false)
            btn8_car4.setEnabled(true)
            clicks++
            hits4--
            hits4 = hitsError(hits4)
            habilitarPruebaAuto5()
        }

        btn8_car4.setOnClickListener {
            car4.setColorFilter(Color.rgb(255, 87, 51))
            btn8_car4.setEnabled(false)
            btn7_car4.setEnabled(true)
            clicks++
            hits4++
            hits4 = hitsAcierto(hits4)
            habilitarPruebaAuto5()
        }
    }

    private fun seleccionarColorAuto_5() {

        btn9_car5.setOnClickListener {
            car5.setColorFilter(Color.rgb(0, 0, 255))
            btn9_car5.setEnabled(false)
            btn10_car5.setEnabled(true)
            clicks++
            hits5--
            hits5 = hitsError(hits5)
            habilitarBotonSiguiente()
        }

        btn10_car5.setOnClickListener {
            car5.setColorFilter(Color.rgb(255, 87, 51))
            btn10_car5.setEnabled(false)
            btn9_car5.setEnabled(true)
            clicks++
            hits5++
            hits5 = hitsAcierto(hits5)
            habilitarBotonSiguiente()
        }
    }

    private fun buttonsSetEnabledFalseLateralidad1() {
        val botonesCarros = arrayListOf<Button>(btn1_car1, btn2_car1, btn3_car2, btn4_car2, btn5_car3, btn6_car3, btn7_car4, btn8_car4, btn9_car5, btn10_car5)

        for (i in botonesCarros) {
            i.setEnabled(false)
            i.setBackgroundColor(Color.GRAY)
        }
    }

    private fun hitsError(restarHits: Int): Int {
        var _restarHits = restarHits

        if (_restarHits == 0 || _restarHits < 0)
            _restarHits = 0

        if (_restarHits == 1)
            _restarHits--

        return _restarHits
    }

    private fun hitsAcierto(acierto: Int): Int {
        var _acierto = acierto

        if (_acierto == 1 || _acierto > 1)
            _acierto = 1

        if (_acierto == 0)
            _acierto++

        return _acierto
    }

    private fun siguiente() {

        btnSiguienteLateralidad1.setOnClickListener {

            stop++
            hits = hits1 + hits2 + hits3 + hits4 + hits5
            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("LateralidadPrueba1").set(
                        hashMapOf("Clicks" to clicks,
                                "Hits" to hits,
                                "Misses" to misses)
                )
            }
        }
    }

    private fun menuPrincipal() {

        btnSalirCA.setOnClickListener {
            onBackPressed()

        }
    }
}













