package com.robertovecchio.done.view.anko

import com.robertovecchio.done.view.fragment.HomeFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView

class HomeLayout: AnkoComponent<HomeFragment> {
    override fun createView(ui: AnkoContext<HomeFragment>) = with(ui) {
        linearLayout {
            textView("HOME")
        }
    }
}