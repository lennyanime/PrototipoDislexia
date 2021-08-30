package com.example.myapplication.competenciasLinguisticas

import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Componentes
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_semantica.*

class CompetenciaSemantica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()

    private val user = Firebase.auth.currentUser

    private var clicks: Int = 0
    private var clicksPrueba1: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_semantica)

        btnSiguienteCompetenciaSemantica.isEnabled = false

        instruccionesCompetenciaSemantica()
        imagenesDeshabilitadas()
        clickTriangulo()
        clickPoligono()
        siguiente()

        imgCirculoObjetivo.setColorFilter(Color.GRAY)
        imgCirculoError.setColorFilter(Color.GRAY)

        imgCircle.setOnLongClickListener(longClickListener)
        imgCirculoObjetivo.setOnDragListener(dragListener)
        imgCirculoError.setOnDragListener(dragListener)

        menu()
    }

    private val longClickListener = View.OnLongClickListener { v ->
        val item = ClipData.Item(v.tag as? CharSequence)

        val dragData = ClipData(
                v.tag as CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item
        )

        val myShadow = DragShadowBuilder(v)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(dragData, myShadow, null, 0)
        } else {
            v.startDrag(dragData, myShadow, null, 0)
        }
    }

    private val dragListener = View.OnDragListener { v, event ->
        val receiverView: ImageView = v as ImageView

        when (event.action) {

            //arrastrando la imagen
            DragEvent.ACTION_DRAG_STARTED -> {
                //textView.text = "arrastrando imagen"
                clicks++
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                //textView.text = "entrando a la imagen"

                if (receiverView.tag as String == event.clipDescription.label) {

                    receiverView.setColorFilter(Color.TRANSPARENT)
                    imgCircle.setColorFilter(Color.GRAY)
                    imgCircle.isEnabled = false
                    hits++
                    deshabilitarImagenesPrueba2()
                    habilitarBotonSiguiente()

                } else {

                    receiverView.setColorFilter(Color.TRANSPARENT)
                    imgCircle.setColorFilter(Color.GRAY)
                    imgCircle.isEnabled = false
                    misses++
                    deshabilitarImagenesPrueba2()
                    habilitarBotonSiguiente()
                }
                true
            }
            //
            DragEvent.ACTION_DRAG_LOCATION -> {
                true
            }
            //saliendo de la imagen
            DragEvent.ACTION_DRAG_EXITED -> {
                //textView.text = "saliendo de la imagen"
                true
            }
            //soltando la imager
            DragEvent.ACTION_DROP -> {
                //textView.text = "soltando la imagen"
                true
            }
            //dejando de arrastrar la imagen
            DragEvent.ACTION_DRAG_ENDED -> {
                //textView.text = "dejando de arrastrar la imagen"
                true
            }
            else -> false
        }
    }

    private class DragShadowBuilder(val v: View) : View.DragShadowBuilder(v) {

        override fun onProvideShadowMetrics(outShadowSize: Point, outShadowTouchPoint: Point) {
            //super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint)
            outShadowSize.set(view.width, view.height)
            outShadowTouchPoint.set(view.width / 2, view.height / 2)
        }

        override fun onDrawShadow(canvas: Canvas?) {
            v.draw(canvas)
        }
    }

    private fun instruccionesCompetenciaSemantica() {

        val mp = MediaPlayer.create(this, R.raw.competenciasemantica)

        btnInstruccionesCompetenciaSemantica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesCompetenciaSemantica.isEnabled = false
                Thread.sleep(19000)
                habilitarImagenesPrueba1()
            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun clickTriangulo() {

        imgTriangulo.setOnClickListener {
            clicksPrueba1++
            hits++
            imgPoligono.setColorFilter(Color.GRAY)
            deshabilitarImagenesPrueba1()
            habilitarImagenesPrueba2()
        }
    }

    private fun clickPoligono() {

        imgPoligono.setOnClickListener {
            clicksPrueba1++
            misses++
            imgTriangulo.setColorFilter(Color.GRAY)
            deshabilitarImagenesPrueba1()
            habilitarImagenesPrueba2()
        }
    }

    private fun imagenesDeshabilitadas() {

        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgCircle, imgCirculoObjetivo, imgCirculoError, imgTriangulo, imgPoligono)

        IMAGENES_COMPETENCIA_SEMANTICA.forEach {
            it.isEnabled = false
        }
    }

    private fun habilitarImagenesPrueba1() {

        txtInstruccion1CompetenciaSemantica.visibility = View.VISIBLE
        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgTriangulo, imgPoligono)

        IMAGENES_COMPETENCIA_SEMANTICA.forEach {
            it.isEnabled = true
            it.visibility = View.VISIBLE
        }
    }

    private fun deshabilitarImagenesPrueba1() {

        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgTriangulo, imgPoligono)
        IMAGENES_COMPETENCIA_SEMANTICA.forEach {
            it.isEnabled = false
        }
    }

    private fun habilitarImagenesPrueba2() {
        txtInstruccion2CompetenciaSemantica.visibility = View.VISIBLE
        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgCircle, imgCirculoObjetivo, imgCirculoError)

        IMAGENES_COMPETENCIA_SEMANTICA.forEach {
            it.isEnabled = true
            it.visibility = View.VISIBLE
        }
    }

    private fun deshabilitarImagenesPrueba2() {

        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgCircle, imgCirculoObjetivo, imgCirculoError)

        IMAGENES_COMPETENCIA_SEMANTICA.forEach { it.isEnabled = false }
    }

    private fun habilitarBotonSiguiente(){

        btnSiguienteCompetenciaSemantica.isEnabled = true
    }

    private fun menu(){

        btnMenuCS.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun siguiente() {

        btnSiguienteCompetenciaSemantica.setOnClickListener {

            clicks = (clicks / 2) + clicksPrueba1

            Firebase.auth.currentUser?.email?.let { email ->
                DB.collection(email).document("CompetenciaSemántica").set(
                        hashMapOf("Clicks" to clicks,
                                "Hits" to hits,
                                "Misses" to misses)
                )
            }

            obtenerDocumento(
                user?.email.toString(),
                "CompetenciaOrtográfica",
                CompetenciaOrtografica()
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