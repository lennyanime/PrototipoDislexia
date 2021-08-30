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
import kotlinx.android.synthetic.main.activity_memoria_secuencial_auditiva.*

private var secuenciaAuditivaCorrecta: MutableList<String> =
    mutableListOf("pato", "perro", "gato", "gato", "pato", "gato")

private lateinit var secuenciaAuditivaRespuestas: MutableList<String>

class MemoriaSecuencialAuditiva : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()

    private val user = Firebase.auth.currentUser

    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0
    private var PUNTAJE_MAXIMO = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoria_secuencial_auditiva)

        btnSiguienteMTSA.isEnabled = false

        btnMemoriaSecuencialAuditiva.isEnabled = false

        instruccionesMemoriaSecuencialAuditiva()

        inhabilitarBotonesSecuencia()

        secuenciaAuditiva()

        secuenciaGato()

        secuenciaPato()

        secuenciaPerro()

        siguiente()

        menu()

        secuenciaAuditivaRespuestas = mutableListOf()
    }

    private fun instruccionesMemoriaSecuencialAuditiva() {

        val mp = MediaPlayer.create(this, R.raw.memoriasecuencialauditiva)

        if (!mp.isPlaying) {
            btnInstruccionesMemoriaSecuencialAuditiva.setOnClickListener {
                mp.start()
                Thread.sleep(16000)
                btnInstruccionesMemoriaSecuencialAuditiva.isEnabled = false
                btnMemoriaSecuencialAuditiva.isEnabled = true
            }
        }
    }

    private fun ocultarBotonesSecuencia(){

    }

    private fun botonesSecuenciaAuditiva(): ArrayList<Button> {

        return arrayListOf(
            btnAudioPato,
            btnAudioPerro,
            btnAudioGato
        )
    }

    private fun secuenciaAuditiva() {

        val mp = MediaPlayer.create(this, R.raw.memoriasecuencialprueba)

        if (!mp.isPlaying) {

            btnMemoriaSecuencialAuditiva.setOnClickListener {

                mp.start()
                Thread.sleep(7000)
                btnMemoriaSecuencialAuditiva.isEnabled = false
                habilitarSecuenciaBotones()
            }
        }
    }

    private fun listaBotonesSecuencia(): ArrayList<Button> {

        return arrayListOf(
            btnAudioGato,
            btnAudioPato,
            btnAudioPerro
        )
    }

    private fun inhabilitarBotonesSecuencia() {

        listaBotonesSecuencia().forEach {
            it.isVisible = false
            it.isEnabled = false
        }
    }

    private fun habilitarSecuenciaBotones() {

        listaBotonesSecuencia().forEach {
            it.isVisible = true
            it.isEnabled = true
        }
    }

    private fun secuenciaGato() {

        val gato = "gato"

        btnAudioGato.setOnClickListener {

            clicks++

            secuenciaAuditivaRespuestas.add(gato)

            habilitarBotonSiguiente()
        }
    }

    private fun secuenciaPerro() {

        val perro = "perro"

        btnAudioPerro.setOnClickListener {

            clicks++

            secuenciaAuditivaRespuestas.add(perro)

            habilitarBotonSiguiente()
        }
    }

    private fun secuenciaPato() {

        val pato = "pato"

        btnAudioPato.setOnClickListener {

            clicks++

            secuenciaAuditivaRespuestas.add(pato)

            habilitarBotonSiguiente()
        }
    }

    private fun validarRespuestas() {

        var indice = 0

        while (indice < secuenciaAuditivaCorrecta.size) {
            if (secuenciaAuditivaCorrecta[indice] == secuenciaAuditivaRespuestas[indice])
                hits++

            indice++
        }
    }

    private fun habilitarBotonSiguiente() {

        if (clicks == 6) {

            btnSiguienteMTSA.isEnabled = true

            inhabilitarBotonesSecuencia()
        }
    }

    private fun menu() {

        btnSMenuMTSA.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun siguiente() {

        btnSiguienteMTSA.setOnClickListener {

            validarRespuestas()

            misses = PUNTAJE_MAXIMO - hits

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("MemoriaSecuencialAuditiva").set(
                    hashMapOf(
                        "Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses
                    )
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
