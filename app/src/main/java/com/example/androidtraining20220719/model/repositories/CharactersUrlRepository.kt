package com.example.androidtraining20220719.model.repositories

import com.example.androidtraining20220719.model.services.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersUrlRepository {

    fun fetchCharactersApiUrl(): String? {
        val service = createRetrofit().create(CharacterService::class.java)
        val response = service.fetchCharactersURL().execute()
        return response.body()?.characters
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}