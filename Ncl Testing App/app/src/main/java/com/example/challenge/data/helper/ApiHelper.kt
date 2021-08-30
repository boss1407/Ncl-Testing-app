package com.example.challenge.data.helper

import com.example.challenge.model.Ship
import retrofit2.Response

interface ApiHelper {
    suspend fun getShipDetails(name: String): Response<Ship>
}