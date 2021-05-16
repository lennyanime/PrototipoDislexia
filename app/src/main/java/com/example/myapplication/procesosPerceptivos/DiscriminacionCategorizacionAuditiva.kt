package com.example.myapplication.procesosPerceptivos

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_discriminacion_c_auditiva.*
import kotlinx.android.synthetic.main.activity_lateralidad_1.*
import kotlinx.android.synthetic.main.activity_memoria_trabajo_auditiva.*
import java.util.*

class DiscriminacionCategorizacionAuditiva : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val wordsDiscriminizacionAuditiva = arrayListOf("udp", "upb", "upq", "udq")
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private val palabraCorrecta: String = "tup"
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discriminacion_c_auditiva)

        instruccionesPruebaDiscriminizacionCategorizacionAuditiva()

        botonesDeshabilitados()

        opcion1()

        opcion2()

        opcion3()

        opcion4()

        siguiente()
    }

    private fun instruccionesPruebaDiscriminizacionCategorizacionAuditiva() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesDiscriminizacionCA.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesDiscriminizacionCA.setEnabled(false)
                Thread.sleep(2000)

                botonesHabilitados()

                btn_rta1_DiscriminizacionAuditiva.setText(wordsDiscriminizacionAuditiva[random.nextInt(wordsDiscriminizacionAuditiva.size)])

                btn_rta2_DiscriminizacionAuditiva.setText(wordsDiscriminizacionAuditiva[random.nextInt(wordsDiscriminizacionAuditiva.size)])

                btn_rta3_DiscriminizacionAuditiva.setText(palabraCorrecta)

                btn_rta4_DiscriminizacionAuditiva.setText(wordsDiscriminizacionAuditiva[random.nextInt(wordsDiscriminizacionAuditiva.size)])

                while (btn_rta1_DiscriminizacionAuditiva.getText() == btn_rta2_DiscriminizacionAuditiva.getText() || btn_rta1_DiscriminizacionAuditiva.getText() == btn_rta4_DiscriminizacionAuditiva)
                    btn_rta1_DiscriminizacionAuditiva.setText(wordsDiscriminizacionAuditiva[random.nextInt(wordsDiscriminizacionAuditiva.size)])

                while (btn_rta2_DiscriminizacionAuditiva.getText() == btn_rta1_DiscriminizacionAuditiva.getText() || btn_rta2_DiscriminizacionAuditiva.getText() == btn_rta4_DiscriminizacionAuditiva)
                    btn_rta2_DiscriminizacionAuditiva.setText(wordsDiscriminizacionAuditiva[random.nextInt(wordsDiscriminizacionAuditiva.size)])

                while (btn_rta4_DiscriminizacionAuditiva.getText() == btn_rta1_DiscriminizacionAuditiva.getText() || btn_rta4_DiscriminizacionAuditiva.getText() == btn_rta2_DiscriminizacionAuditiva)
                    btn_rta4_DiscriminizacionAuditiva.setText(wordsDiscriminizacionAuditiva[random.nextInt(wordsDiscriminizacionAuditiva.size)])

            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun opcion1(){

        btn_rta1_DiscriminizacionAuditiva.setOnClickListener {
            botonesDeshabilitados()
            botonSiguienteHabilitado()
            clicks++
            misses++
        }
    }

    private fun opcion2(){

        btn_rta2_DiscriminizacionAuditiva.setOnClickListener {
            botonesDeshabilitados()
            botonSiguienteHabilitado()
            clicks++
            misses++
        }

    }

    private fun opcion3(){

        btn_rta3_DiscriminizacionAuditiva.setOnClickListener {
            botonesDeshabilitados()
            botonSiguienteHabilitado()
            clicks++
            hits++
        }
    }

    private fun opcion4(){

        btn_rta4_DiscriminizacionAuditiva.setOnClickListener {
            botonesDeshabilitados()
            botonSiguienteHabilitado()
            clicks++
            misses++
        }
    }

    private fun botonSiguienteHabilitado() {

        btnSiguienteDiscriminizacionCA.setEnabled(true)
    }

    private fun botonesDeshabilitados() {

        val botonesDiscriminacionCategorizacionAuditiva = arrayListOf<Button>(btn_rta1_DiscriminizacionAuditiva, btn_rta2_DiscriminizacionAuditiva, btn_rta3_DiscriminizacionAuditiva, btn_rta4_DiscriminizacionAuditiva)
        botonesDiscriminacionCategorizacionAuditiva.forEach {
            it.setEnabled(false)
        }
    }

    private fun botonesHabilitados() {

        val botonesDiscriminacionCategorizacionAuditiva = arrayListOf<Button>(btn_rta1_DiscriminizacionAuditiva, btn_rta2_DiscriminizacionAuditiva, btn_rta3_DiscriminizacionAuditiva, btn_rta4_DiscriminizacionAuditiva)
        botonesDiscriminacionCategorizacionAuditiva.forEach {
            it.setEnabled(true)
        }
    }

    private fun siguiente() {

        btnSiguienteDiscriminizacionCA.setOnClickListener{
            Firebase.auth.currentUser?.email?.let { email ->
                db.collection(email).document("DiscriminizaciónCategorizacionAuditiva").set(
                        hashMapOf("Clicks" to clicks,
                                "Hits" to hits,
                                "Misses" to misses)
                )
            }
        }
    }
}