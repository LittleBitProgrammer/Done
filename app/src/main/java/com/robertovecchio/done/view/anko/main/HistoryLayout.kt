package com.robertovecchio.done.view.anko.main

import com.robertovecchio.done.view.fragment.main.HistoryFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView

class HistoryLayout: AnkoComponent<HistoryFragment>{
    override fun createView(ui: AnkoContext<HistoryFragment>) = with(ui) {
        linearLayout {
            textView("History Fragment")
        }
    }
}