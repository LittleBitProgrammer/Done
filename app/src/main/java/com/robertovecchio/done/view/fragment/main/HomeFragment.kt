package com.robertovecchio.done.view.fragment.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.robertovecchio.done.model.database.DoneDatabase
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.view.anko.main.HomeLayout
import com.robertovecchio.done.viewmodel.HomeViewModel
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx

class HomeFragment: Fragment(), OnReselectedDelegate {

    private var isDatabaseLoad = false

    private lateinit var mainUI: HomeLayout

    private lateinit var viewUI: View

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel(act.application)::class.java)
    }

    private var tags: MutableList<Tag> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainUI = HomeLayout()
        viewUI = mainUI.createView(AnkoContext.create(ctx,this))

        isDatabaseLoad = act.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isDatabaseLoad", true)

        viewModel.retrieveAllTask()?.observe(this,android.arch.lifecycle.Observer { taskItem ->
            //set here the adapter
        })

        viewModel.retrievveUserById(1)?.observe(this, android.arch.lifecycle.Observer { activeUser ->
            mainUI.text.text = activeUser?.userName.toString()
            val uri = Uri.parse(activeUser?.image)
            Glide.with(this).load(uri).into(mainUI.image)
        })

        if (isDatabaseLoad){
            setTagValue()
            viewModel.insertTags(tags)
            act.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isDatabaseLoad", false).apply()
            Log.i("TASK", "executed")
        }

        return viewUI
    }
    override fun onReselected() {
        //nothing for now
    }

    override fun onDestroy() {
        DoneDatabase.destroyInstance()
        super.onDestroy()
    }

    private fun setTagValue(){
        tags.add(Tag(0,"Family","#56D999"))
        tags.add(Tag(0,"Work","#F15360"))
        tags.add(Tag(0,"Daily","#FBE054"))
        tags.add(Tag(0,"Friend","#568BD9"))
    }
}