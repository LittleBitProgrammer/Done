package com.robertovecchio.done.view.fragment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.robertovecchio.done.model.component.DaggerDoneComponent
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.model.module.ActivityModule
import com.robertovecchio.done.model.module.AnkoModule
import com.robertovecchio.done.model.module.ContextModule
import com.robertovecchio.done.view.adapter.TagAdapter
import com.robertovecchio.done.viewmodel.AddViewModel
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject
import javax.inject.Named

class AddFragment: Fragment(),OnReselectedDelegate {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject @field:Named("ADD") lateinit var viewUI: View
    @Inject lateinit var tagRecycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        DaggerDoneComponent.builder()
                .ankoModule(AnkoModule())
                .contextModule(ContextModule(ctx))
                .activityModule(ActivityModule(act))
                .build()
                .injectAdd(this)

        val vm = ViewModelProviders.of(this,viewModelFactory)[AddViewModel::class.java]

        vm.getAllTags()?.observe(this, Observer { tags ->
            tagRecycler.adapter = TagAdapter(tags,ctx)
        })

        return viewUI
    }

    override fun onReselected() {
    }

}