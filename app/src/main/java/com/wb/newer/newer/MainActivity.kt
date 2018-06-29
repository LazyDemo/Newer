package com.wb.newer.newer

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.wb.newer.newer.home.IndexFragment
import com.wb.newer.newer.home.ThirdFragment
import android.view.WindowManager
import android.os.Build
import android.app.Activity
import android.app.ActivityManager
import android.graphics.Color
import android.view.View
import com.wb.newer.newer.home.SecondFragment


class MainActivity : AppCompatActivity(), IndexFragment.OnFragmentInteractionListener, ThirdFragment.OnFragmentInteractionListener {
    override fun onIndexEvent(uri: Uri) {
        Toast.makeText(this, "index", Toast.LENGTH_LONG).show()
    }

    override fun onThirdEvent(uri: Uri) {
        Toast.makeText(this, "third", Toast.LENGTH_LONG).show()
    }

    private var mTextMessage: TextView? = null
    private var indexFragment = IndexFragment.newInstance("", "")
    private var secondFragment = SecondFragment.newInstance()
    private var thirdFragment = ThirdFragment.newInstance("", "")

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showFragment(indexFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                showFragment(secondFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                showFragment(thirdFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextMessage = findViewById(R.id.message)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        savedInstanceState ?: showFragment(indexFragment)
        setTransparentStatusBar(this)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.animator.card_alpha_in,
                        R.animator.card_alpha_out,
                        R.animator.card_alpha_in,
                        R.animator.card_alpha_out
                )
                .replace(R.id.content, fragment)
                .commit()
    }


    private fun setTransparentStatusBar(activity: Activity) {
        //5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.navigationBarColor = Color.TRANSPARENT
            //4.4到5.0
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val localLayoutParams = activity.window.attributes
            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        }

    }
}
