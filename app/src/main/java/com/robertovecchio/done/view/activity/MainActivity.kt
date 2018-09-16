package com.robertovecchio.done.view.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.robertovecchio.done.R
import com.robertovecchio.done.model.general.or
import com.robertovecchio.done.model.interfaces.OnReselectedDelegate
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

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

                returnValue = true
            }

            1->{
                currentController = navHistoryController

                sectionHomeWrapper.visibility = View.INVISIBLE
                sectionHistoryWrapper.visibility = View.VISIBLE

                returnValue = true
            }

        }
        onReselected(position)
        return@OnTabSelectedListener returnValue
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentController = navHomeController

        initUI()

        sectionHomeWrapper.visibility = View.VISIBLE
        sectionHistoryWrapper.visibility = View.INVISIBLE
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

        bottomNavigation.defaultBackgroundColor = ContextCompat.getColor(ctx,R.color.colorPrimaryDark)
        bottomNavigation.setOnTabSelectedListener(mOnNavigationItemSelectedListener)
    }
}
