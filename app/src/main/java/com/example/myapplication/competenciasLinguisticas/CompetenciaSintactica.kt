package com.example.myapplication.competenciasLinguisticas

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_sintactica.*


class CompetenciaSintactica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()

    private val user = Firebase.auth.currentUser

    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_sintactica)

        instruccionesCompetenciaSintactica()

        imagenesDeshabilitadas()

        btnSiguienteCompetenciaSintactica.setEnabled(false)

        prueba()

        siguiente()

        menu()
    }

    private fun menu() {

        btnMenuCSintactica.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun instruccionesCompetenciaSintactica() {

        val mp = MediaPlayer.create(this, R.raw.competenciasintactica)

        btnInstruccionesCompetenciaSintactica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                Thread.sleep(19000)
                btnInstruccionesCompetenciaSintactica.setEnabled(false)
                imagenesHabilitadas()
                txtInstruccionCompetenciaSemantica.setVisibility(View.VISIBLE)
            }
        }
    }

    private fun prueba() {

        img1CompetenciaSintactica.setOnClickListener{
            clicks++
            hits++
            imagenesDeshabilitadas()
            habilitarBotonSiguiente()
        }

        img2CompetenciaSintactica.setOnClickListener{
            clicks++
            misses++
            imagenesDeshabilitadas()
            habilitarBotonSiguiente()
        }

        img3CompetenciaSintactica.setOnClickListener{
            clicks++
            misses++
            imagenesDeshabilitadas()
            habilitarBotonSiguiente()
        }

        img4CompetenciaSintactica.setOnClickListener{
            clicks++
            misses++
            imagenesDeshabilitadas()
            habilitarBotonSiguiente()
        }
    }

    private fun imagenesDeshabilitadas() {
        val IMAGENES_COMPETENCIA_SINTACTICA = arrayListOf<ImageView>(img1CompetenciaSintactica, img2CompetenciaSintactica, img3CompetenciaSintactica, img4CompetenciaSintactica)

        IMAGENES_COMPETENCIA_SINTACTICA.forEach {
            it.setEnabled(false)
        }
    }

    private fun imagenesHabilitadas() {
        val IMAGENES_COMPETENCIA_SINTACTICA = arrayListOf<ImageView>(img1CompetenciaSintactica, img2CompetenciaSintactica, img3CompetenciaSintactica, img4CompetenciaSintactica)

        IMAGENES_COMPETENCIA_SINTACTICA.forEach {
            it.setEnabled(true)
            it.setVisibility(View.VISIBLE)
        }
    }

    private fun habilitarBotonSiguiente() {

        btnSiguienteCompetenciaSintactica.setEnabled(true)
    }

    private fun siguiente(){

        btnSiguienteCompetenciaSintactica.setOnClickListener {

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaSintáctica").set(
                    hashMapOf("Clicks" to clicks,
                        "Hits" to hits,
                        "Misses" to misses)
                )
            }
            obtenerDocumento(
                user?.email.toString(),
                "CompetenciaSemántica",
                CompetenciaSemantica()
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