package com.example.codemate.testbot.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.codemate.testbot.BaseActivity
import com.example.codemate.testbot.R
import com.example.codemate.testbot.chat.ChatActivity
import com.example.codemate.testbot.cv.CVList
import com.example.codemate.testbot.cv.YourCvActivity
import com.example.codemate.testbot.signup.EmployerSignupActivity
import kotlinx.android.synthetic.main.activity_login_employer.*
import org.jetbrains.anko.toast

/**
 * Created by Alex Lindroos on 05/10/2017.
 */

class EmployerLoginActivity: BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, EmployerLoginActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_employer)

        employer_btn_login.setOnClickListener {
            performLogin()
        }

        employer_link_signup.setOnClickListener {
            val intent = Intent(this, EmployerSignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performLogin() {
        val username = employer_input_username.text.toString()
        val password = employer_input_password.text.toString()
        var usernameFromDb = "admin" //FOR TESTING
        var passwordFromDb = "test" //FOR TESTING

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            toast("Some of the fields are empty.")
        }

        if (password == passwordFromDb && username == usernameFromDb) {
            val intent = Intent(this, CVList::class.java)
            startActivity(intent)
            finish()
        } else {
            toast("Login credentials are wrong.")
        }
    }
}