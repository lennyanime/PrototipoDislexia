package com.example.myapplication.memoriaDeTrabajo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia__memoria__trabajo.*

class Competencia_Memoria_Trabajo : AppCompatActivity() {

    private val user = Firebase.auth.currentUser

    private val DB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia__memoria__trabajo)


        btn_mvisual.setOnClickListener { memoriaTrabajoVisual() }

        btn_mauditiva.setOnClickListener { memoriaTrabajoAuditiva() }

        btn_msauditiva.setOnClickListener { memoriaSecuencialAuditiva() }

        btn_msvisual.setOnClickListener { memoriaSecuencialVisual() }

        menu()
    }

    private fun mensaje() {
        Toast.makeText(this, "Ya realizaste esta prueba", Toast.LENGTH_SHORT).show()
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

    private fun memoriaTrabajoVisual() {

        obtenerDocumento(
            user?.email.toString(),
            "MemoriaVisual",
            MemoriaVisual()
        )
    }

    private fun memoriaSecuencialAuditiva() {

        obtenerDocumento(
            user?.email.toString(),
            "MemoriaSecuencialAuditiva",
            MemoriaSecuencialAuditiva()
        )
    }

    private fun memoriaSecuencialVisual() {

        obtenerDocumento(
            user?.email.toString(),
            "MemoriaSecuencialVisual",
            MemoriaSecuencialVisual()
        )
    }

    private fun memoriaTrabajoAuditiva() {

        obtenerDocumento(
            user?.email.toString(),
            "MemoriaTrabajoAuditiva",
            MemoriaTrabajoAuditiva()
        )
    }

    private fun menu(){

        btnMenuMemoria.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}