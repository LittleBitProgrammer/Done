package com.robertovecchio.done.view.fragment.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.robertovecchio.done.model.database.DoneDatabase
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.entity.User
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.model.thread.DbWorkerThread
import com.robertovecchio.done.view.anko.main.HomeLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx

class HomeFragment: Fragment(), OnReselectedDelegate {

    private var isDatabaseLoad = false

    private lateinit var mainUI: HomeLayout

    private lateinit var viewUI: View

    private var mDb: DoneDatabase? = null

    private  val mDbWorkerThread: DbWorkerThread = DbWorkerThread("dbWorkerThread")

    private val mUiHandler = Handler()

    private var user: User = User()
    private var tags: MutableList<Tag> = mutableListOf()
    private lateinit var textName: String
    private lateinit var imageSTring: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainUI = HomeLayout()
        viewUI = mainUI.createView(AnkoContext.create(ctx,this))

        if (!mDbWorkerThread.isAlive) {
            mDbWorkerThread.start()
        }

        mDb = DoneDatabase.getInstance(ctx)

        isDatabaseLoad = act.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isDatabaseLoad", true)

        retrieveName()
        loadTags()

        return viewUI
    }
    override fun onReselected() {
        //nothing for now
    }

    override fun onDestroy() {
        DoneDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }

    private fun retrieveName(){
        val task = Runnable {
            user = mDb?.daoAccess()?.retrieveUserById(1)!!
            textName = user.userName
            imageSTring = user.image!!
            val mUri = Uri.parse(imageSTring)

            mUiHandler.post {
                mainUI.text.text = "Ciao $textName !"
                Glide.with(ctx).load(mUri).into(mainUI.image)
            }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun loadTags(){
        if (isDatabaseLoad){
            val task = Runnable {
                setTagValue()
                mDb?.daoAccess()?.insertTags(tags)
            }
            act.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isDatabaseLoad", false).apply()
            mDbWorkerThread.postTask(task)
            Log.i("TASK", "executed")
        }
    }

    private fun setTagValue(){
        tags.add(Tag(0,"Family","#56D999"))
        tags.add(Tag(0,"Work","#F15360"))
        tags.add(Tag(0,"Daily","#FBE054"))
        tags.add(Tag(0,"Friend","#568BD9"))
    }
}