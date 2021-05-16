package com.example.myapplication.procesosPerceptivos

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_discriminicion_visual.*
import kotlinx.android.synthetic.main.activity_discriminicion_visual.view.*
import kotlinx.android.synthetic.main.activity_lateralidad_1.*
import kotlinx.android.synthetic.main.activity_lateralidad_2.*
import kotlin.time.milliseconds

/*
val  IMAGENES_DISCRIMINIZCION_VISUAL = arrayListOf<ImageView>(img1PruebaDiscriminizacionVisual, img2PruebaDiscriminizacionVisual, img3PruebaDiscriminizacionVisual,
        img4PruebaDiscriminizacionVisual, img5PruebaDiscriminizacionVisual, img6PruebaDiscriminizacionVisual)
*/
class DiscriminacionCategorizacionVisual : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val EMAIL = Firebase.auth.currentUser?.email
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var t1: Int = 0
    private var t2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discriminicion_visual)

        instruccionesPruebaDiscriminizacionVisual()

        desactivarImagenes()


    }

    private fun instruccionesPruebaDiscriminizacionVisual() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesDiscriminizacionVisual.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesDiscriminizacionVisual.setEnabled(false)
                Thread.sleep(4000)
                activarImagenes()
                btnInstruccionesDiscriminizacionVisual.setEnabled(true)
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun desactivarImagenes() {

        val IMAGENES_DISCRIMINIZCION_VISUAL = arrayListOf<ImageView>(img1PruebaDiscriminizacionVisual, img2PruebaDiscriminizacionVisual, img3PruebaDiscriminizacionVisual,
                img4PruebaDiscriminizacionVisual, img5PruebaDiscriminizacionVisual, img6PruebaDiscriminizacionVisual)

        IMAGENES_DISCRIMINIZCION_VISUAL.forEach { it.setEnabled(false) }
    }

    private fun activarImagenes() {

        val IMAGENES_DISCRIMINIZCION_VISUAL = arrayListOf<ImageView>(img1PruebaDiscriminizacionVisual, img2PruebaDiscriminizacionVisual, img3PruebaDiscriminizacionVisual,
                img4PruebaDiscriminizacionVisual, img5PruebaDiscriminizacionVisual, img6PruebaDiscriminizacionVisual)

        IMAGENES_DISCRIMINIZCION_VISUAL.forEach { it.setEnabled(true) }
    }

    private fun prueba(){

        if(img4PruebaDiscriminizacionVisual.isActivated && img5PruebaDiscriminizacionVisual.isActivated){

        }

        img4PruebaDiscriminizacionVisual.setOnClickListener{
            t1=1
        }   

        img5PruebaDiscriminizacionVisual.setOnClickListener{
            t2=1
        }
    }
}