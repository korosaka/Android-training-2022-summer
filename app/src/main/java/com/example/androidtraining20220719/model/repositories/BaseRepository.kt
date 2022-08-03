package com.example.androidtraining20220719.model.repositories

import com.example.androidtraining20220719.model.services.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepository {

    protected fun createCharacterService(): CharacterService {
        return createRetrofit().create(CharacterService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}