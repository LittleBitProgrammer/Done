package com.robertovecchio.done.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.robertovecchio.done.R
import com.robertovecchio.done.view.anko.splash.SplashLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.ctx

class SplashScreen: AppCompatActivity() {

    private lateinit var mainUI: SplashLayout

    private lateinit var viewUi: View

    //BOOLEAN VARIABLES
    private val animationStarted = false

    //LAYOUT VARIABLES
    private lateinit var centralLogo: ImageView
    private lateinit var textPowered: TextView
    private lateinit var textName: TextView

     override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)

         mainUI = SplashLayout()

         viewUi = mainUI.createView(AnkoContext.create(this,this))

         centralLogo = mainUI.image
         textPowered = mainUI.powered
         textName = mainUI.name

         setContentView(viewUi)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        if (!hasFocus || animationStarted) {
            return
        }

        animate()

        super.onWindowFocusChanged(hasFocus)
    }

    private fun animate(){
        centralLogo.y = -1000F
        textPowered.y = 2500F
        textName.y = 2800F

        ViewCompat.animate(centralLogo)
                .translationY(0F)
                .setStartDelay(50.toLong())
                .setDuration(800.toLong())
                .setInterpolator(DecelerateInterpolator(1.2F))
                .start()

        ViewCompat.animate(textPowered)
                .translationY(0F)
                .setStartDelay(50.toLong())
                .setDuration(800.toLong())
                .setInterpolator(DecelerateInterpolator(1.2F))
                .start()

        ViewCompat.animate(textName)
                .translationY(0F)
                .setStartDelay(50.toLong())
                .setDuration(800.toLong())
                .setInterpolator(DecelerateInterpolator(1.2F))
                .withEndAction {
                    Handler().postDelayed({
                        val isFirstRun = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isFirstRun", true)

                        if (isFirstRun){
                            startActivity(Intent(this@SplashScreen, Subscribe::class.java))
                            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
                            Log.i("PRIMA RUN", "TRUE")
                        }else{
                            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
                            Log.i("PRIMA RUN", "FALSE")
                            Log.i("PRIMA RUN", isFirstRun.toString())
                        }
                    },50)
                }
                .start()
    }
}