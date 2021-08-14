package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_datos_personales.*
import java.util.*

private val listaEdadNiño = arrayListOf("Seleccione", "7", "8", "9", "10", "11")

private val listaGradoEscolar =
    arrayListOf("Seleccione", "1ro", "2do", "3ro", "4to", "5to", "6to")

private val listaSexoNiño = arrayListOf("Seleccione", "F", "M")


class DatosPersonales : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()

    private lateinit var nombreAcudiente: String

    private lateinit var edadNiño: String

    private lateinit var gradoEscolarNiño: String

    private lateinit var sexoNiño: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales)

        listaEdadNiño()

        listaGradoEscolarNiño()

        listaSexoNiño()

        guardarDatosNiño()

        nombreAcudiente = toString()
    }

    private fun listaEdadNiño(): String {

        edadNiño = toString()

        val listAdapter: ArrayAdapter<*>

        listAdapter =
            ArrayAdapter(this, android.R.layout.simple_selectable_list_item, listaEdadNiño)

        listaEdades_spinner.adapter = listAdapter

        listaEdades_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                edadNiño = listaEdades_spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        return edadNiño
    }

    private fun listaGradoEscolarNiño(): String {

        gradoEscolarNiño = toString()

        val listAdapter: ArrayAdapter<*>

        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaGradoEscolar)

        listaGradoEscolar_spinner.adapter = listAdapter

        listaGradoEscolar_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                gradoEscolarNiño = listaGradoEscolar_spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        return gradoEscolarNiño
    }

    private fun listaSexoNiño(): String {

        sexoNiño = toString()

        val listAdapter: ArrayAdapter<*>

        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaSexoNiño)

        listaSexo_spinner.adapter = listAdapter

        listaSexo_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sexoNiño = listaSexo_spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        return sexoNiño
    }

    private fun validarNombreAcudiente(): Boolean {

        //nombreAcudiente = toString()

        val regex = "^[a-zA-Z ]+$".toRegex()

        return if (txtNombreAcudiente.isEmpty()) {
            txtNombreAcudiente.error = "Este campo no puede estar vacío"
            false
        } else if (!txtNombreAcudiente.editText?.text.toString().trim().toLowerCase(Locale.ROOT)
                .contains(regex)
        ) {
            txtNombreAcudiente.error = "Por favor escribe un nombre válido"
            false
        } else {
            txtNombreAcudiente.error = null
            nombreAcudiente = txtNombreAcudiente.editText?.text.toString()
            true
        }
    }

    private fun validarFormulario() {

        if (edadNiño == "Seleccione" || gradoEscolarNiño == "Seleccione" || sexoNiño == "Seleccione" || !validarNombreAcudiente()) {

            Toast.makeText(
                applicationContext,
                "Formulario inválido",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            val intent = Intent(this, Componentes::class.java)
            startActivity(intent)
        }
    }

    private fun guardarDatosNiño() {

        btnIrPruebasDislexia.setOnClickListener {

            validarNombreAcudiente()

            validarFormulario()
            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("DatosNiño").set(
                    hashMapOf(
                        "NombreAcudiente" to nombreAcudiente,
                        "EdadNiño" to edadNiño,
                        "Sexo" to sexoNiño,
                        "GradoEscolar" to gradoEscolarNiño
                    )
                )
            }
        }
    }
}
