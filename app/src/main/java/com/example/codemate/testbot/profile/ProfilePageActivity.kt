package com.example.codemate.testbot.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.codemate.testbot.BaseActivity
import com.example.codemate.testbot.R
import com.example.codemate.testbot.model.User
import kotlinx.android.synthetic.main.activity_yourprofile.*
import org.jetbrains.anko.image

/**
 * Created by Alex Lindroos on 08/11/2017.
 */

//Activity for the Profile page

class ProfilePageActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ProfilePageActivity::class.java)
            return intent
        }
    }

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yourprofile)
        //Savig the user object from intent extras
        user = intent.getSerializableExtra("userExtra") as User
        invalidateView()
    }
//Function to invalidate the view
    private fun invalidateView() {
        your_profile_profile_pic.image = getDrawable(user.img)
        your_profile_name.text = user.name
        your_profile_profession.text = "Profession: ${user.profession}"
        your_profile_education_text.text = user.education
        your_profile_experience_text.text = user.experience
        your_profile_hobbies_text.text = user.hobbies
        your_profile_intrests_text.text = user.intrests
        your_profile_age.text = "Age: ${user.age}"
        your_profile_email.text = user.email
    }
}