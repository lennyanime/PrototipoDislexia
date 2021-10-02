package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.competenciasLinguisticas.Competencias_Linguisticas
import com.example.myapplication.funcionesEjecutivas.AtencionDividida
import com.example.myapplication.funcionesEjecutivas.AtencionSelectiva
import com.example.myapplication.funcionesEjecutivas.AtencionSostenida1
import com.example.myapplication.funcionesEjecutivas.Competencias_Funciones_Ejecutivas
import com.example.myapplication.lateralidad.Competencias_Lateralidad
import com.example.myapplication.memoriaDeTrabajo.*
import com.example.myapplication.procesosPerceptivos.Competencias_Procesos_Perceptivos
import com.example.myapplication.procesosPerceptivos.DiscriminacionCategorizacionAuditiva
import com.example.myapplication.procesosPerceptivos.DiscriminacionCategorizacionVisual1
import com.example.myapplication.rendimientoDesempeño.Competencias_Rendimiento_Desempeno
import com.example.myapplication.rendimientoDesempeño.OrtografiaArbitraria_VelocidadEscritura
import com.example.myapplication.rendimientoDesempeño.Reconocimiento_CorreccionErrores
import com.example.myapplication.rendimientoDesempeño.VelocidadDeLectura
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_componentes.*

class Componentes : AppCompatActivity() {

    private var contadorPruebas: Int = 0

    private val user = Firebase.auth.currentUser

    private val DB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_componentes)

        btncl.setOnClickListener {
            CompetenciasLinguisticas()
        }

        btnmt.setOnClickListener {
            MemoriaDeTrabajo()
        }

        btnfe.setOnClickListener {
            FuncionesEjecutivas()
        }

        btnrd.setOnClickListener {
            RendimientoDesempeno()
        }

        btnpp.setOnClickListener {
            ProcesosPerceptivos()
        }

        btnl.setOnClickListener {
            Lateralidad()
        }
    }

    private fun CompetenciasLinguisticas() {

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaAlfabética",
            Competencias_Linguisticas(),
            7
        )

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaFónica",
            Competencias_Linguisticas(),
            7
        )

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaSilábica",
            Competencias_Linguisticas(),
            7
        )

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaLéxica",
            Competencias_Linguisticas(),
            7
        )

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaSintáctica",
            Competencias_Linguisticas(),
            7
        )

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaSemántica",
            Competencias_Linguisticas(),
            7
        )

        obtenerDocumento(
            user?.email.toString(),
            "CompetenciaOrtográfica",
            Competencias_Linguisticas(),
            7
        )
    }

    private fun MemoriaDeTrabajo() {

        obtenerDocumento(
            user?.email.toString(),
            "MemoriaVisual",
            Competencia_Memoria_Trabajo(),
            4
        )

        obtenerDocumento(
            user?.email.toString(),
            "MemoriaSecuencialAuditiva",
            Competencia_Memoria_Trabajo(),
            4
        )

        obtenerDocumento(
            user?.email.toString(),
            "MemoriaSecuencialVisual",
            Competencia_Memoria_Trabajo(),
            4
        )

        obtenerDocumento(
            user?.email.toString(),
            "MemoriaTrabajoAuditiva",
            Competencia_Memoria_Trabajo(),
            4
        )
    }

    private fun FuncionesEjecutivas() {

        obtenerDocumento(
            user?.email.toString(),
            "AtenciónDividida",
            Competencias_Funciones_Ejecutivas(),
            3
        )

        obtenerDocumento(
            user?.email.toString(),
            "AtenciónSelectiva",
            Competencias_Funciones_Ejecutivas(),
            3
        )

        obtenerDocumento(
            user?.email.toString(),
            "AtenciónSostenida",
            Competencias_Funciones_Ejecutivas(),
            3
        )
    }

    private fun RendimientoDesempeno() {

        obtenerDocumento(
            user?.email.toString(),
            "VelocidadLectura",
            Competencias_Rendimiento_Desempeno(),
            3
        )

        obtenerDocumento(
            user?.email.toString(),
            "OrtografíaArbitraria_VelodidadEscritura",
            Competencias_Rendimiento_Desempeno(),
            3
        )

        obtenerDocumento(
            user?.email.toString(),
            "ReconocimientoCorrecciónErrores",
            Competencias_Rendimiento_Desempeno(),
            3
        )
    }

    private fun ProcesosPerceptivos() {

        obtenerDocumento(
            user?.email.toString(),
            "DiscriminaciónCategorizacionAuditiva",
            Competencias_Procesos_Perceptivos(),
            2
        )

        obtenerDocumento(
            user?.email.toString(),
            "DiscriminaciónCategorizacionVisual",
            Competencias_Procesos_Perceptivos(),
            2
        )
    }

    private fun Lateralidad() {

        obtenerDocumento(
            user?.email.toString(),
            "LateralidadPrueba1",
            Competencias_Lateralidad(),
            2
        )

        obtenerDocumento(
            user?.email.toString(),
            "LateralidadPrueba2",
            Competencias_Lateralidad(),
            2
        )
    }

    private fun obtenerDocumento(
        correo: String,
        documento: String,
        activity: Activity,
        contador:Int
    ) {

        val document = DB.collection(correo).document(documento)
        document.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    contadorPruebas++
                }
                if (contadorPruebas == contador) {
                    mensaje()
                } else {
                    val intent = Intent(this, activity::class.java)
                    startActivity(intent)
                }
            }
    }

    private fun mensaje() {
        Toast.makeText(this, "Ya realizaste todas las pruebas de este nivel", Toast.LENGTH_SHORT)
            .show()
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}