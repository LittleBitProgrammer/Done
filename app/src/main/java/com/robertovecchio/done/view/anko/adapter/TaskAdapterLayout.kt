package com.robertovecchio.done.view.anko.adapter

import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout

class TaskAdapterLayout: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View  = with(ui){
        linearLayout {  }
    }
}