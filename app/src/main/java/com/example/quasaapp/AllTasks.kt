package com.example.quasaapp


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AllTasks : Fragment() {

    @SuppressLint("AddJavascriptInterface")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val handler = Handler()
        val view: View = inflater.inflate(
            R.layout.fragment_all_tasks,
            container,
            false
        )

        val mWebView = view.findViewById(R.id.webView1) as WebView
        mWebView.clearCache(true)
        mWebView.loadUrl("https://quasa.webdealgroup.ru/all_tasks/")

        mWebView.addJavascriptInterface(
            WebAppInterface(context!!) {
                handler.post {
                    (activity as? MainActivity)?.closeDrawer()
                }
            }, "AndroidFunction"
        )

        val webSettings = mWebView.getSettings()
        webSettings.setJavaScriptEnabled(true)

        mWebView.setWebViewClient(WebViewClient())

        return view
    }

    class WebAppInterface
    internal constructor(val c: Context, val function: () -> Unit) {
        // Show a menu from the web page
        @JavascriptInterface
        fun showMenu() {
            function.invoke()
        }
        @JavascriptInterface
        fun changeName(user_name: String) {
            Toast.makeText(c, user_name, Toast.LENGTH_SHORT).show()



        }
    }
}


