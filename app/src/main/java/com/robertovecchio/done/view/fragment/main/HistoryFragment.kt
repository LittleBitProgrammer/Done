package com.robertovecchio.done.view.fragment.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.view.anko.main.HistoryLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class HistoryFragment: Fragment(), OnReselectedDelegate {

    private lateinit var mainUI: HistoryLayout

    private lateinit var viewUI: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainUI = HistoryLayout()
        viewUI = mainUI.createView(AnkoContext.create(ctx,this))

        return viewUI
    }
    override fun onReselected() {
        //nothing for now
    }

}