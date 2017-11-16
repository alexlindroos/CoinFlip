package com.example.codemate.testbot.model

import java.io.Serializable

/**
 * Created by Alex Lindroos on 26/10/2017.
 */


data class User(val img: Int, val name: String, val age: String, val email: String, val phone: String,
                val profession: String, val education: String, val experience: String,
                val intrests: String, val hobbies: String): Serializable