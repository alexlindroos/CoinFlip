package com.example.codemate.testbot.cv

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import com.example.codemate.testbot.BaseActivity
import com.example.codemate.testbot.R
import com.example.codemate.testbot.api.ApiManager
import com.example.codemate.testbot.model.Message
import com.example.codemate.testbot.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_yourcv.*
import org.jetbrains.anko.image

/**
 * Created by Alex Lindroos on 04/10/2017.
 */

class YourCvActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, YourCvActivity::class.java)
            return intent
        }
    }

    var disposable: Disposable? = null
    var listOfAllMessages: List<Message>? = null
    var listOfCurrentUserMessages: MutableList<Message>? = null

    var name: String = ""
    var location: String = ""
    var gender: String = ""
    var age: String = ""
    var email: String = ""
    var number: String = ""
    var profession: String = ""
    var education: String = ""
    var languages:String = ""
    var hobbies: String = ""

    val apiManager by lazy {
        ApiManager.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yourcv)

        your_cv_profile_pic.setOnClickListener {
            fetchConversation()
        }
    }

    override fun onResume() {
        super.onResume()
        disposable?.dispose()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun fetchConversation() {
        disposable = apiManager.getConversations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            result ->
                            listOfAllMessages = result.messages
                            getCurrentUserMessages()
                            invalidateView()
                        },
                        {
                            error ->
                            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                        }
                )
    }

    private fun getCurrentUserMessages() {
        listOfAllMessages!!.forEach {
            if (it.session == "5c93b50c-d589-4306-8e4d-88f22ca9a4c2") {
               listOfCurrentUserMessages!!.add(it)
            }
        }
    }

    private fun invalidateView() {
        listOfCurrentUserMessages!!.forEach {
            if (it.text != "immediateNext") {
                when (it.moduleNickname) {
                    "Name" -> name = it.text
                    "Location" -> location = it.text
                    "Gender" -> gender = it.text
                    "Age" -> age = it.text
                    "Email" -> email = it.text
                    "Number" -> number = it.text
                    "Profession" -> profession = it.text
                    "Education" -> education = it.text
                    "Languages" -> languages = it.text
                    "Hobbies" -> hobbies = it.text
                }
            }
        }
        your_cv_name.text = name
        your_cv_location.text = location
        your_cv_gender.text = gender
        your_cv_age.text = age
        your_cv_email.text = email
        your_cv_phone.text = number
        your_cv_profession.text = profession
        your_cv_education.text = education
        your_cv_languages.text = languages
        your_cv_hobbies.text = hobbies

    }
}