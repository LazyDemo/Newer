package com.wb.newer.newer

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.wb.newer.newer.home.IndexFragment
import com.wb.newer.newer.home.SecondFragment
import com.wb.newer.newer.home.ThirdFragment

class MainActivity : AppCompatActivity(),IndexFragment.OnFragmentInteractionListener,SecondFragment.OnFragmentInteractionListener,ThirdFragment.OnFragmentInteractionListener {
    override fun onIndexEvent(uri: Uri) {
        Toast.makeText(this,"index",Toast.LENGTH_LONG).show()
    }

    override fun onSecondEvent(uri: Uri) {
        Toast.makeText(this,"second",Toast.LENGTH_LONG).show()
    }

    override fun onThirdEvent(uri: Uri) {
        Toast.makeText(this,"third",Toast.LENGTH_LONG).show()
    }

    private var mTextMessage: TextView? = null
    private var indexFragment = IndexFragment.newInstance("", "")
    private var secondFragment = SecondFragment.newInstance("", "")
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

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }

}
