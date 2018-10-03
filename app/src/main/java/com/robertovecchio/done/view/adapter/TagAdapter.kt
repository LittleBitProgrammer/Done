package com.robertovecchio.done.view.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.robertovecchio.done.R
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.view.anko.adapter.TagAdapterLayout
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TagAdapter(tag: List<Tag>?, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tagItems: List<Tag>? = tag

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        doAsync {


            val tags = tagItems!![position]

            val name = tags.color
            val color = ColorDrawable(Color.parseColor(tags.type)) //as Drawable


            uiThread {
                (holder as? ItemViewHolder)?.checkBox?.text = name
                Glide.with(context).load(color).into((holder as? ItemViewHolder)?.tagColor!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(TagAdapterLayout().createView(AnkoContext.create(parent.context, parent)))
    }



    override fun getItemCount() = tagItems!!.size


    internal class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.CHECK_BOX)
        val tagColor: CircleImageView = view.findViewById(R.id.TAG_COLOR)

        init {
            ButterKnife.bind(this,view)
        }
    }
}