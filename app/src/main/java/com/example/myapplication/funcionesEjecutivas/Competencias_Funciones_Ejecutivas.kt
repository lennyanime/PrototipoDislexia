package com.example.myapplication.funcionesEjecutivas

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
import kotlinx.android.synthetic.main.activity_competencias__funciones__ejecutivas.*

class Competencias_Funciones_Ejecutivas : AppCompatActivity() {

    private val user = Firebase.auth.currentUser

    private val DB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__funciones__ejecutivas)

        btnAtencionDividida.setOnClickListener {
            atencionDividida()
        }

        btnAtencionSelectiva.setOnClickListener {
            atencionSelectiva()
        }

        btnAtencionSostenida.setOnClickListener {
            atencionSostenida()
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

    private fun atencionDividida() {

        obtenerDocumento(
            user?.email.toString(),
            "AtenciónDividida",
            AtencionDividida()
        )
    }

    private fun atencionSelectiva() {

        obtenerDocumento(
            user?.email.toString(),
            "AtenciónSelectiva",
            AtencionSelectiva()
        )
    }

    private fun atencionSostenida() {

        obtenerDocumento(
            user?.email.toString(),
            "AtenciónSostenida",
            AtencionSostenida1()
        )
    }

    private fun menu(){

        btnMenuFuncionesEjecutivas.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}