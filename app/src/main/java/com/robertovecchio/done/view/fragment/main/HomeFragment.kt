package com.robertovecchio.done.view.fragment.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.robertovecchio.done.model.component.DaggerDoneComponent
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.model.module.ActivityModule
import com.robertovecchio.done.model.module.AnkoModule
import com.robertovecchio.done.model.module.ContextModule
import com.robertovecchio.done.viewmodel.HomeViewModel
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject
import javax.inject.Named

class HomeFragment: Fragment(), OnReselectedDelegate {

    private var isDatabaseLoad = false

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject @field:Named("home") lateinit var viewUI: View

    @Inject lateinit var textName: TextView
    @Inject lateinit var profileImage: CircleImageView
    @Inject lateinit var tags: MutableList<Tag>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        DaggerDoneComponent.builder()
                .ankoModule(AnkoModule())
                .contextModule(ContextModule(ctx))
                .activityModule(ActivityModule(act))
                .build()
                .injectHome(this)

        val vm = ViewModelProviders.of(this,viewModelFactory)[HomeViewModel::class.java]

        isDatabaseLoad = act.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isDatabaseLoad", true)

        vm.retrieveAllTask()?.observe(this, Observer { taskItem ->
            //set here the adapter
        })

        vm.retrievveUserById(1)?.observe(this, Observer { activeUser ->
            textName.text = activeUser?.userName.toString()
            val uri = Uri.parse(activeUser?.image)
            Glide.with(this).load(uri).into(profileImage)
        })

        if (isDatabaseLoad){
            setTagValue()
            vm.insertTags(tags)
            act.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isDatabaseLoad", false).apply()
        }

        return viewUI
    }
    override fun onReselected() {
        //nothing for now
    }

    private fun setTagValue(){
        tags.add(Tag(0,"Family","#56D999"))
        tags.add(Tag(0,"Work","#F15360"))
        tags.add(Tag(0,"Daily","#FBE054"))
        tags.add(Tag(0,"Friend","#568BD9"))
    }
}