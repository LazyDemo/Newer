package com.wb.newer.newer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.wb.newer.newer.base.IntentKey
import com.wb.newer.newer.ui.web.WebFragment

class WebActivity : AppCompatActivity() {
    private val webFragment = WebFragment.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, webFragment)
                    .commitNow()
            webFragment.setUrl(intent.getStringExtra(IntentKey.web_url))
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webFragment.canGoBack()!!) {
            webFragment.webGoBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
