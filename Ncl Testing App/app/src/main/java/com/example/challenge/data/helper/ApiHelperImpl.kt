package com.example.challenge.data.helper

import com.example.challenge.data.ApiService
import com.example.challenge.model.Ship
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getShipDetails(name: String): Response<Ship> = apiService.getShipDetails(name)

}