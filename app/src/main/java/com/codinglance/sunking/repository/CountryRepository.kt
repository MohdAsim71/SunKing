package com.codinglance.sunking.repository

import com.codinglance.sunking.model.CounrtyDate
import com.codinglance.sunking.netwrok.ApiInterface
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(
    private val  service:ApiInterface
)
{
    suspend fun getDate(): CounrtyDate {
        return service.getData()
    }
}