package com.robertovecchio.done.view.anko.main

import android.graphics.Color
import android.graphics.Typeface
import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.v7.widget.RecyclerView
import android.view.ViewManager
import android.widget.TextView
import androidx.core.view.size
import com.bumptech.glide.Glide
import com.robertovecchio.done.R
import com.robertovecchio.done.view.fragment.main.HomeFragment
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.recyclerview.v7.recyclerView

inline fun ViewManager.roundImage(theme: Int = 0, init: CircleImageView.() -> Unit) = ankoView({ CircleImageView(it) }, theme, init)

class HomeLayout: AnkoComponent<HomeFragment> {

    lateinit var image: CircleImageView
    lateinit var text: TextView
    lateinit var recycler: RecyclerView

    override fun createView(ui: AnkoContext<HomeFragment>) = with(ui) {
        constraintLayout {

            roundImage {

                Glide.with(ctx).load(R.drawable.simple_circle).into(this)
            }.lparams(width= dip(79), height = dip(79)){
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID

                topMargin = dip(26)
                leftMargin = dip(21)
            }

            image = roundImage {
                id = R.id.PROFILE_IMAGE

            }.lparams(width= dip(75), height = dip(75)){
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID

                topMargin = dip(28)
                leftMargin = dip(23)
            }

            text = textView{
                id =R.id.NAME_TEXT_VIEW

                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                textSize = 20F
                textColor = Color.parseColor("#6F6F6F")
            }.lparams(width = wrapContent, height = wrapContent){
                leftToRight = R.id.PROFILE_IMAGE
                topToTop = R.id.PROFILE_IMAGE

                topMargin = dip(0)
                leftMargin = dip(16)
            }

            textView{
                id =R.id.NAME_TEXT_VIEW

                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                textSize = 20F
                textColor = Color.parseColor("#6F6F6F")
            }.lparams(width = wrapContent, height = wrapContent){
                leftToRight = R.id.PROFILE_IMAGE
                topToTop = R.id.PROFILE_IMAGE

                topMargin = dip(0)
                leftMargin = dip(16)
            }

            textView {
                id = R.id.TASK_LABEL

                text = "Tutte le task"
                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                textSize = 14F
                textColor = Color.parseColor("#6F6F6F")
            }.lparams(width = wrapContent,height = wrapContent){
                topToBottom = R.id.PROFILE_IMAGE
                leftToLeft = PARENT_ID

                topMargin = dip(33)
                leftMargin = dip(23)
            }
            imageView {
                id = R.id.LOGO_IMAGE

                Glide.with(ctx).load(R.drawable.group).into(this)
            }.lparams(width= dip(24), height = dip(19)){
                leftToRight = R.id.TASK_LABEL
                bottomToBottom = R.id.TASK_LABEL

                leftMargin = dip(5)
                bottomMargin = dip(5)
            }
            recycler = recyclerView {

                id =R.id.RECYCLER_MAIN
            }.lparams(width = matchConstraint, height = matchConstraint){
                topToBottom = R.id.TASK_LABEL
                bottomToBottom = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID

                topMargin = dip(25)
                leftMargin = dip(18)
                rightMargin = dip(18)
            }

            textView{

                typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrande.ttf")
                textSize = 14F
                textColor = Color.parseColor("#6F6F6F")

                text = if (recycler.size == 0){
                    "Non hai task da completare"
                }else{
                    "Hai ancora ${recycler.size} task da completare"
                }
            }.lparams(width = wrapContent, height = wrapContent){
                leftToRight = R.id.PROFILE_IMAGE
                topToBottom= R.id.NAME_TEXT_VIEW

                topMargin = dip(9)
                leftMargin = dip(16)
            }
        }
    }
}