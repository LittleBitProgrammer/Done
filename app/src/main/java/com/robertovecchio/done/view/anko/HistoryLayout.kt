package com.robertovecchio.done.view.anko

import android.view.View
import com.robertovecchio.done.view.fragment.HistoryFragment
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