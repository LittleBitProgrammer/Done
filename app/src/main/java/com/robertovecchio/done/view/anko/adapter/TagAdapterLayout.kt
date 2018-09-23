package com.robertovecchio.done.view.anko.adapter

import android.graphics.Color
import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.view.ViewGroup
import android.view.ViewManager
import com.robertovecchio.done.R
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.roundImage(theme: Int = 0, init: CircleImageView.() -> Unit) = ankoView({ CircleImageView(it) }, theme, init)

class TagAdapterLayout: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
        constraintLayout {
            topPadding = dip(6)
            lparams(matchParent, wrapContent)

            checkBox ("Family"){
                id = R.id.CHECK_BOX
                textColor = Color.parseColor("#BDBCBF")
                textSize = 18F
            }.lparams(width = wrapContent, height = wrapContent){
                topToTop = PARENT_ID
            }
            roundImage {
                id = R.id.TAG_COLOR

            }.lparams(dip(16),dip(16)){
                rightToRight = PARENT_ID
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID

                rightMargin = dip(16)
            }

        }
    }
}