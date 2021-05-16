package com.example.myapplication.memoriaDeTrabajo

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_lateralidad_1.*
import kotlinx.android.synthetic.main.activity_memoria_trabajo_auditiva.*
import java.util.*

class MemoriaTrabajoAuditiva : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val wordsMTrabajoAuditiva = arrayListOf("udp", "upb", "upq", "udq")
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private val palabraCorrecta: String = "upd"
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoria_trabajo_auditiva)

        btnSiguienteMemoriaTrabajoAuditiva.setEnabled(false)

        instruccionesPruebaMemoriaTrabajoAuditiva()

        opcion1()

        opcion2()

        opcion3()

        opcion4()

        siguiente()
    }

    private fun instruccionesPruebaMemoriaTrabajoAuditiva() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesMemoriaTAuditiva.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesMemoriaTAuditiva.setEnabled(false)
                Thread.sleep(2000)

                btn_rta1_memoriatrabajoaudtiva.setText(wordsMTrabajoAuditiva[random.nextInt(wordsMTrabajoAuditiva.size)])

                btn_rta2_memoriatrabajoaudtiva.setText(wordsMTrabajoAuditiva[random.nextInt(wordsMTrabajoAuditiva.size)])
                //btn_rta3_memoriatrabajoaudtiva.setText(wordsMTrabajoAuditiva[random.nextInt(wordsMTrabajoAuditiva.size)])
                btn_rta3_memoriatrabajoaudtiva.setText(palabraCorrecta)

                btn_rta4_memoriatrabajoaudtiva.setText(wordsMTrabajoAuditiva[random.nextInt(wordsMTrabajoAuditiva.size)])

                while (btn_rta1_memoriatrabajoaudtiva.getText() == btn_rta2_memoriatrabajoaudtiva.getText() || btn_rta1_memoriatrabajoaudtiva.getText() == btn_rta4_memoriatrabajoaudtiva)
                    btn_rta1_memoriatrabajoaudtiva.setText(wordsMTrabajoAuditiva[random.nextInt(wordsMTrabajoAuditiva.size)])

                while (btn_rta2_memoriatrabajoaudtiva.getText() == btn_rta1_memoriatrabajoaudtiva.getText() || btn_rta2_memoriatrabajoaudtiva.getText() == btn_rta4_memoriatrabajoaudtiva)
                    btn_rta2_memoriatrabajoaudtiva.setText(wordsMTrabajoAuditiva[random.nextInt(wordsMTrabajoAuditiva.size)])

                while (btn_rta4_memoriatrabajoaudtiva.getText() == btn_rta1_memoriatrabajoaudtiva.getText() || btn_rta4_memoriatrabajoaudtiva.getText() == btn_rta2_memoriatrabajoaudtiva)
                    btn_rta4_memoriatrabajoaudtiva.setText(wordsMTrabajoAuditiva[random.nextInt(wordsMTrabajoAuditiva.size)])
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun opcion1() {

        btn_rta1_memoriatrabajoaudtiva.setOnClickListener {
            botonesDeshabilitados()
            botonSiguienteHabilitado()
            clicks++
            misses++
        }
    }

    private fun opcion2() {

        btn_rta2_memoriatrabajoaudtiva.setOnClickListener {
            botonesDeshabilitados()
            botonSiguienteHabilitado()
            clicks++
            misses++
        }
    }

    private fun opcion3() {

        btn_rta3_memoriatrabajoaudtiva.setOnClickListener {
            botonesDeshabilitados()
            botonSiguienteHabilitado()
            clicks++
            hits++
        }
    }

    private fun opcion4() {

        btn_rta4_memoriatrabajoaudtiva.setOnClickListener {
            botonesDeshabilitados()
            botonSiguienteHabilitado()
            clicks++
            misses++
        }
    }

    private fun botonSiguienteHabilitado() {

        btnSiguienteMemoriaTrabajoAuditiva.setEnabled(true)
    }

    private fun botonesDeshabilitados() {

        val botonesMemoriaTrabajoAuditiva = arrayListOf<Button>(btn_rta1_memoriatrabajoaudtiva, btn_rta2_memoriatrabajoaudtiva, btn_rta3_memoriatrabajoaudtiva, btn_rta4_memoriatrabajoaudtiva)
        botonesMemoriaTrabajoAuditiva.forEach {
            it.setEnabled(false)
        }
    }

    private fun siguiente() {

        btnSiguienteMemoriaTrabajoAuditiva.setOnClickListener{
            Firebase.auth.currentUser?.email?.let { email ->
                db.collection(email).document("MemoriaTrabajoAuditiva").set(
                        hashMapOf("Clicks" to clicks,
                                "Hits" to hits,
                                "Misses" to misses)
                )
            }
        }

    }
}