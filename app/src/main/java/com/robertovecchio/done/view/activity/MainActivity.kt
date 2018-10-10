package com.robertovecchio.done.view.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.robertovecchio.done.R
import com.robertovecchio.done.model.component.DaggerDoneComponent
import com.robertovecchio.done.model.general.or
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import com.robertovecchio.done.model.module.ActivityModule
import com.robertovecchio.done.model.module.AnkoModule
import com.robertovecchio.done.model.module.ContextModule
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.act
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var isInsideAdd: Boolean = false

    private val fab: FloatingActionButton by lazy { fabButton }
    private val confirmFab: FloatingActionButton by lazy { fabButtonConfirm }

    private val sectionHomeWrapper: FrameLayout by lazy { section_home_wrapper }
    private val sectionHistoryWrapper: FrameLayout by lazy { section_history_wrapper }

    private val navHomeController: NavController by lazy { findNavController(R.id.section_home) }
    private val navHomeFragment: Fragment by lazy { section_home }

    private val navHistoryController: NavController by lazy { findNavController(R.id.section_history) }
    private val navHistoryFragment: Fragment by lazy { section_history }

    private var currentController: NavController? = null

    // UI
    private lateinit var bottomNavigation: AHBottomNavigation
    private val bottomNavigationItems = ArrayList<AHBottomNavigationItem>()

    private val mOnNavigationItemSelectedListener = AHBottomNavigation.OnTabSelectedListener { position, _ ->

        var returnValue = false

        when(position){

            0->{
                currentController = navHomeController

                sectionHomeWrapper.visibility = View.VISIBLE
                sectionHistoryWrapper.visibility = View.INVISIBLE
                fab.show()
                confirmFab.show()

                returnValue = true
            }

            1->{
                currentController = navHistoryController

                sectionHomeWrapper.visibility = View.INVISIBLE
                sectionHistoryWrapper.visibility = View.VISIBLE
                fab.hide()
                confirmFab.hide()

                returnValue = true
            }

        }
        //onReselected(position)
        return@OnTabSelectedListener returnValue
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentController = navHomeController

        initUI()

        sectionHomeWrapper.visibility = View.VISIBLE
        sectionHistoryWrapper.visibility = View.INVISIBLE
        fab.show()
        confirmFab.show()

        fab.onClick {
            Completable.fromAction { doClickEvent() }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
        }
    }

    private fun doClickEvent(){
        isInsideAdd = if (!isInsideAdd){
                Completable.fromAction {  navHomeController.navigate(R.id.action_home_to_add)}
                        .subscribeOn(Schedulers.newThread())
                        .subscribe()

                Completable.fromAction {  fab.animate().rotation(45F)
                confirmFab.animate().translationX(-200F)}
                        .subscribeOn(Schedulers.io())
                        .subscribe()
            true
        }else{
            Completable.fromAction { navHomeController.navigateUp()}
                    .subscribeOn(Schedulers.newThread())
                    .subscribe()
                Completable.fromAction {  fab.animate().rotation(0F)
                confirmFab.animate().translationX(0F)}
                        .subscribeOn(Schedulers.io())
                        .subscribe()
            false
        }
    }
    override fun onBackPressed() {
        currentController
                ?.let { if (it.popBackStack().not()) finish() }
                .or { finish() }
    }

    private fun onReselected(itemId: Int){
        when(itemId){

            0->{
                val fragmentClassName = (navHomeController.currentDestination as FragmentNavigator.Destination).fragmentClass.simpleName

                navHomeFragment.childFragmentManager.fragments.asReversed().forEach {

                    if (it.javaClass.simpleName == fragmentClassName && it is OnReselectedDelegate){
                        it.onReselected()
                        return@forEach
                    }

                }
            }

            1->{
                val fragmentClassName = (navHistoryController.currentDestination as FragmentNavigator.Destination).fragmentClass.simpleName

                navHistoryFragment.childFragmentManager.fragments.asReversed().forEach {

                    if (it.javaClass.simpleName == fragmentClassName && it is OnReselectedDelegate){
                        it.onReselected()
                        return@forEach
                    }

                }
            }

        }
    }

    private fun initUI(){
        bottomNavigation = findViewById(R.id.navigation)

        val item1 = AHBottomNavigationItem(R.string.home_section,R.drawable.home_icon_silhouette,R.color.white)
        val item2 = AHBottomNavigationItem(R.string.history_section, R.drawable.navigation_history_interface_symbol_of_a_clock_with_an_arrow, R.color.white)

        bottomNavigationItems.add(item1)
        bottomNavigationItems.add(item2)

        bottomNavigation.addItems(bottomNavigationItems)

        bottomNavigation.isTranslucentNavigationEnabled = false

        bottomNavigation.inactiveColor = Color.parseColor("#FFFFFF")
        bottomNavigation.accentColor = Color.parseColor("#FFFFFF")

        bottomNavigation.elevation = 0F

        //bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        bottomNavigation.defaultBackgroundColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        bottomNavigation.setOnTabSelectedListener(mOnNavigationItemSelectedListener)
    }
}
