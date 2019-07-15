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
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Login : Fragment() {

    @SuppressLint("AddJavascriptInterface")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val handler = Handler()
        val view: View = inflater.inflate(
            R.layout.fragment_login,
            container,
            false
        )

        val  mWebView = view.findViewById(R.id.webView1) as WebView
        mWebView.clearCache(true)
        mWebView.loadUrl("https://quasa.webdealgroup.ru/login/")

        mWebView.addJavascriptInterface(
             WebAppInterface1 (context!! ) { user_name ->
                 handler.post {
                     (activity as? MainActivity)?.changeUsername(user_name)
                 }
            }, "Android"
        )
        mWebView.addJavascriptInterface(
            WebAppInterface2 (context!! ) {
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

    class WebAppInterface1
    internal constructor(val c: Context, val function: (name:String) -> Unit) {

        @JavascriptInterface
        fun changeName(user_name: String) {
            //Toast.makeText(c, user_name, Toast.LENGTH_SHORT).show()

            function.invoke(user_name)
        }

    }

    class WebAppInterface2
    internal constructor(val c: Context, val function: () -> Unit) {

        @JavascriptInterface
        fun showMenu() {
            function.invoke()
        }

    }
}



