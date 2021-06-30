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
import kotlinx.android.synthetic.main.activity_memoria_trabajo_auditiva.*
import java.util.*

private lateinit var PRUEBA: ArrayList<String>

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

        btn_rta1_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udp", btn_rta1_memoriatrabajoaudtiva)
            habilitarBotonesPrueba2()
        }

        btn_rta2_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udp", btn_rta2_memoriatrabajoaudtiva)
            habilitarBotonesPrueba2()
        }

        btn_rta3_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udp", btn_rta3_memoriatrabajoaudtiva)
            habilitarBotonesPrueba2()
        }

        btn_rta4_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udp", btn_rta4_memoriatrabajoaudtiva)
            habilitarBotonesPrueba2()
        }
    }

    private fun habilitarBotonesPrueba2() {

        PRUEBA = arrayListOf("cal", "cqo", "cdi", "cap")

        validacionPalabraAleatorias(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        btn_rta1_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cal", btn_rta1_memoriatrabajoaudtiva)
            habilitarBotonesPrueba3()
        }

        btn_rta2_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cal", btn_rta2_memoriatrabajoaudtiva)
            habilitarBotonesPrueba3()
        }

        btn_rta3_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cal", btn_rta3_memoriatrabajoaudtiva)
            habilitarBotonesPrueba3()
        }

        btn_rta4_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cal", btn_rta4_memoriatrabajoaudtiva)
            habilitarBotonesPrueba3()
        }
    }

    private fun habilitarBotonesPrueba3() {

        PRUEBA = arrayListOf("enp", "enm", "esd", "emt")
        validacionPalabraAleatorias(btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva)

        btn_rta1_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enp", btn_rta1_memoriatrabajoaudtiva)
            habilitarBotonSiguiente()
        }

        btn_rta2_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enp", btn_rta2_memoriatrabajoaudtiva)
            habilitarBotonSiguiente()
        }

        btn_rta3_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enp", btn_rta3_memoriatrabajoaudtiva)
            habilitarBotonSiguiente()
        }

        btn_rta4_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enp", btn_rta4_memoriatrabajoaudtiva)
            habilitarBotonSiguiente()
        }
    }

    private fun habilitarBotonSiguiente(){

        if(clicks == 3){
            btnSiguienteMemoriaTrabajoAuditiva.isEnabled = true
            inhabilitarBotones()
        }
    }

    private fun validacionPalabraCorrecta(palabra: String, boton: Button) {

        if (boton.text.toString() == palabra) {

            hits++
            Toast.makeText(applicationContext,
                "entro, $hits",
                Toast.LENGTH_SHORT).show()
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
            it.text=""
        }
    }

    private fun siguiente() {

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