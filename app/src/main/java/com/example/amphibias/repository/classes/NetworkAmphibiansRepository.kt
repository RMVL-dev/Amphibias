package com.example.amphibias.repository.classes

import com.example.amphibias.data.AmphibiansModel
import com.example.amphibias.network.AmphibiansApiService
import com.example.amphibias.repository.interfaces.AmphibiansRepository

class NetworkAmphibiansRepository(
    private val retrofitService: AmphibiansApiService
    ):AmphibiansRepository {
    override suspend fun getAmphibiansData(): List<AmphibiansModel> = retrofitService.getAmphibiansData()
}