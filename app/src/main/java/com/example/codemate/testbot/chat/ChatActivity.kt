package com.example.codemate.testbot.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import com.example.codemate.testbot.R
import com.example.codemate.testbot.R.id.*
import com.example.codemate.testbot.api.ApiManager
import com.example.codemate.testbot.cv.YourCvActivity
import com.example.codemate.testbot.model.Message
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton


/**
 * Created by Alex Lindroos on 28/09/2017.
 */

//ChatBot Activity

class ChatActivity: AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var toolbar: Toolbar


    private var disposable: Disposable? = null
    private var listOfAllMessages: List<Message>? = null
    private var listOfCurrentUserMessages: MutableList<Message>? = null
    private var conversationFetched = false
    private var sessionIdFetched = false
    private var SESSION_ID = ""

    private var name: String = ""
    private var location: String = ""
    private var gender: String = ""
    private var age: String = ""
    private var email: String = ""
    private var number: String = ""
    private var profession: String = ""
    private var education: String = ""
    private var languages: String = ""
    private var hobbies: String = ""

    private val apiManager by lazy {
        ApiManager.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)

        webView = findViewById(R.id.webview) as WebView
        toolbar = findViewById(R.id.toolbar) as Toolbar

        //Setup the toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        //Setup the webview
        val url = "https://api.motion.ai/webchat/84924?color=3588eb&sendBtn=SEND&inputBox=Type%20something...&token=6938b68c9f59dd91f2f4ba2583077765"
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

    override fun onResume() {
        super.onResume()
        disposable?.dispose()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.chat_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            fetch_id -> fetchSessionId()
            fetch_info -> fetchConversationWithSessionId(SESSION_ID)
            done -> moveToCv()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        logout()
    }
//Function for moving in to the finished cv activity.
    private fun moveToCv() {
        alert(R.string.are_you_sure) {
            title = "Alert"
            yesButton {
                if (conversationFetched) {
                    getCurrentUserMessages()
                    persistAndMoveToCv()
                } else {
                    toast(R.string.please_fetch)
                }
            }
            noButton {

            }
        }.show()
    }
//This function fetches the session id from the latest conversation with the chatbot.
    private fun fetchSessionId() {
        disposable = apiManager.getConversations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            listOfAllMessages = result.messages
                            val sessionMessage = listOfAllMessages?.get(0)
                            SESSION_ID = sessionMessage!!.session
                            sessionIdFetched = true
                            toast("Session ID fetched")
                        },
                        { error ->
                            Toast.makeText(this, "ERROR FETCHING THE CONVERSATION", Toast.LENGTH_SHORT).show()
                            sessionIdFetched = false
                        }
                )
    }
//This function will fetch the latest conversation with the session id that is fetched first
    private fun fetchConversationWithSessionId(sessionId: String) {
        disposable = apiManager.getConversationsWithSessionId(sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            listOfCurrentUserMessages = result.messages
                            conversationFetched = true
                            toast("CV Data fetched")
                        },
                        { error ->
                            Toast.makeText(this, "ERROR FETCHING THE CONVERSATION", Toast.LENGTH_SHORT).show()
                            conversationFetched = false
                        }
                )
    }
//This function will loop through the listOfAllMessages and adds all messages from the user in to the new list.
    private fun getCurrentUserMessages() {

        if (listOfAllMessages == null) {
            return
        }

        listOfAllMessages!!.forEach {
            if (it.session == SESSION_ID) {
                listOfCurrentUserMessages!!.add(it)
            } else {
                return
            }
        }
    }
//This function will loop listOfCurrentUserMessages and persists all answers and then starts YourCvActivity with new data.
    private fun persistAndMoveToCv() {
        listOfCurrentUserMessages!!.forEach {
            if (it.text != "immediateNext") {
                when (it.moduleNickname) {
                    "Location" -> name = it.text
                    "Gender" -> location = it.text
                    "Age" -> gender = it.text
                    "Email" -> age = it.text
                    "Phone number" -> email = it.text
                    "Personal info done" -> number = it.text
                    "Education" -> profession = it.text
                    "Languages" -> education = it.text
                    "Hobbies" -> languages = it.text
                    "End module" -> hobbies = it.text
                }
            }
        }
        val intent = YourCvActivity.newIntent(this,
                name,
                location,
                gender,
                age,
                email,
                number,
                profession,
                education,
                languages,
                hobbies)
        startActivity(intent)
        disposable?.dispose()
    }
//Function for logging out
    private fun logout() {
        alert(R.string.logout) {
            title = "Alert"
            yesButton {
                finish()
            }
            noButton {

            }
        }.show()
    }
}

