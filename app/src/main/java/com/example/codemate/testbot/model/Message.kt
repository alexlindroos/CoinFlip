package com.example.codemate.testbot.model

import java.io.Serializable

/**
 * Created by Alex Lindroos on 01/11/2017.
 */

//Model for the message

class Message: Serializable {

    var id: String = ""
    var text: String = ""
    var from: String = ""
    var from_type: String = ""
    var to: String = ""
    var to_type: String = ""
    var updated_at: String = ""
    var session: String = ""
    var result: String = ""
    var nlpData: String? = null
    var module: Int = 0
    var moduleNickname: String = ""
    var inResponseTo: Int = 0
    var botID: Int = 0
    var botNickname: String = ""
    var botType: String = ""
    var direction: String = ""
    var media: List<Any>? = null
    var cards: List<Any>? = null
    var quickReplies: List<Any>? = null
    var archieved: Boolean = false
}