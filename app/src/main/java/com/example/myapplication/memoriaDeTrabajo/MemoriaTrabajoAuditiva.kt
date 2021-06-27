package com.example.myapplication.memoriaDeTrabajo

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_fonica.view.*
import kotlinx.android.synthetic.main.activity_memoria_trabajo_auditiva.*
import java.util.*

private lateinit var PRUEBA: ArrayList<String>
private lateinit var LETRAS: MutableList<String>
private lateinit var TAG: MutableList<String>
private val map: HashMap<Button, String> = hashMapOf()

class MemoriaTrabajoAuditiva : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJEMAXIMO = 3
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoria_trabajo_auditiva)

        btnSiguienteMemoriaTrabajoAuditiva.isEnabled = false

        instruccionesPruebaMemoriaTrabajoAuditiva()

        siguiente()

    }

    private fun instruccionesPruebaMemoriaTrabajoAuditiva() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesMemoriaTAuditiva.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesMemoriaTAuditiva.setEnabled(false)
                Thread.sleep(2000)
                habilitarBotonesPrueba1()
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun habilitarBotonesPrueba1() {

        PRUEBA = arrayListOf("udp", "upb", "upq", "udq")
        validacionPalabraAleatorias(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        val BOTONES_MEMORIA_AUDITIVA = arrayListOf<Button>(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        BOTONES_MEMORIA_AUDITIVA.forEach {

            it.setOnClickListener {

                clicks++
                validacionPalabraCorrecta("udp")
                habilitarBotonesPrueba2()
            }
        }
    }

    private fun habilitarBotonesPrueba2() {
        PRUEBA = arrayListOf("cal", "cqo", "cdi", "cap")

        val BOTONES_MEMORIA_AUDITIVA = arrayListOf<Button>(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        validacionPalabraAleatorias(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        BOTONES_MEMORIA_AUDITIVA.forEach {

            it.setOnClickListener {
                clicks++
                validacionPalabraCorrecta("cal")
                habilitarBotonesPrueba3()
            }
        }
    }

    private fun habilitarBotonesPrueba3() {

        val BOTONES_MEMORIA_AUDITIVA = arrayListOf<Button>(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        PRUEBA = arrayListOf("enp", "enm", "esd", "emt")
        validacionPalabraAleatorias(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        BOTONES_MEMORIA_AUDITIVA.forEach {
            it.setOnClickListener {

                clicks++
                habilitarBotonesPrueba3()
                validacionPalabraCorrecta("enp")
                inhabilitarBotones()
                btnSiguienteMemoriaTrabajoAuditiva.isEnabled = true

                if (clicks == 3) {
                    btnSiguienteMemoriaTrabajoAuditiva.isEnabled = true
                    //it.text = ""
                }
            }
        }
    }

    private fun validacionPalabraCorrecta(palabra: String) {
        Toast.makeText(applicationContext,
            palabra,
            Toast.LENGTH_SHORT).show()
        val BOTONES_MEMORIA_AUDITIVA = arrayListOf<Button>(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        BOTONES_MEMORIA_AUDITIVA.forEach {

            it.setOnClickListener {

                for(i in BOTONES_MEMORIA_AUDITIVA.iterator()) {

                    if (i.tag.toString() == palabra) {
                        hits++
                    }

                }
            }
        }
    }

    private fun validacionPalabraAleatorias(a: Button, b: Button, c: Button, d: Button) {

        val BOTONES_MEMORIA_AUDITIVA = arrayListOf<Button>(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        BOTONES_MEMORIA_AUDITIVA.forEach {

            it.text = PRUEBA.random()

            while (a.text == b.text || a.text == c.text || a.text == d.text)
                a.text = PRUEBA[random.nextInt(PRUEBA.size)]

            while (a.text == b.text || b.text == c.text || b.text == d.text)
                b.text = PRUEBA[random.nextInt(PRUEBA.size)]

            while (c.text == b.text || a.text == c.text || c.text == d.text)
                c.text = PRUEBA[random.nextInt(PRUEBA.size)]

            while (d.text == b.text || d.text == c.text || a.text == d.text)
                d.text = PRUEBA[random.nextInt(PRUEBA.size)]

        }
    }

    private fun inhabilitarBotones() {

        val botonesMemoriaTrabajoAuditiva = arrayListOf<Button>(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        botonesMemoriaTrabajoAuditiva.forEach {
            it.setEnabled(false)
        }
    }

    private fun siguiente() {

        val BOTONES_MEMORIA_AUDITIVA = arrayListOf<Button>(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        btnSiguienteMemoriaTrabajoAuditiva.setOnClickListener {

            misses = PUNTAJEMAXIMO - hits

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