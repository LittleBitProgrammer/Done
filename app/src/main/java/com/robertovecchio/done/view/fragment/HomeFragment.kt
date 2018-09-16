package com.robertovecchio.done.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.view.anko.HomeLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class HomeFragment: Fragment(), OnReselectedDelegate {

    private lateinit var mainUI: HomeLayout

    private lateinit var viewUI: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainUI = HomeLayout()
        viewUI = mainUI.createView(AnkoContext.create(ctx,this))

        return viewUI
    }
    override fun onReselected() {
        //nothing for now
    }
}