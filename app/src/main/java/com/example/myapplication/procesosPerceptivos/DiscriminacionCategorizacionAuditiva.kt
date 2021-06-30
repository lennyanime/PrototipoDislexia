package com.example.myapplication.procesosPerceptivos

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_discriminacion_c_auditiva.*
import kotlinx.android.synthetic.main.activity_memoria_trabajo_auditiva.*
import java.util.*

private lateinit var PRUEBA: ArrayList<String>

class DiscriminacionCategorizacionAuditiva : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discriminacion_c_auditiva)

        instruccionesPruebaDiscriminizacionCategorizacionAuditiva()

        btnSiguienteDiscriminizacionCA.isEnabled = false

        siguiente()
    }

    private fun instruccionesPruebaDiscriminizacionCategorizacionAuditiva() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesDiscriminizacionCA.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesDiscriminizacionCA.setEnabled(false)
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

        validacionPalabraAleatorias(btn_rta1_DiscriminizacionAuditiva,
            btn_rta2_DiscriminizacionAuditiva,
            btn_rta3_DiscriminizacionAuditiva,
            btn_rta4_DiscriminizacionAuditiva)

        btn_rta1_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udp", btn_rta1_DiscriminizacionAuditiva)
            habilitarBotonesPrueba2()
        }

        btn_rta2_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udp", btn_rta2_DiscriminizacionAuditiva)
            habilitarBotonesPrueba2()
        }

        btn_rta3_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udp", btn_rta3_DiscriminizacionAuditiva)
            habilitarBotonesPrueba2()
        }

        btn_rta4_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udp", btn_rta4_DiscriminizacionAuditiva)
            habilitarBotonesPrueba2()
        }
    }

    private fun habilitarBotonesPrueba2() {

        PRUEBA = arrayListOf("cal", "cqo", "cdi", "cap")

        validacionPalabraAleatorias(btn_rta1_DiscriminizacionAuditiva,
            btn_rta2_DiscriminizacionAuditiva,
            btn_rta3_DiscriminizacionAuditiva,
            btn_rta4_DiscriminizacionAuditiva)

        btn_rta1_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cdi", btn_rta1_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
        }

        btn_rta2_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cdi", btn_rta2_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
        }

        btn_rta3_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cdi", btn_rta3_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
        }

        btn_rta4_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cdi", btn_rta4_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
        }
    }

    private fun habilitarBotonesPrueba3() {

        PRUEBA = arrayListOf("enp", "enm", "esd", "emt")

        validacionPalabraAleatorias(btn_rta1_DiscriminizacionAuditiva,
            btn_rta2_DiscriminizacionAuditiva,
            btn_rta3_DiscriminizacionAuditiva,
            btn_rta4_DiscriminizacionAuditiva)

        btn_rta1_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enp", btn_rta1_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
            habilitarBotonSiguiente()
        }

        btn_rta2_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enp", btn_rta2_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
            habilitarBotonSiguiente()
        }

        btn_rta3_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enp", btn_rta3_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
            habilitarBotonSiguiente()
        }

        btn_rta4_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enp", btn_rta4_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
            habilitarBotonSiguiente()
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

        val botonesDiscriminacionCategorizacionAuditiva = arrayListOf<Button>(btn_rta1_DiscriminizacionAuditiva,
            btn_rta2_DiscriminizacionAuditiva,
            btn_rta3_DiscriminizacionAuditiva,
            btn_rta4_DiscriminizacionAuditiva)

        botonesDiscriminacionCategorizacionAuditiva.forEach {

            it.text = PRUEBA.random()

            while (a.text == b.text || a.text == c.text || a.text == d.text)
                a.text =
                    PRUEBA[random.nextInt(PRUEBA.size)]

            while (a.text == b.text || b.text == c.text || b.text == d.text)
                b.text =
                    PRUEBA[random.nextInt(PRUEBA.size)]

            while (c.text == b.text || a.text == c.text || c.text == d.text)
                c.text =
                    PRUEBA[random.nextInt(PRUEBA.size)]

            while (d.text == b.text || d.text == c.text || a.text == d.text)
                d.text =
                    PRUEBA[random.nextInt(PRUEBA.size)]
        }
    }

    private fun habilitarBotonSiguiente() {

        if (clicks == 3) {
            btnSiguienteDiscriminizacionCA.isEnabled = true
            botonesDeshabilitados()
        }
    }

    private fun botonesDeshabilitados() {

        val botonesDiscriminacionCategorizacionAuditiva = arrayListOf<Button>(
            btn_rta1_DiscriminizacionAuditiva,
            btn_rta2_DiscriminizacionAuditiva,
            btn_rta3_DiscriminizacionAuditiva,
            btn_rta4_DiscriminizacionAuditiva)

        botonesDiscriminacionCategorizacionAuditiva.forEach {
            it.isEnabled = false
        }
    }

    private fun siguiente() {

        btnSiguienteDiscriminizacionCA.setOnClickListener {
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