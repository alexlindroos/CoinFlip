package com.example.codemate.testbot.profile

import android.os.Bundle
import com.example.codemate.testbot.BaseActivity
import com.example.codemate.testbot.R
import kotlinx.android.synthetic.main.activity_employer_profile.*

/**
 * Created by Alex Lindroos on 30/11/2017.
 */
//Activity for the Profile page

class MyProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_profile)
        invalidateView()
    }

    //Function to invalidate the view
    private fun invalidateView() {
        employer_profile_name.text = "CoinFlip"
        employer_profile_recruiter_name.text = "Rick Recruiter"
        employer_profile_email.text = "rick_recruiter@coinflip.com"
        employer_profile_phone.text = "040-123123322"
        employer_profile_company_info_text.text = "Coinflip is a startup that is established in " +
                "autumn 2017. Our field of expertise is in Industrial Management, Software Developement and Marketing. "
        employer_profile_looking_for_text.text = "We are looking for new developers and business stars."
        employer_profile_salary_text.text = "~3000 euro/month."
    }
}