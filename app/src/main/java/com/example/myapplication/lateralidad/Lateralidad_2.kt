package com.example.myapplication.lateralidad

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
import com.example.myapplication.competenciasLinguisticas.CompetenciaFonica
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_lateralidad_2.*

class Lateralidad_2 : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val email = Firebase.auth.currentUser?.email

    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lateralidad_2)

        btnSiguienteLateralidad2.isEnabled = false

        instruccionesPruebaLateralidad2()

        salir()

        siguiente()

        dragImageViewBoat.isEnabled = false


        dragTargetImageViewBoat.setColorFilter(Color.GRAY)
        dragTargetFalseImageViewBoat.setColorFilter(Color.GRAY)

        dragImageViewBoat.setOnLongClickListener(longClickListener)
        dragTargetImageViewBoat.setOnDragListener(dragListener)
        dragTargetFalseImageViewBoat.setOnDragListener(dragListener)
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
                true
            }
            //entrando a la imagen

            DragEvent.ACTION_DRAG_ENTERED -> {
                //textView.text = "entrando a la imagen"

                if (receiverView.tag as String == event.clipDescription.label) {

                    receiverView.setColorFilter(Color.TRANSPARENT)
                    dragImageViewBoat.setColorFilter(Color.GRAY)
                    dragImageViewBoat.isEnabled = false
                    hits++

                    habilitarBotonSiguiente()
                } else {

                    receiverView.setColorFilter(Color.TRANSPARENT)
                    dragImageViewBoat.setColorFilter(Color.GRAY)
                    dragImageViewBoat.isEnabled = false
                    misses ++

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
                clicks ++
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

    private fun salir() {

        btnSalirLateralidad2.setOnClickListener {
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    private fun instruccionesPruebaLateralidad2() {

        val mp = MediaPlayer.create(this, R.raw.lateralidad2)

        btnInstruccionesLateralidad2.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
                btnInstruccionesLateralidad2.isEnabled = false
                Thread.sleep(17000)
                dragImageViewBoat.isEnabled = true

            }
            /*else {
                mp.pause()
            }*/
        }
    }

    private fun habilitarBotonSiguiente(){

        if(hits > 0 || misses > 0)
            btnSiguienteLateralidad2.isEnabled = true
    }

    private fun siguiente(){

        btnSiguienteLateralidad2.setOnClickListener {
            //el dropdown duplica el valor de los clicks y se debe dividir entre 2
            clicks = clicks / 2
            //Firebase.auth.currentUser?.email?.let{ it ->
            db.collection("$email").document("LateralidadPrueba2").set(
                    hashMapOf("Clicks" to clicks,
                            "Hits" to hits,
                            "Misees" to misses)
            )
            val intent = Intent(this, Componentes()::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Funcionalidad desactivada", Toast.LENGTH_SHORT).show()
    }
}
