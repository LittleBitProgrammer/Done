package com.robertovecchio.done.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.robertovecchio.done.model.entity.Task
import com.robertovecchio.done.view.anko.adapter.TaskAdapterLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.runOnUiThread
import kotlin.concurrent.thread

class TaskAdapter(task: List<Task>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val taskItems: List<Task> = task

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tasks = taskItems[position]

        thread(start = true) {
            //val coverUrl = book.coverUrl

            context.runOnUiThread {
                //Glide.with(context).load(coverUrl).into(holderContainer?.coverImage!!)
                //holderContainer.bookTitle.text = title
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(TaskAdapterLayout().createView(AnkoContext.create(parent.context, parent)))
    }



    override fun getItemCount() = taskItems.size


    internal class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        //val coverImage: ImageView = view.findViewById(R.id.image_bestweek)

        init {

            ButterKnife.bind(this,view)
        }
    }
}