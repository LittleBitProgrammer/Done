package com.robertovecchio.done.model.module

import android.content.Context
import com.robertovecchio.done.model.entity.Tag
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robertovecchio.done.view.anko.add.AddLayout
import com.robertovecchio.done.view.anko.main.HomeLayout
import com.robertovecchio.done.view.fragment.add.AddFragment
import com.robertovecchio.done.view.fragment.main.HomeFragment
import dagger.Module
import dagger.Provides
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.AnkoContext
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ContextModule::class, EntityModule::class, FragmentModule::class, LayoutModule::class])

class AnkoModule{

    @Singleton
    @Provides
    @Named("ADD")
    fun provideAddView(addLayout: AddLayout,context: Context,addFragment: AddFragment): View{
        return addLayout.createView(AnkoContext.create(context,addFragment))
    }

    @Singleton
    @Provides @Named("home")
    fun provideView(homeLayout: HomeLayout, context: Context, homeFragment: HomeFragment): View{
        return homeLayout.createView(AnkoContext.create(context,homeFragment))
    }

    @Singleton
    @Provides
    fun provideTextName(homeLayout: HomeLayout): TextView{
        return homeLayout.text
    }

    @Singleton
    @Provides
    fun provideImageProfile(homeLayout: HomeLayout): CircleImageView{
        return homeLayout.image
    }

    @Singleton
    @Provides
    fun provideTags(): List<Tag>{
        return mutableListOf()
    }

    @Singleton
    @Provides
    fun provideRecyclerView(addLayout: AddLayout): RecyclerView {
        return addLayout.tagRecycler
    }
}