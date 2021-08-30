package com.example.myapplication.memoriaDeTrabajo

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_memoria_trabajo_auditiva.*
import java.util.*
import kotlin.collections.ArrayList

private lateinit var PRUEBA: ArrayList<String>

class MemoriaTrabajoAuditiva : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()

    private val user = Firebase.auth.currentUser

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

        inhabilitarBotonesPrimeraVez()

        siguiente()

        menu()
    }

    private fun menu() {

        btnMenuMemoriaAuditiva.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun habilitarBotonesPrimeraVez() {

        listaBotonesMemoriaAuditiva().forEach {
            it.isVisible = true
            it.isEnabled = true
        }
    }

    private fun inhabilitarBotonesPrimeraVez() {

        listaBotonesMemoriaAuditiva().forEach {
            it.isVisible = false
            it.isEnabled = false
        }
    }

    private fun listaBotonesMemoriaAuditiva(): ArrayList<Button> {

        return arrayListOf(
            btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva
        )
    }

    private fun instruccionesPruebaMemoriaTrabajoAuditiva() {

        val mp = MediaPlayer.create(this, R.raw.memoriaauditiva3)

        btnInstruccionesMemoriaTAuditiva.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesMemoriaTAuditiva.setEnabled(false)
                Thread.sleep(28000)
                layoutFila2MemoriaAuditiva.isVisible = true
                layoutFila1MemoriaAuditiva.isVisible = true

                habilitarBotonesPrimeraVez()
                habilitarBotonesPrueba1()
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun habilitarBotonesPrueba1() {

        PRUEBA = arrayListOf("udp", "upb", "upq", "udb")

        validacionPalabraAleatorias(
            btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva
        )

        btn_rta1_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udb", btn_rta1_memoriatrabajoaudtiva)
            habilitarBotonesPrueba2()
        }

        btn_rta2_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udb", btn_rta2_memoriatrabajoaudtiva)
            habilitarBotonesPrueba2()
        }

        btn_rta3_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udb", btn_rta3_memoriatrabajoaudtiva)
            habilitarBotonesPrueba2()
        }

        btn_rta4_memoriatrabajoaudtiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("udb", btn_rta4_memoriatrabajoaudtiva)
            habilitarBotonesPrueba2()
        }
    }

    private fun habilitarBotonesPrueba2() {

        PRUEBA = arrayListOf("cal", "cqo", "cdi", "cap")

        validacionPalabraAleatorias(
            btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva
        )

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

        PRUEBA = arrayListOf("enp", "enm", "esd", "enq")
        validacionPalabraAleatorias(
            btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva
        )

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

    private fun habilitarBotonSiguiente() {

        if (clicks == 3) {
            btnSiguienteMemoriaTrabajoAuditiva.isEnabled = true
            inhabilitarBotones()
        }
    }

    private fun validacionPalabraCorrecta(palabra: String, boton: Button) {

        if (boton.text.toString() == palabra) {

            hits++
        }
    }

    private fun validacionPalabraAleatorias(a: Button, b: Button, c: Button, d: Button) {

        val BOTONES_MEMORIA_AUDITIVA = arrayListOf<Button>(
            btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva
        )

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

        val botonesMemoriaTrabajoAuditiva = arrayListOf<Button>(
            btn_rta1_memoriatrabajoaudtiva,
            btn_rta2_memoriatrabajoaudtiva,
            btn_rta3_memoriatrabajoaudtiva,
            btn_rta4_memoriatrabajoaudtiva
        )

        botonesMemoriaTrabajoAuditiva.forEach {
            it.setEnabled(false)
            it.text = ""
        }
    }

    private fun siguiente() {

        btnSiguienteMemoriaTrabajoAuditiva.setOnClickListener {

            misses = PUNTAJEMAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("MemoriaTrabajoAuditiva").set(
                    hashMapOf(
                        "Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses
                    )
                )
            }

            obtenerDocumento(
                user?.email.toString(),
                "MemoriaSecuencialAuditiva",
                MemoriaSecuencialAuditiva()
            )
        }
    }

    private fun obtenerDocumento(
        correo: String,
        documento: String,
        activity: Activity
    ) {

        val document = DB.collection(correo).document(documento)
        document.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val intent = Intent(this, Componentes()::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, activity::class.java)
                    startActivity(intent)
                }
            }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}