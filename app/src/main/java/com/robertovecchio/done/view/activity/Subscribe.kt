package com.robertovecchio.done.view.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.robertovecchio.done.R
import com.robertovecchio.done.model.database.DoneDatabase
import com.robertovecchio.done.model.entity.User
import com.robertovecchio.done.view.anko.subscribe.host.SubscribeLayout
import com.robertovecchio.done.view.fragment.subscribe.ImageChooser
import com.robertovecchio.done.view.fragment.subscribe.ImageChooser.Companion._roundimage
import com.robertovecchio.done.view.fragment.subscribe.NameChooser
import com.robertovecchio.done.view.fragment.subscribe.NameChooser.Companion._editName
import com.robertovecchio.done.viewmodel.SubscribeViewModel
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk27.coroutines.onClick

class Subscribe: AppCompatActivity() {

    private lateinit var mainUI: SubscribeLayout

    private lateinit var viewUI: View

    private var fragmentManager: FragmentManager? = null

    private lateinit var subscribeButton: Button

    private lateinit var newUser: User

    private var isNameLoaded: Boolean = false

    private val viewModel: SubscribeViewModel by lazy {
        ViewModelProviders.of(this).get(SubscribeViewModel(this.application)::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1)
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                2)

        fragmentManager = supportFragmentManager

        mainUI = SubscribeLayout()

        viewUI = mainUI.createView(AnkoContext.create(this,this))
        setContentView(viewUI)

        newUser = User()

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager!!
                    .beginTransaction()
                    .replace(R.id.FRAME_CONTAINER, NameChooser(), "Login_Fragment")
                    .commit()
        }

        subscribeButton = mainUI.subscribeButton
        subscribeButton.onClick {
            if (!isNameLoaded){
                if (isEditTextEmpty(_editName)){
                    _editName.error = "Questo campo non può essere vuoto"
                }else{
                    isNameLoaded = true
                    newUser.userName = getString(_editName)
                    fragmentManager!!
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                            .replace(R.id.FRAME_CONTAINER, ImageChooser(), "ImageChooser")
                            .commit()
                    subscribeButton.text = "Fine"
                }
            }else{
                getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply()
                newUser.image = _pickedImage.toString()

                insertName()

                startActivity(Intent(this@Subscribe, MainActivity::class.java))
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            }
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                if (!isPointInsideView(event.rawX, event.rawY, v)) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    v.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun isPointInsideView(x: Float, y: Float, view: View): Boolean {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val viewX = location[0]
        val viewY = location[1]

        // point is inside view bounds
        return x > viewX && x < viewX + view.width && y > viewY && y < viewY + view.height
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 ->
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Let's read picked roundedImage data - its URI
                    _pickedImage = data.data!!
                    Glide.with(this).load(_pickedImage).into(_roundimage)
                    //val imageStream = contentResolver.openInputStream(pickedImage)
                    //_bitmapSelectedImage = BitmapFactory.decodeStream(imageStream)
                }
        }
    }

    companion object {
        lateinit var _pickedImage: Uri
    }

    private fun getString(editText: EditText): String {
        return editText.text.toString()
    }

    /**
     *
     * This function work to see if a editText is empty
     *
     * @bookAuthor Roberto Vecchio
     * @param editText editText that you want to verify if is empty
     *
     * @version 1.0
     *
     * @return if is EMPTY or NOT
     *
     */
    private fun isEditTextEmpty(editText: EditText): Boolean {
        return editText.length() == 0
    }

    private fun insertName(){
        viewModel.insertUser(newUser)
    }

    override fun onDestroy() {
        DoneDatabase.destroyInstance()
        super.onDestroy()
    }
}