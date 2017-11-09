package com.example.codemate.testbot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.codemate.testbot.login.EmployerLoginActivity
import com.example.codemate.testbot.login.JobseekerLoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chat_btn_jobseeker.setOnClickListener {
//            val intent = ChatActivity.newIntent(this)
            val intent = JobseekerLoginActivity.newIntent(this)
            startActivity(intent)
        }

        chat_btn_employer.setOnClickListener {
            //val intent = YourCvActivity.newIntent(this)
            val intent = EmployerLoginActivity.newIntent(this)
            startActivity(intent)
        }
    }
}
