package com.example.myapplication.competenciasLinguisticas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencias__linguisticas.*

class Competencias_Linguisticas : AppCompatActivity() {

    private val user = Firebase.auth.currentUser

    private val DB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__linguisticas)

        btnc_alfabetica.setOnClickListener {
            competenciaAlfabetica()
        }

        btnc_fonica.setOnClickListener {
            competenciaFonologica()
        }

        btnc_silabica.setOnClickListener {
            competenciaSilabica()
        }

        btnc_lexica.setOnClickListener {
            competenciaLexica()
        }

        btnc_sintactica.setOnClickListener {
            competenciaSintactica()
        }

        btnc_semantica.setOnClickListener {
            competenciaSemantica()
        }

        btnc_ortografica.setOnClickListener {
            competenciaOrtografica()
        }

        menu()
    }

    private fun mensaje() {
        Toast.makeText(
            this,
            "Ya realizaste esta prueba",
            Toast.LENGTH_SHORT
        ).show()
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
                    mensaje()
                } else {
                    val intent = Intent(this, activity::class.java)
                    startActivity(intent)
                }
            }
    }

    private fun competenciaAlfabetica() {

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaAlfabética",
            CompetenciaAlfabetica(),
        )
    }

    private fun competenciaFonologica() {

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaFónica",
            CompetenciaFonica()
        )
    }

    private fun competenciaSilabica() {

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaSilábica",
            CompetenciaSilabica()
        )
    }

    private fun competenciaLexica() {
        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaLéxica",
            CompetenciaLexica()
        )
    }

    private fun competenciaSintactica() {
        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaSintáctica",
            CompetenciaSintactica()
        )
    }

    private fun competenciaSemantica() {
        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaSemántica",
            CompetenciaSemantica()
        )
    }

    private fun competenciaOrtografica() {
        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaOrtográfica",
            CompetenciaOrtografica()
        )
    }

    private fun menu(){

        btnSalirCompetenciasLinguisticas.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}

