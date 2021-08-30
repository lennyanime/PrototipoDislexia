package com.example.myapplication.rendimientoDesempeño

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
import kotlinx.android.synthetic.main.activity_competencias__rendimiento__desempeno.*

class Competencias_Rendimiento_Desempeno : AppCompatActivity() {

    private val user = Firebase.auth.currentUser

    private val DB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__rendimiento__desempeno)

        btnVelocidadLectura.setOnClickListener {

            velocidadLectura()
        }

        btnOrtografiaArbitraria.setOnClickListener {

            ortografiaArbitraria()
        }

        btnReconocimientoErrores.setOnClickListener {

            reconocimientoErrores()
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

    private fun velocidadLectura() {

        obtenerDocumento(
            user?.email.toString(),
            "VelocidadLectura",
            VelocidadDeLectura()
        )
    }

    /*private fun comprensionLectura() {

        obtenerDocumento(
            user?.email.toString(),
            "ComprensiónLectura",
            btnComprensionLectura,
            ComprensionLectura()
        )
    }*/

    private fun ortografiaArbitraria() {

        obtenerDocumento(
            user?.email.toString(),
            "OrtografíaArbitraria_VelodidadEscritura",
            OrtografiaArbitraria_VelocidadEscritura()
        )
    }

    private fun reconocimientoErrores() {

        obtenerDocumento(
            user?.email.toString(),
            "ReconocimientoCorrecciónErrores",
            Reconocimiento_CorreccionErrores()
        )
    }

    private fun menu(){

        btnMenuRDesempeno.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}