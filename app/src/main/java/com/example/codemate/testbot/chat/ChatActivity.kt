package com.example.codemate.testbot.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import com.example.codemate.testbot.R
import com.example.codemate.testbot.R.id.done
import com.example.codemate.testbot.api.ApiManager
import com.example.codemate.testbot.cv.YourCvActivity
import com.example.codemate.testbot.model.Message
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


/**
 * Created by Alex Lindroos on 28/09/2017.
 */
class ChatActivity: AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ChatActivity::class.java)
            return intent
        }
    }

    lateinit var webView: WebView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)

        webView = findViewById(R.id.webview) as WebView
        toolbar = findViewById(R.id.toolbar) as Toolbar

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val url = "https://api.motion.ai/webchat/84924?color=3588eb&sendBtn=SEND&inputBox=Type%20something...&token=6938b68c9f59dd91f2f4ba2583077765"
        val webSettings: WebSettings = webView.settings

        webSettings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.chat_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            done -> moveToCv()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun moveToCv() {
        alert(R.string.are_you_sure) {
            title = "Alert"
            yesButton {
              val intent = Intent(applicationContext, YourCvActivity::class.java)
              startActivity(intent)
            }
            noButton {

            }
        }.show()
    }

    override fun onBackPressed() {
        //USER SHOULD NOT RETURN FROM THIS ACTIVITY WITH RETURN KEY
    }
}
