package com.example.myapplication.lateralidad

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_lateralidad_1.*

class Lateralidad_1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lateralidad_1)

        instruccionesPruebaLateralidad()

        seleccionarColorAuto_1()

        seleccionarColorAuto_2()

        seleccionarColorAuto_3()

        seleccionarColorAuto_4()

        seleccionarColorAuto_5()

        salir()
    }

    private fun instruccionesPruebaLateralidad(){

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        Img_btnaudio.setOnClickListener {
            if (!mp.isPlaying){
                mp.start()
            }else{
                mp.pause()
            }
        }
    }

    private fun seleccionarColorAuto_1() {

        //if (btn1_car1.isClickable)
            btn1_car1.setOnClickListener {Toast.makeText(applicationContext, "Color verde", Toast.LENGTH_SHORT).show()
                btn1_car1.setEnabled(false)
                btn2_car1.setEnabled(true)
            }

        btn2_car1.setOnClickListener{Toast.makeText(applicationContext, "Color amarillo", Toast.LENGTH_SHORT).show()
            btn2_car1.setEnabled(false)
            btn1_car1.setEnabled(true)
        }
    }

    private fun seleccionarColorAuto_2(){


        btn3_car2.setOnClickListener {Toast.makeText(applicationContext, "Color verde", Toast.LENGTH_SHORT).show()
            btn3_car2.setEnabled(false)
            btn4_car2.setEnabled(true)
        }

        btn4_car2.setOnClickListener{ Toast.makeText(applicationContext, "Color amarillo", Toast.LENGTH_SHORT).show()
            btn4_car2.setEnabled(false)
            btn3_car2.setEnabled(true)
        }
    }

    private fun seleccionarColorAuto_3(){


            btn5_car3.setOnClickListener {Toast.makeText(applicationContext, "Color verde", Toast.LENGTH_SHORT).show()
                btn5_car3.setEnabled(false)
                btn6_car3.setEnabled(true)
            }

        btn6_car3.setOnClickListener{ Toast.makeText(applicationContext, "Color amarillo", Toast.LENGTH_SHORT).show()
            btn6_car3.setEnabled(false)
            btn5_car3.setEnabled(true)
        }
    }

    private fun seleccionarColorAuto_4(){


        btn7_car4.setOnClickListener {Toast.makeText(applicationContext, "Color verde", Toast.LENGTH_SHORT).show()
            btn7_car4.setEnabled(false)
            btn8_car4.setEnabled(true)
        }

        btn8_car4.setOnClickListener{ Toast.makeText(applicationContext, "Color amarillo", Toast.LENGTH_SHORT).show()
            btn8_car4.setEnabled(false)
            btn7_car4.setEnabled(true)
        }
    }

    private fun seleccionarColorAuto_5(){


        btn9_car5.setOnClickListener {Toast.makeText(applicationContext, "Color verde", Toast.LENGTH_SHORT).show()
            btn9_car5.setEnabled(false)
            btn10_car5.setEnabled(true)
        }

        btn10_car5.setOnClickListener{ Toast.makeText(applicationContext, "Color amarillo", Toast.LENGTH_SHORT).show()
            btn10_car5.setEnabled(false)
            btn9_car5.setEnabled(true)
        }
    }

    private fun siguiente(){

    }

    private fun salir( ) {

        btnSalirCA.setOnClickListener{
            onBackPressed()
            super.onDestroy()
        }
    }
}








