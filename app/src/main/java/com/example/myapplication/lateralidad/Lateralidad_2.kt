package com.example.myapplication.lateralidad

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Login
import com.example.myapplication.R
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseOptions
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuthSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_lateralidad_2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

//lateinit var view: TextView
class Lateralidad_2 : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val email = Firebase.auth.currentUser?.email

    private var clicks: Int = 0
    private var hits: Int = 0
    private var misses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lateralidad_2)

        salir()
        getTextView()

        dragTargetImageViewBoat.setColorFilter(Color.GRAY)
        dragTargetFalseImageViewBoat.setColorFilter(Color.GRAY)

        dragImageViewBoat.setOnLongClickListener(longClickListener)
        dragTargetImageViewBoat.setOnDragListener(dragListener)
        dragTargetFalseImageViewBoat.setOnDragListener(dragListener)

        btnSiguienteLateralidad2.setOnClickListener {

            //Firebase.auth.currentUser?.email?.let{ it ->
                db.collection("LateralidadPrueba2").document("$email").set(
                        hashMapOf("Clicks" to clicks,
                                "Hits" to hits,
                                "Misees" to misses)
                )
            }
        //}
    }

    private fun getTextView() {

        Toast.makeText(applicationContext, "$email", Toast.LENGTH_SHORT).show()
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

                true

            }
            //entrando a la imagen

            DragEvent.ACTION_DRAG_ENTERED -> {
                textView.text = "entrando a la imagen"

                if (receiverView.tag as String == event.clipDescription.label) {

                    receiverView.setColorFilter(Color.TRANSPARENT)
                    dragImageViewBoat.setColorFilter(Color.GRAY)
                    dragImageViewBoat.setEnabled(false)
                    hits ++
                    clicks ++

                } else {

                    receiverView.setColorFilter(Color.TRANSPARENT)
                    dragImageViewBoat.setColorFilter(Color.GRAY)
                    dragImageViewBoat.setEnabled(false)
                    misses ++
                    clicks ++

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

    private fun salir() {

        btnSalirLateralidad2.setOnClickListener {
            onBackPressed()
        }
    }
}
