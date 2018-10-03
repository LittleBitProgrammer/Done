package com.robertovecchio.done.view.fragment.subscribe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robertovecchio.done.view.anko.subscribe.pages.ImageChooserLayout
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.AnkoContext

class ImageChooser: Fragment() {

    private lateinit var mainUI: ImageChooserLayout

    private lateinit var viewUI: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainUI = ImageChooserLayout(activity!!)

        viewUI = mainUI.createView(AnkoContext.create(context!!,this))

        _roundimage = mainUI.profileImage

        return viewUI
    }

    companion object {
        lateinit var _roundimage: CircleImageView
    }
}