package com.example.codemate.testbot.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alex Lindroos on 01/11/2017.
 */

class ApiManager {

    companion object {
        fun create() : Api {
            var retrofit = Retrofit.Builder()
                    .baseUrl("https://api.motion.ai/1.0/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create<Api>(Api::class.java)
        }
    }
}