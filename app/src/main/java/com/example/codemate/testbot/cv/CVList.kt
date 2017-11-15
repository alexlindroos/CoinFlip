package com.example.codemate.testbot.cv

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

/**
 * Created by Alex Lindroos on 26/10/2017.
 */
class CVList: BaseActivity(), AppCompatCallback{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cvlist)

        val delegate = AppCompatDelegate.create(this, this)
        delegate.onCreate(savedInstanceState)
        delegate.setContentView(R.layout.activity_cvlist)
        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        delegate.setSupportActionBar(toolbar)

        val rv = findViewById<RecyclerView>(R.id.rv_cv)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //In a real app, users should be fetched from the backend
        val users = ArrayList<User>()
        users.add(User(R.drawable.face,"Lisa", "Doctor"))
        users.add(User(R.drawable.face2,"Mohammed", "Chef"))
        users.add(User(R.drawable.face3,"Aaron", "IT"))
        users.add(User(R.drawable.face4,"Helen", "Teacher"))

        val adapter = CVAdapter(this, users)
        rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.search -> println("Search pressed")
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