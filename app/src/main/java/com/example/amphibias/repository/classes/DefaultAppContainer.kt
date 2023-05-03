
package com.example.amphibias.repository.classes

import com.example.amphibias.network.AmphibiansApiService
import com.example.amphibias.repository.interfaces.AmphibiansRepository
import com.example.amphibias.repository.interfaces.AppContainer
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


class DefaultAppContainer:AppContainer {

    private val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit:Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(retrofitService)
    }
}