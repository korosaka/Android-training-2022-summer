package com.example.androidtraining20220719.model.services

import com.example.androidtraining20220719.model.entities.CharactersApiEntity
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {

    @GET("api")
    fun fetchCharactersURL(): Call<CharactersApiEntity>
}