package com.wb.newer.newer.ui.web

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.*
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wb.newer.newer.R
import kotlinx.android.synthetic.main.web_fragment.*


class WebFragment : Fragment() {
    private lateinit var url: String

    companion object {
        fun newInstance() = WebFragment()
    }

    private lateinit var viewModel: WebViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.web_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WebViewModel::class.java)
        // TODO: Use the ViewModel
        initWebView()
    }

    private var webView: WebView? = null
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
        webView = WebView(activity?.application)
        webView?.layoutParams = layoutParams

        web.addView(webView)

        val settings = webView?.settings
        settings?.javaScriptEnabled = true
        settings?.useWideViewPort = true
        settings?.loadWithOverviewMode = true

        settings?.setSupportZoom(false)
        settings?.builtInZoomControls = false
        settings?.displayZoomControls = false

        webView?.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        //其他细节操作
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

//        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK; //关闭webview中缓存
//        settings.allowFileAccess = true; //设置可以访问文件
//        settings.javaScriptCanOpenWindowsAutomatically = true; //支持通过JS打开新窗口
//        settings.loadsImagesAutomatically = true; //支持自动加载图片
//        settings.defaultTextEncodingName = "utf-8";//设置编码格式

//        settings.domStorageEnabled = true // 开启 DOM storage API 功能
//        settings.databaseEnabled = true   //开启 database storage API 功能
//        settings.setAppCacheEnabled(true)//开启 Application Caches 功能

        webView?.loadUrl(url)

    }

    fun setUrl(url: String) {
        this.url = url
    }

    override fun onResume() {
        super.onResume()
        webView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (webView != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            val parent = webView?.parent as ViewGroup
            parent.removeView(webView)

            webView?.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webView?.settings?.javaScriptEnabled = false
            webView?.clearHistory()
            webView?.clearView()
            webView?.removeAllViews()
            try {
                webView?.destroy()
            } catch (ex: Throwable) {
            }
        }

    }

    fun webGoBack() {
        webView?.goBack()
    }

    fun canGoBack(): Boolean? {
        return webView?.canGoBack()
    }


}
