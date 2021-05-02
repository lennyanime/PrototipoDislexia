package com.example.myapplication.competenciasLinguisticas

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_competencia_semantica.*
import kotlinx.android.synthetic.main.activity_lateralidad_2.*
import kotlinx.android.synthetic.main.activity_lateralidad_2.textView

class CompetenciaSemantica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia_semantica)

        imgCircle.setOnLongClickListener(longClickListener)
        imgTrueCuadrado.setOnDragListener(dragListener)
        imgFalseCuadrado.setOnDragListener(dragListener)
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

                    receiverView.setColorFilter(Color.BLUE)
                    /*dragImageViewBoat.setColorFilter(Color.GRAY)
                    dragImageViewBoat.setEnabled(false)*/

                } else {

                    //receiverView.setColorFilter(Color.TRANSPARENT)
                    /*dragImageViewBoat.setColorFilter(Color.GRAY)
                    dragImageViewBoat.setEnabled(false)*/
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
}