package com.example.androidtraining20220719.model.services

import com.example.androidtraining20220719.model.entities.CharactersApiEntity
import com.example.androidtraining20220719.model.entities.CharactersEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterService {

    @GET("api")
    fun fetchCharactersURL(): Call<CharactersApiEntity>

    @GET
    fun fetchCharacters(@Url url: String): Call<CharactersEntity>
}