package com.example.codemate.testbot.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.codemate.testbot.BaseActivity
import com.example.codemate.testbot.chat.ChatActivity
import com.example.codemate.testbot.R
import com.example.codemate.testbot.db.RowParser
import com.example.codemate.testbot.db.database
import com.example.codemate.testbot.model.User
import com.example.codemate.testbot.model.UserLoginModel
import com.example.codemate.testbot.signup.JobseekerSignupActivity
import kotlinx.android.synthetic.main.activity_login_jobseeker.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.toast

/**
 * Created by Alex Lindroos on 10/10/2017.
 */

//Jobseeker login activity

class JobseekerLoginActivity: BaseActivity() {

    lateinit var listOfUsers: List<UserLoginModel>

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, JobseekerLoginActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_jobseeker)
        //Persist all the users from db in to the List
        listOfUsers = getUsers(database)
        //Setup onclicklisteners for the login and signup
        jobseeker_btn_login.setOnClickListener {
            performLogin()
        }
        jobseeker_link_signup.setOnClickListener {
            val intent = Intent(this, JobseekerSignupActivity::class.java)
            startActivity(intent)
        }
    }
//Function to perform the login
    private fun performLogin() {
        val username = jobseeker_input_username.text.toString()
        val password = jobseeker_input_password.text.toString()
        var usernameFromDb = "admin" //FOR TESTING
        var passwordFromDb = "test" //FOR TESTING

    //Check if the user inputs are null or empty
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            toast("Some of the fields are empty.")
        }
    //Check if the password and username matches with the database
        if (password == passwordFromDb && username == usernameFromDb) {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            toast("Login credentials are wrong.")
        }
    }
    //Functions to get all the users from the db
    fun getUsers(db: ManagedSQLiteOpenHelper): List<UserLoginModel> = db.use {
        val rowParser = classParser<UserLoginModel>()
        select("User")
                .parseList(rowParser)
    }

}