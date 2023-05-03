package com.example.amphibias.repository.interfaces

import com.example.amphibias.data.AmphibiansModel

interface AmphibiansRepository {
    suspend fun getAmphibiansData(): List<AmphibiansModel>
}