package com.robertovecchio.done.view.anko.add

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.robertovecchio.done.R
import com.robertovecchio.done.view.fragment.add.AddFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AddLayout: AnkoComponent<AddFragment> {

    lateinit var tagRecycler: RecyclerView

    override fun createView(ui: AnkoContext<AddFragment>) = with(ui) {
        scrollView {
            linearLayout {
                orientation = LinearLayout.VERTICAL

                textView {
                    text = "Titolo"
                    textSize = 20F
                    textColor = ContextCompat.getColor(ctx,R.color.textDark)
                    typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                    textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(22)
                    leftMargin = dip(18)
                }

                editText {
                    inputType = InputType.TYPE_CLASS_TEXT
                    maxLines = 1

                    background = ContextCompat.getDrawable(ctx,R.drawable.task_background)
                    val maxLength = 30
                    filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))

                    textColor = Color.parseColor("#8D8D8D")
                    textSize = 14F
                    padding = dip(10)

                }.lparams(width = matchParent, height = wrapContent){
                    leftMargin = dip(18)
                    rightMargin = dip(18)

                }
                textView {
                    text = "Descrizione"
                    textSize = 20F
                    textColor = ContextCompat.getColor(ctx,R.color.textDark)
                    typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                    textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(13)
                    leftMargin = dip(18)
                }

                editText {

                    val maxLength = 150
                    filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
                    background = ContextCompat.getDrawable(ctx,R.drawable.task_background)
                    textColor = Color.parseColor("#8D8D8D")
                    textSize = 14F

                    gravity = Gravity.TOP
                    padding = dip(10)
                }.lparams(width = matchParent, height = dip(130)){
                    leftMargin = dip(18)
                    rightMargin = dip(18)

                }
                textView {
                    text = "Tag"
                    textSize = 20F
                    textColor = ContextCompat.getColor(ctx,R.color.textDark)
                    typeface = Typeface.createFromAsset(ctx.assets,"LucidaGrandeBold.ttf")
                    textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(18)
                    leftMargin = dip(18)
                }
                tagRecycler = recyclerView {
                    ViewCompat.setNestedScrollingEnabled(this, false)
                    this.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                    this.setHasFixedSize(true)

                    val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                    itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
                    addItemDecoration(itemDecorator)

                }.lparams(width = matchParent, height = wrapContent){
                    leftMargin = dip(12)
                    topMargin = dip(8)
                }
            }.lparams(matchParent, wrapContent)
        }
    }
}