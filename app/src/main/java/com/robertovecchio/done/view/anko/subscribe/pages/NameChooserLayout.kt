package com.robertovecchio.done.view.anko.subscribe.pages

import android.graphics.PorterDuff.Mode.*
import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.robertovecchio.done.R
import com.robertovecchio.done.view.fragment.subscribe.NameChooser
import org.jetbrains.anko.*


class NameChooserLayout: AnkoComponent<NameChooser> {

    lateinit var editName: EditText

    override fun createView(ui: AnkoContext<NameChooser>) = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL

            textView {
                text = "Come ti chiami ?"
                textSize = 24F
                textColor = ContextCompat.getColor(ctx,R.color.colorPrimaryDark)
                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }.lparams(width = matchParent, height = wrapContent){
                topMargin = dip(59)
            }

            editName = editText {
                inputType = InputType.TYPE_CLASS_TEXT
                maxLines = 1

                val maxLength = 15
                filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
                this.keyListener = DigitsKeyListener.getInstance("qwertzuiopasdfghjklyxcvbnm ")
                background.mutate().setColorFilter(ContextCompat.getColor(ctx,R.color.colorAccent), SRC_ATOP)


            }.lparams(width = dip(203), height = wrapContent){
                gravity = Gravity.CENTER_HORIZONTAL

            }
        }
    }
}