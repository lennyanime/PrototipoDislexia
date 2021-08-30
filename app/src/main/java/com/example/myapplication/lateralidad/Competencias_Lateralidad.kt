package com.example.myapplication.lateralidad

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.components.Component
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencias__lateralidad.*

class Competencias_Lateralidad : AppCompatActivity() {

    private val user = Firebase.auth.currentUser

    private val DB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencias__lateralidad)


        btnpruebaL1.setOnClickListener{
            pruebaUnoLateralidad()
        }

        btnpruebaL2.setOnClickListener{
            pruebaDosLateralidad()
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

    private fun pruebaUnoLateralidad(){

        obtenerDocumento(
            user?.email.toString(),
            "LateralidadPrueba1",
            Lateralidad_1()
        )
    }

    private fun pruebaDosLateralidad(){

        obtenerDocumento(
            user?.email.toString(),
            "LateralidadPrueba2",
            Lateralidad_2()
        )
    }

    private fun menu(){

        btnMenuLateralidad.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}