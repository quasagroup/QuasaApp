package com.example.quasaapp


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreateTask : Fragment() {

    @SuppressLint("AddJavascriptInterface")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val handler = Handler()
        val view: View = inflater.inflate(
            R.layout.fragment_create_task,
            container,
            false
        )

        val  mWebView = view.findViewById(R.id.webView1) as WebView
        mWebView.clearCache(true)
        mWebView.loadUrl("https://quasa.webdealgroup.ru/create_task/")

        mWebView.addJavascriptInterface(
            AllTasks.WebAppInterface(context!!) {
                handler.post {
                    (activity as? MainActivity)?.closeDrawer()
                }
            }, "AndroidFunction"
        )

        val webSettings = mWebView.getSettings()
        webSettings.setJavaScriptEnabled(true)

        mWebView.setWebViewClient(WebViewClient())

        return  view
    }

    class WebAppInterface
    internal constructor(val c: Context, val function: () -> Unit) {
        // Show a menu from the web page
        @JavascriptInterface
        fun showMenu() {
            function.invoke()
        }
    }

}


