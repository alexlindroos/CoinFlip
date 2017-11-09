package com.example.codemate.testbot.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.codemate.testbot.BaseActivity
import com.example.codemate.testbot.R
import com.example.codemate.testbot.cv.CVList
import com.example.codemate.testbot.cv.YourCvActivity
import com.example.codemate.testbot.signup.EmployerSignupActivity
import kotlinx.android.synthetic.main.activity_login_employer.*

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
        val intent = Intent(this, CVList::class.java)
        startActivity(intent)
        finish()
    }
}