package com.robertovecchio.done.view.fragment.subscribe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertovecchio.done.view.anko.subscribe.pages.ImageChooserLayout
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx

class ImageChooser: Fragment() {

    private lateinit var mainUI: ImageChooserLayout

    private lateinit var viewUI: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainUI = ImageChooserLayout(act)

        viewUI = mainUI.createView(AnkoContext.create(ctx,this))

        _roundimage = mainUI.profileImage

        return viewUI
    }

    companion object {
        lateinit var _roundimage: CircleImageView
    }
}