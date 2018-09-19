package com.robertovecchio.done.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife
import com.robertovecchio.done.R
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.view.anko.adapter.TagAdapterLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.runOnUiThread
import kotlin.concurrent.thread

class TagAdapter(tag: List<Tag>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tagItems: List<Tag> = tag

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tags = tagItems[position]

        thread(start = true) {
            //val name = tags.type

            context.runOnUiThread {
                //(holder as? ItemViewHolder)?.tagName?.text = name
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(TagAdapterLayout().createView(AnkoContext.create(parent.context, parent)))
    }



    override fun getItemCount() = tagItems.size


    internal class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.CHECK_BOX)
        //val tagName: TextView = view.findViewById(R.id.TAG_NAME)
        val tagColor: ImageView = view.findViewById(R.id.TAG_COLOR)

        init {

            ButterKnife.bind(this,view)
        }
    }
}