package com.example.codemate.testbot.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.codemate.testbot.BaseActivity
import com.example.codemate.testbot.R
import com.example.codemate.testbot.db.database
import kotlinx.android.synthetic.main.activity_signup_employer.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import java.util.*

/**
 * Created by Alex Lindroos on 05/10/2017.
 */
class EmployerSignupActivity: BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, EmployerSignupActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_employer)

        employer_btn_signup.setOnClickListener {
            performSignup()
        }

        employer_link_login.setOnClickListener {
            finish()
        }
    }

    private fun performSignup(){
        val username = employer_signup_input_username.text.toString()
        val password = employer_signup_input_password.text.toString()
        val passwordConfirm = employer_signup_input_password_confim.text.toString()

        if (username.isNullOrEmpty() || password.isNullOrEmpty() || passwordConfirm.isNullOrEmpty()) {
            toast("Some of the fields are empty.")
        }

        if (password == passwordConfirm) {
            database.use {
                insert("User",
                        "id" to Random().nextInt(50) + 1,
                        "username" to username,
                        "password" to passwordConfirm
                )
                toast("User created!")
                finish()
            }
        } else {
            toast("Passwords did not match.")
        }

    }
}
