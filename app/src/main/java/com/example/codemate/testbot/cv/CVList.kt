package com.example.codemate.testbot.cv

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatCallback
import android.support.v7.app.AppCompatDelegate
import android.support.v7.view.ActionMode
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.example.codemate.testbot.BaseActivity
import com.example.codemate.testbot.R
import com.example.codemate.testbot.model.User
import com.example.codemate.testbot.profile.MyProfileActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


/**
 * Created by Alex Lindroos on 26/10/2017.
 */
class CVList: BaseActivity(), AppCompatCallback{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cvlist)
        //AppCompatDelegate is made for getting toolbar to work in this activity
        val delegate = AppCompatDelegate.create(this, this)
        delegate.onCreate(savedInstanceState)
        delegate.setContentView(R.layout.activity_cvlist)
        //Setup toolbar
        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        delegate.setSupportActionBar(toolbar)
        delegate.supportActionBar?.title = getString(R.string.all_jobseekers)
        //Setup Recycleriew
        val rv = findViewById<RecyclerView>(R.id.rv_cv)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //In a real app, users should be fetched from the backend
        //But, because we don't have a backend users are hardcoded
        val users = ArrayList<User>()
        users.add(User(R.drawable.face,"Lisa",
                "25",
                "lisa@email.com",
                "0401231333",
                "Doctor",
                "2015 - Medical School of Helsinki \n2011 - Gymnasium \n2008 - Secondary School",
                "2015 - Jorvi (5 months) \n2012 - Kindergarden Töölö (3 months) \n2010 - Alepa (1 year)",
                "Helping people and hearth surgery.",
                "Swimming, cooking and movies."))
        users.add(User(R.drawable.face2,"Mohammed",
                "29",
                "mohammed@email.com",
                "0502352355",
                "Chef",
                "2009 - Restaurant School Perho \n2003 - Secondary School",
                "2009 - Restaurant Olo (10 months) \n2008 - Construction Oy (6 months) \n2005 - Mailman (4 months)",
                "Cooking for the people is my passion and arabic cuisine.",
                "Football, gym, food and music."))
        users.add(User(R.drawable.face3,"Aaron",
                "31",
                "aaron@email.com",
                "0502342311",
                "IT",
                "2007 - Metropolia UAS \n2004 - Gymnasium \n2000 - Secondary School",
                "2010 - Helsinki City IT office (7 years) \n2009 - Tieto (8 months) \n2004 - Prisma (2 year)",
                "Programming and learning new languages.",
                "Gym and playing guitar."))
        users.add(User(R.drawable.face4,"Helen",
                "24",
                "helen@email.com",
                "0442431333",
                "Teacher",
                "2015 - Laurea \n2012 - Helsinki Business School \n2009 - Secondary School",
                "2015 - Sibelius gymnasium (11 months) \n2012 - R-Kioski (1 month) \n2010 - Veikkaus (1 year)",
                "Maths and music.",
                "Dogs and running."))
        //Finishing the Recyclerview setup with the adapter.
        val adapter = CVAdapter(this, users)
        rv.adapter = adapter
    }

    override fun onBackPressed() {
        logout()
    }

    //Function for logging out
    private fun logout() {
        alert(R.string.logout) {
            title = "Alert"
            yesButton {
                finish()
            }
            noButton {

            }
        }.show()
    }
    //Function to move in to my profile
    private fun goToMyProfile() {
        val intent = Intent(this, MyProfileActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            //R.id.search -> println("Search pressed")
            //R.id.action_add_job_ad -> println("Add job ad pressed")
            R.id.action_my_profile -> goToMyProfile()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onWindowStartingSupportActionMode(callback: ActionMode.Callback?): ActionMode? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSupportActionModeStarted(mode: ActionMode?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSupportActionModeFinished(mode: ActionMode?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}