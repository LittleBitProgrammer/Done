package com.robertovecchio.done.view.fragment.add

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.view.adapter.TagAdapter
import com.robertovecchio.done.view.anko.add.AddLayout
import com.robertovecchio.done.viewmodel.AddViewModel
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx

class AddFragment: Fragment(),OnReselectedDelegate {

    private lateinit var mainUI: AddLayout

    private lateinit var viewUI: View

    private lateinit var tagRecycler: RecyclerView

    private val viewModel: AddViewModel by lazy {
        ViewModelProviders.of(this).get(AddViewModel(act.application)::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainUI = AddLayout()
        viewUI = mainUI.createView(AnkoContext.create(ctx,this))

        tagRecycler = mainUI.tagRecycler
        retrieveTag()

        return viewUI
    }

    override fun onReselected() {
    }

    private fun retrieveTag(){
        viewModel.getAllTags()?.observe(this,android.arch.lifecycle.Observer { tags ->
            tagRecycler.adapter = TagAdapter(tags,ctx)
        })

    }
}