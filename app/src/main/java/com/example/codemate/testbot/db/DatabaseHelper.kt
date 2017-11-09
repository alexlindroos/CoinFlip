package com.example.codemate.testbot.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by Alex Lindroos on 26/10/2017.
 */

class DatabaseHelper(context: Context): ManagedSQLiteOpenHelper(context, DB_NAME,
        null, DB_VERSION) {

    companion object {
        val DB_NAME = "cv.db"
        val DB_VERSION = 1
        private var instance: DatabaseHelper? = null

        //Singleton that will save the instance of the helper, in a synchronized way so that different threads can not generate more than one instance.
        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("User", true,
                "_id" to INTEGER + PRIMARY_KEY,
                "username" to TEXT,
                "password" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

// Access property for Context
val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)


