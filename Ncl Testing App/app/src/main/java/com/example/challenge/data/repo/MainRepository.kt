package com.example.challenge.data.repo

import com.example.challenge.data.helper.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getShipDetails(name:String)  = apiHelper.getShipDetails(name)

}