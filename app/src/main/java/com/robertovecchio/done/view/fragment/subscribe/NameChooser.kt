package com.robertovecchio.done.view.fragment.subscribe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.robertovecchio.done.view.anko.subscribe.pages.NameChooserLayout
import org.jetbrains.anko.AnkoContext

class NameChooser: Fragment() {

    private lateinit var mainUI: NameChooserLayout

    private lateinit var viewUI: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainUI = NameChooserLayout()

        viewUI = mainUI.createView(AnkoContext.create(context!!,this))

        _editName = mainUI.editName

        return viewUI
    }

    companion object {
        lateinit var _editName: EditText
    }
}