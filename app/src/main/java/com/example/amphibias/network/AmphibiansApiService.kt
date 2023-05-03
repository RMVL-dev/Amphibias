package com.example.amphibias.network

import com.example.amphibias.data.AmphibiansModel
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibiansData(): List<AmphibiansModel>
}