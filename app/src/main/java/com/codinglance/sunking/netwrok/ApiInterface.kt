package com.codinglance.sunking.netwrok

import com.codinglance.sunking.model.CounrtyDate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiInterface
{
    companion object{
        const val BASE_URL="https://run.mocky.io/v3/"
    }


    @GET("286f38b4-c6c1-4348-aabc-6d396dcbc4de")
    suspend fun getData():CounrtyDate



}