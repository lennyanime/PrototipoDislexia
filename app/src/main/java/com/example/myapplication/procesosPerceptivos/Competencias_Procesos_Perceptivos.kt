package com.example.myapplication.procesosPerceptivos

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
import kotlinx.android.synthetic.main.activity_competencias__procesos__perceptivos.*

class Competencias_Procesos_Perceptivos : AppCompatActivity() {

    private val user = Firebase.auth.currentUser

    private val DB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__procesos__perceptivos)

        btnPrueba1DCV.setOnClickListener {
            discrimizacionCategorizacionVisual()
        }

        btnPrueba2DCA.setOnClickListener {
            discrimizacionCategorizacionAuditiva()
        }

        menu()
    }

    private fun mensaje(){
        Toast.makeText(this,"Ya realizaste esta prueba", Toast.LENGTH_SHORT).show()
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

    private fun discrimizacionCategorizacionAuditiva() {

        obtenerDocumento(
            user?.email.toString(),
            "DiscriminaciónCategorizacionAuditiva",
            DiscriminacionCategorizacionAuditiva()
        )
    }

    private fun discrimizacionCategorizacionVisual() {

        obtenerDocumento(
            user?.email.toString(),
            "DiscriminaciónCategorizacionVisual",
            DiscriminacionCategorizacionVisual1()
        )
    }

    private fun menu(){

        btnMenuProcesosPerceptivos.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}