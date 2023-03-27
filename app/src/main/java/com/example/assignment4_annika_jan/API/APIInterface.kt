package com.example.assignment4_annika_jan.API

import com.example.assignment4_annika_jan.DataAPIItem
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("posts")
    fun getAPIData(): retrofit2.Call<List<DataAPIItem>>
}