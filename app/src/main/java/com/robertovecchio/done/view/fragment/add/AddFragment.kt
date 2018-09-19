package com.robertovecchio.done.view.fragment.add

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertovecchio.done.model.database.DoneDatabase
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.model.thread.DbWorkerThread
import com.robertovecchio.done.view.adapter.TagAdapter
import com.robertovecchio.done.view.anko.add.AddLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class AddFragment: Fragment(),OnReselectedDelegate {

    private lateinit var mainUI: AddLayout

    private lateinit var viewUI: View

    private var mDb: DoneDatabase? = null

    private  var mDbWorkerThread: DbWorkerThread = DbWorkerThread("dbWorkerThread2")

    private val mUiHandler = Handler()

    private lateinit var tags: List<Tag>

    private lateinit var tagRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!mDbWorkerThread.isAlive) {
            mDbWorkerThread.start()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainUI = AddLayout()
        viewUI = mainUI.createView(AnkoContext.create(ctx,this))

        mDb = DoneDatabase.getInstance(ctx)
        tagRecycler = mainUI.tagRecycler

        return viewUI
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        retrieveTag()
    }
    override fun onReselected() {
    }

    override fun onDestroyView() {
        DoneDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroyView()
    }

    private fun retrieveTag(){
        val task = Runnable {
            tags = mDb?.daoAccess()?.retrieveAllTag()!!
            mUiHandler.post {
                tagRecycler.adapter = TagAdapter(tags,ctx)
            }
        }

        mDbWorkerThread.postTask(task)
    }
}