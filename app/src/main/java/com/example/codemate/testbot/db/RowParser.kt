package com.example.codemate.testbot.db

/**
 * Created by Alex Lindroos on 08/11/2017.
 */

//Interface for parsing the rows in DB

interface RowParser<T> {
    fun parseRow(columns: Array<Any>): T
}