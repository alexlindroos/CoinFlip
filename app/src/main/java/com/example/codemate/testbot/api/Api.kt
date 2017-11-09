package com.example.codemate.testbot.api

import com.example.codemate.testbot.model.Conversation
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Alex Lindroos on 01/11/2017.
 */

interface Api {

    @GET("getConversations?key=efcc5ef2da3ebd64dd7ad069d4a860c6&direction=in")
    fun getConversations(): Observable<Conversation>
}