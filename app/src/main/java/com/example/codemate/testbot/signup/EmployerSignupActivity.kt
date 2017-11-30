package com.example.codemate.testbot.signup

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

//Activity for the employer signup

class EmployerSignupActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_employer)
        //Setup onclicklisteners for the signup and login
        employer_btn_signup.setOnClickListener {
            performSignup()
        }
        employer_link_login.setOnClickListener {
            finish()
        }
    }
//Function to performing the signup
    private fun performSignup(){
        val username = employer_signup_input_username.text.toString()
        val password = employer_signup_input_password.text.toString()
        val passwordConfirm = employer_signup_input_password_confim.text.toString()
        //Checking if the username input, password input and passwordconfirm input is empty or null.
        if (username.isNullOrEmpty() || password.isNullOrEmpty() || passwordConfirm.isNullOrEmpty()) {
            toast("Some of the fields are empty.")
        }
        //Checks if the password user has put matches with the password confirm user has put
        //Then inserts that user in to the database
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
