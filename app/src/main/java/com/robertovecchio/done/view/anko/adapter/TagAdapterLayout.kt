package com.robertovecchio.done.view.anko.adapter

import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.view.ViewGroup
import android.widget.LinearLayout
import com.robertovecchio.done.R
import org.jetbrains.anko.*

class TagAdapterLayout: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
        linearLayout {
            orientation = LinearLayout.HORIZONTAL


            textView("agagagagagagga"){
                id = R.id.TAG_NAME

                ViewCompat.setNestedScrollingEnabled(this, false)
                textColor = android.R.color.white
                textSize = 14F
                backgroundColor = ContextCompat.getColor(ctx,android.R.color.black)
            }.lparams(width = dip(200), height = wrapContent){
                leftMargin = dip(10)
            }

        }
    }
}