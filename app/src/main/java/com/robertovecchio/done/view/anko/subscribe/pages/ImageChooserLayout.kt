package com.robertovecchio.done.view.anko.subscribe.pages

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.robertovecchio.done.R
import com.robertovecchio.done.view.fragment.subscribe.ImageChooser
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.onClick

inline fun ViewManager.roundImage(theme: Int = 0, init: CircleImageView.() -> Unit) = ankoView({ CircleImageView(it) }, theme, init)

class ImageChooserLayout(private val activity: Activity):AnkoComponent<ImageChooser> {

    lateinit var profileImage: CircleImageView

    override fun createView(ui: AnkoContext<ImageChooser>) = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL

            profileImage = roundImage {
                Glide.with(ctx).load(R.drawable.default_profile_image).into(this)

                onClick {
                    val intent = Intent()
                    intent.type = "image/*"
                    intent.action = Intent.ACTION_OPEN_DOCUMENT
                    activity.startActivityForResult(intent, 1)
                }
            }.lparams(width= dip(210), height = dip(210)){
                topMargin = dip(43)
                gravity = Gravity.CENTER_HORIZONTAL
            }

            textView {
                text = "carica una tua foto"
                textColor = ContextCompat.getColor(ctx,R.color.colorPrimaryDark)
                textSize = 16F
                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER

            }.lparams(width = matchParent, height = wrapContent){
                topMargin = dip(20)
            }
        }
    }
}