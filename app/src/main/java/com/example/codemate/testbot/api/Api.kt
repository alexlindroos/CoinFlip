package com.example.codemate.testbot.api

import com.example.codemate.testbot.model.Conversation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alex Lindroos on 01/11/2017.
 */

// This interface is for retrofit to know API endpoints

interface Api {

    //Api endpoint for fetching all the conversations
    @GET("getConversations?key=efcc5ef2da3ebd64dd7ad069d4a860c6&direction=in")
    fun getConversations(): Observable<Conversation>

    //Api endpoint for fetching all the conversations with session id as a parameter
    @GET("getConversations?key=efcc5ef2da3ebd64dd7ad069d4a860c6&direction=in&session")
    fun getConversationsWithSessionId(@Query("sessionId") sessionId: String): Observable<Conversation>
}