package com.example.challenge.data

import com.example.challenge.model.Ship
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/in/en/cms-service/cruise-ships/{shipName}")
    suspend fun getShipDetails(@Path("shipName") shipName:String): Response<Ship>
}