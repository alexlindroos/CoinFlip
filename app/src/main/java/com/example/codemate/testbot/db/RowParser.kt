package com.example.codemate.testbot.db

/**
 * Created by Alex Lindroos on 08/11/2017.
 */

interface RowParser<T> {
    fun parseRow(columns: Array<Any>): T
}