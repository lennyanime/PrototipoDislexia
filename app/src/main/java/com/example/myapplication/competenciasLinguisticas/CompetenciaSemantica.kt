package com.example.myapplication.competenciasLinguisticas

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_competencia_semantica.*
import kotlinx.android.synthetic.main.activity_discriminicion_visual.*
import kotlinx.android.synthetic.main.activity_lateralidad_1.*
import kotlinx.android.synthetic.main.activity_lateralidad_2.*
import kotlinx.android.synthetic.main.activity_lateralidad_2.textView

class CompetenciaSemantica : AppCompatActivity() {

    private val DB = FirebaseFirestore.getInstance()
    private var clicks: Int = 0
    private var clicksPrueba1: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_semantica)

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
                textView.text = "arrastrando imagen"
                clicks++
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                textView.text = "entrando a la imagen"

                if (receiverView.tag as String == event.clipDescription.label) {

                    receiverView.setColorFilter(Color.TRANSPARENT)
                    imgCircle.setColorFilter(Color.GRAY)
                    imgCircle.setEnabled(false)
                    hits++
                    deshabilitarImagenesPrueba2()
                    habilitarBotonSiguiente()

                } else {

                    receiverView.setColorFilter(Color.TRANSPARENT)
                    imgCircle.setColorFilter(Color.GRAY)
                    imgCircle.setEnabled(false)
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
                textView.text = "saliendo de la imagen"
                true
            }
            //soltando la imager
            DragEvent.ACTION_DROP -> {
                textView.text = "soltando la imagen"
                true
            }
            //dejando de arrastrar la imagen
            DragEvent.ACTION_DRAG_ENDED -> {
                textView.text = "dejando de arrastrar la imagen"
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
            //super.onDrawShadow(canvas)
            v.draw(canvas)
        }
    }

    private fun instruccionesCompetenciaSemantica() {

        val mp = MediaPlayer.create(this, R.raw.lenny2)

        btnInstruccionesCompetenciaSemantica.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesCompetenciaSemantica.setEnabled(false)
                Thread.sleep(2000)
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
            it.setEnabled(false)
        }
    }

    private fun habilitarImagenesPrueba1() {

        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgTriangulo, imgPoligono)

        IMAGENES_COMPETENCIA_SEMANTICA.forEach { it.setEnabled(true) }
    }

    private fun deshabilitarImagenesPrueba1() {

        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgTriangulo, imgPoligono)

        IMAGENES_COMPETENCIA_SEMANTICA.forEach { it.setEnabled(false) }
    }

    private fun deshabilitarImagenesPrueba2() {

        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgCircle, imgCirculoObjetivo, imgCirculoError)

        IMAGENES_COMPETENCIA_SEMANTICA.forEach { it.setEnabled(false) }
    }

    private fun habilitarImagenesPrueba2() {

        val IMAGENES_COMPETENCIA_SEMANTICA = arrayListOf<ImageView>(imgCircle, imgCirculoObjetivo, imgCirculoError)

        IMAGENES_COMPETENCIA_SEMANTICA.forEach { it.setEnabled(true) }
    }

    private fun habilitarBotonSiguiente(){ btnSiguienteCompetenciaSemantica.setEnabled(true) }

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
        }
    }
}