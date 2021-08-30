package com.example.myapplication.procesosPerceptivos

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
import kotlinx.android.synthetic.main.activity_discriminacion_c_auditiva.*
import java.util.*
import kotlin.collections.ArrayList

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

        menu()

        ocultarInhabilitarBotonesAudio()

        btnPalabra1PruebaDCA.isEnabled = false

        audioInstruccion()
    }

    private fun menu(){

        btnMenuDiscriminacionAuditiva.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun ocultarInhabilitarBotonesAudio(){

        listaBotonesDiscriminacionAuditiva().forEach {
            it.isEnabled = false
            it.isVisible = false
        }
    }

    private fun mostrarHabilitarBotonesAudio(){

        listaBotonesDiscriminacionAuditiva().forEach {
            it.isEnabled = true
            it.isVisible = true
        }
    }

    private fun listaBotonesDiscriminacionAuditiva():ArrayList<Button>{

        return arrayListOf(btn_rta1_DiscriminizacionAuditiva,
            btn_rta2_DiscriminizacionAuditiva,
            btn_rta3_DiscriminizacionAuditiva,
            btn_rta4_DiscriminizacionAuditiva)
           /* btnPalabra1PruebaDCA,
            btnPalabra2PruebaDCA,*/
            //btnPalabra3PruebaDCA)
    }

    private fun instruccionesPruebaDiscriminizacionCategorizacionAuditiva() {

        val mp = MediaPlayer.create(this, R.raw.discriminacionauditiva2)

        btnInstruccionesDiscriminizacionCA.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesDiscriminizacionCA.isEnabled = false
                Thread.sleep(16000)
                layoutDiscriminacionAuditiva.isVisible = true
                btnPalabra1PruebaDCA.isEnabled = true
                btnPalabra1PruebaDCA.isVisible = true
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun audioInstruccion(){

        val mp = MediaPlayer.create(this, R.raw.pruebadiscriminacionauditiva)

        btnPalabra1PruebaDCA.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(4000)
                btnPalabra1PruebaDCA.isEnabled = false
                habilitarBotonesPrueba1()
                mostrarHabilitarBotonesAudio()
            }
        }
    }

    private fun habilitarBotonesPrueba1() {

        PRUEBA = arrayListOf("udp", "upb", "upq", "upt")

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
            validacionPalabraCorrecta("cap", btn_rta1_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
        }

        btn_rta2_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cap", btn_rta2_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
        }

        btn_rta3_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cap", btn_rta3_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
        }

        btn_rta4_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("cap", btn_rta4_DiscriminizacionAuditiva)
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
            validacionPalabraCorrecta("enm", btn_rta1_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
            habilitarBotonSiguiente()
        }

        btn_rta2_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enm", btn_rta2_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
            habilitarBotonSiguiente()
        }

        btn_rta3_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enm", btn_rta3_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
            habilitarBotonSiguiente()
        }

        btn_rta4_DiscriminizacionAuditiva.setOnClickListener {
            clicks++
            validacionPalabraCorrecta("enm", btn_rta4_DiscriminizacionAuditiva)
            habilitarBotonesPrueba3()
            habilitarBotonSiguiente()
        }
    }

    private fun validacionPalabraCorrecta(palabra: String, boton: Button) {

        if (boton.text.toString() == palabra) {
            hits++
        }else{
            misses++
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
                db.collection(email).document("DiscriminaciónCategorizacionAuditiva").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }

            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}