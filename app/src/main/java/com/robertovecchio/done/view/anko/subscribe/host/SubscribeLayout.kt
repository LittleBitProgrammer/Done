package com.robertovecchio.done.view.anko.subscribe.host

import android.graphics.Typeface
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.robertovecchio.done.R
import com.robertovecchio.done.view.activity.Subscribe
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class SubscribeLayout: AnkoComponent<Subscribe> {

    lateinit var subscribeButton: Button

    override fun createView(ui: AnkoContext<Subscribe>)= with(ui) {
        constraintLayout {

            imageView {
                id = Ids.LOGO

                Glide.with(ctx).load(R.drawable.group).into(this)
            }.lparams(width = dip(64), height = dip(51) ){
                topToTop = PARENT_ID
                topMargin = dip(129)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
            }

            frameLayout {
                id = Ids.FRAME_CONTAINER

            }.lparams(width = matchConstraint, height = matchConstraint){
                topToBottom = Ids.LOGO
                bottomToTop = Ids.BUTTON
                endToEnd = PARENT_ID
                startToStart = PARENT_ID

                //this i necessary because the alternative is a general padding on the four side
                //don't deactivate the padding
                leftPadding = dip(0)
                rightPadding = dip(0)
                bottomPadding = dip(0)
                topPadding = dip(0)

            }

            subscribeButton = button("Avanti"){
                id = Ids.BUTTON

                background = ContextCompat.getDrawable(ctx,R.drawable.rounded_button)
                textSize = 24F
                isAllCaps = false
                textColor = ContextCompat.getColor(ctx,R.color.white)
                textAlignment = Button.TEXT_ALIGNMENT_CENTER

                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")


            }.lparams(width = dip(202), height = dip(50)){
                bottomToBottom = PARENT_ID
                endToEnd = PARENT_ID
                startToStart = PARENT_ID

                bottomMargin = dip(34)
            }
        }
    }

    private object Ids{
        const val LOGO = R.id.IMAGE_VIEW_LOGO
        const val FRAME_CONTAINER = R.id.FRAME_CONTAINER
        const val BUTTON = R.id.BUTTON_SUBSCRIBE
    }
}