package com.robertovecchio.done.view.anko

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.robertovecchio.done.R
import com.robertovecchio.done.view.activity.SplashScreen
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class SplashLayout: AnkoComponent<SplashScreen> {

    lateinit var image: ImageView
    lateinit var powered: TextView
    lateinit var name: TextView

    override fun createView(ui: AnkoContext<SplashScreen>) = with(ui) {

        constraintLayout {

            val colors = intArrayOf(Color.parseColor("#02C0FF"), Color.parseColor("#009BFF"))
            val gd = GradientDrawable(GradientDrawable.Orientation.TR_BL, colors)
            gd.cornerRadius = 0f

            background = gd

            image = imageView {
                id = Ids.IMAGE

                Glide.with(ctx).load(R.drawable.personal_logo).into(this)
            }.lparams(width = dip(262), height = dip(262)){
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID

                topMargin = dip(60)
            }

            powered = textView("Powered by") {
                id = Ids.POWERED

                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                textSize = 14F
                textColor = Color.parseColor("#FFFFFF")
            }.lparams(width = wrapContent, height = wrapContent){
                bottomToTop = Ids.NAME
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                bottomMargin = dip(2)
            }

            name = textView("Roberto Vecchio") {
                id = Ids.NAME

                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                textSize = 16F
                textColor = Color.parseColor("#FFFFFF")
            }.lparams(width = wrapContent, height = wrapContent){
                bottomToBottom = PARENT_ID
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                bottomMargin = dip(10)
            }
        }
    }

    private object Ids{
        const val IMAGE = R.id.IMAGE_SPLASH_SCREEN
        const val POWERED = R.id.TEXT_POWERED_BY
        const val NAME = R.id.TEXT_NAME_SPLASH
    }
}