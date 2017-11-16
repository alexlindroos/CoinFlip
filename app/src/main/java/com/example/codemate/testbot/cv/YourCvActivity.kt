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
        val NAME = "name"
        val LOCATION = "location"
        val GENDER = "gender"
        val AGE = "age"
        val EMAIL = "email"
        val NUMBER = "number"
        val PROFESSION = "profession"
        val EDUCATION = "education"
        val LANGUAGES = "languages"
        val HOBBIES = "hobbies"

        fun newIntent(context: Context,
                      name: String,
                      location: String,
                      gender: String,
                      age: String,
                      email: String,
                      number: String,
                      profession: String,
                      education: String,
                      languages: String,
                      hobbies: String): Intent {
            val intent = Intent(context, YourCvActivity::class.java)
            intent.putExtra(NAME, name)
            intent.putExtra(LOCATION, location)
            intent.putExtra(GENDER, gender)
            intent.putExtra(AGE, age)
            intent.putExtra(EMAIL, email)
            intent.putExtra(NUMBER, number)
            intent.putExtra(PROFESSION, profession)
            intent.putExtra(EDUCATION, education)
            intent.putExtra(LANGUAGES, languages)
            intent.putExtra(HOBBIES, hobbies)
            return intent
        }
    }

    private var name: String = ""
    private var location: String = ""
    private var gender: String = ""
    private var age: String = ""
    private var email: String = ""
    private var number: String = ""
    private var profession: String = ""
    private var education: String = ""
    private var languages:String = ""
    private var hobbies: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yourcv)

        name = intent.getStringExtra(NAME)
        location = intent.getStringExtra(LOCATION)
        gender = intent.getStringExtra(GENDER)
        age = intent.getStringExtra(AGE)
        email = intent.getStringExtra(EMAIL)
        number = intent.getStringExtra(NUMBER)
        profession = intent.getStringExtra(PROFESSION)
        education = intent.getStringExtra(EDUCATION)
        languages = intent.getStringExtra(LANGUAGES)
        hobbies = intent.getStringExtra(HOBBIES)

        invalidateView()
    }

    private fun invalidateView() {
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